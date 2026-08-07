#!/usr/bin/env python3
"""Structural audit for the android/ App Lock build.

Performs compile-time-resolvable checks WITHOUT a Gradle/JDK toolchain:
  1. Manifest components (.ui.AppLockGateActivity, .services.AppLockService)
     resolve to .kt files existing in the source tree.
  2. Every @string/@color/@drawable/@menu/@layout reference used in the new
     resources exists in res/values or res/drawable|menu|layout|xml.
  3. Every @+id defined and every @id referenced in the App Lock layouts
     resolves within that layout file (binding-style checks).
  4. Room entity columns vs the SQL column names referenced in the DAO.
Prints a report; exits non-zero when hits are found.
"""
import os
import re
import sys

ROOT = r"C:\Users\GOPAL\Music\system monitor\android"
SRC = os.path.join(ROOT, "app", "src", "main")
JAVA = os.path.join(SRC, "java", "com", "soc", "agent")
RES = os.path.join(SRC, "res")

hits = []
notes = []


def add(sev, msg):
    hits.append((sev, msg))


# ---- 1. Manifest components resolve to .kt files -------------------------
def kt_path(component):
    rel = component.strip(".").replace(".", "/") + ".kt"
    return os.path.normpath(os.path.join(JAVA, rel))


manifest = os.path.join(SRC, "AndroidManifest.xml")
mt = open(manifest, encoding="utf-8").read()
for name in re.findall(r'android:name="(\.\S+?)"', mt):
    if name in (".MainActivity", ".App", ".LoginActivity"):
        continue
    p = kt_path(name)
    if os.path.exists(p):
        notes.append(f"manifest component resolves: {name}")
    else:
        add("MANIFEST", f"{name} -> missing source file {os.path.relpath(p, ROOT)}")

for expect in [".ui.AppLockGateActivity", ".services.AppLockService"]:
    if expect not in mt:
        add("MANIFEST", f"declaration missing for {expect}")

# ---- Resource catalog ------------------------------------------------
RESOURCETYPES = {}
for f in os.listdir(os.path.join(RES, "values")):
    if not f.endswith(".xml"):
        continue
    txt = open(os.path.join(RES, "values", f), encoding="utf-8").read()
    for m in re.finditer(r'<(\w+)\s+name="([^"]+)"', txt):
        tag, nm = m.group(1), m.group(2)
        RESOURCETYPES.setdefault(tag, set()).add(nm)


def res_exists(want):
    """want like 'string/nav_applock' or 'color/soc_bg'."""
    if "/" not in want:
        return True
    typ, name = want.split("/", 1)
    if typ in ("string", "color", "dimen", "style"):
        return name in RESOURCETYPES.get(typ, set())
    dirs = {
        "layout": ("layout", ".xml"), "drawable": ("drawable", ".xml"),
        "menu": ("menu", ".xml"), "xml": ("xml", ".xml"),
        "mipmap": ("mipmap-anydpi-v26", ".xml"),
        "bool": ("values", ".xml"),
    }
    if typ in dirs:
        d, ext = dirs[typ]
        if typ == "bool":
            return name in RESOURCET.get("bool", set())
        return os.path.exists(os.path.join(RES, d, name + ext))
    return True


# ---- 2. @res references in new resources resolve -------------------------
applock_rel = [
    "layout/fragment_app_lock.xml",
    "layout/fragment_locked_apps.xml",
    "layout/fragment_add_app_lock.xml",
    "layout/item_app_lock.xml",
    "layout/activity_app_lock_gate.xml",
    "menu/bottom_nav.xml",
]
for rel in applock_rel:
    p = os.path.join(RES, rel)
    if not os.path.exists(p):
        add("RESEXIST", f"expected resource file missing: {rel}")
        continue
    txt = open(p, encoding="utf-8").read()
    for m in re.finditer(r'@(string|color|drawable|menu|layout|style|dimen|mipmap|xml|bool)/([A-Za-z0-9_]+[A-Za-z0-9_.]*)', txt):
        ref = f"{m.group(1)}/{m.group(2)}"
        # Dotted styles resolve to library-provided themes (material, appcompat,
        # android) which are not part of our res/ tree - skip those.
        if m.group(1) == "style" and "." in m.group(2):
            notes.append(f"library style (skipped): {ref}")
            continue
        if not res_exists(ref):
            add("XMLRES", f"{rel}: unresolved @{ref}")

# ---- 3. @id/@+id self-consistency per layout ------------------------------
for rel in applock_rel:
    p = os.path.join(RES, rel)
    if not os.path.exists(p):
        continue
    txt = open(p, encoding="utf-8").read()
    defined = set(re.findall(r'android:id="@\+id/(\w+)"', txt))
    if not defined:
        continue
    for ref in re.findall(r'@id/(\w+)', txt):
        if ref in defined:
            continue
        # cross-layout ids that legitimately live elsewhere
        if ref in ("fragment_container", "bottom_nav"):
            continue
        add("LAYOUT", f"{rel}: @id/{ref} not defined in this layout")

# ---- 4. Room entity columns vs DAO SQL ------------------------------------
entity_p = os.path.join(JAVA, "database", "entity", "LockedAppEntity.kt")
dao_p = os.path.join(JAVA, "database", "dao", "LockedAppDao.kt")
entity = open(entity_p, encoding="utf-8").read()
cols = set(re.findall(r'@ColumnInfo\s*\(\s*name="([^"]+)"', entity))
sqltokens = " ".join(re.findall(r'@Query\("(.+?)"\)', open(dao_p, encoding="utf-8").read()))
table = re.search(r'tableName = "(\w+)"', entity).group(1)
if f"FROM {table}" not in sqltokens:
    add("ROOM", f"table {table} not used in any DAO @Query")
for col in cols:
    if col not in sqltokens:
        add("ROOM", f"column '{col}' never referenced in DAO SQL")

# ---- 5. Class-member / import consistency spot checks --------------------
# (light, since full compilation is unavailable)
gate = open(os.path.join(JAVA, "ui", "AppLockGateActivity.kt"), encoding="utf-8").read()
for member in ["Prefs.pinHash", "Prefs.pinEnabled", "PatternLock.matches",
               "AppLockService.recordUnlock"]:
    if member not in gate:
        add("GATE", f"AppLockGateActivity does not reference expected member {member}")

# ---- Summary ------------------------------------------------------
print("=" * 70)
print("Android App Lock structural audit")
print("=" * 70)
for sev, msg in hits:
    print(f"  [{sev}] {msg}")
if not hits:
    print("  no structural hits found")
print(f"{'='*70}\n{len(hits)} hits")
for n in notes:
    print("  note: " + n)
sys.exit(1 if hits else 0)