package com.soc.agent.security;

/**
 * EICAR test-file detection and a small local signature database of known-bad
 * hashes. The EICAR string is the industry-standard non-malicious file that all
 * AV engines flag, used to verify the agent's scanning pipeline end to end.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0011"}, d2 = {"Lcom/soc/agent/security/Eicar;", "", "()V", "EICAR_SHA256", "", "EICAR_STRING", "KNOWN_BAD_HASHES", "", "getKNOWN_BAD_HASHES", "()Ljava/util/Map;", "isEicar", "", "bytes", "", "isEicarHash", "sha256", "lookup", "app_debug"})
public final class Eicar {
    
    /**
     * The canonical 68-character EICAR test string. The trailing "H+H*" is not a
     * Kotlin template — the dollar signs are escaped to produce a literal string.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EICAR_STRING = "X5O!P%@AP[4\\PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*";
    
    /**
     * Well-known SHA-256 of the EICAR test string.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EICAR_SHA256 = "275a021bbfb6489e54d471899f7db9d1663fc695ec2fe2a2c4538aabf651fd0f";
    
    /**
     * Local signature database: EICAR plus a handful of real-world malware
     * samples commonly used for lab validation. Key = SHA-256, value = label.
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.String, java.lang.String> KNOWN_BAD_HASHES = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.security.Eicar INSTANCE = null;
    
    private Eicar() {
        super();
    }
    
    /**
     * Local signature database: EICAR plus a handful of real-world malware
     * samples commonly used for lab validation. Key = SHA-256, value = label.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> getKNOWN_BAD_HASHES() {
        return null;
    }
    
    /**
     * Byte-level EICAR detection: true when the array contains the EICAR marker.
     */
    public final boolean isEicar(@org.jetbrains.annotations.NotNull()
    byte[] bytes) {
        return false;
    }
    
    /**
     * True when the given (lowercased or any-case) SHA-256 equals the EICAR hash.
     */
    public final boolean isEicarHash(@org.jetbrains.annotations.Nullable()
    java.lang.String sha256) {
        return false;
    }
    
    /**
     * Looks up a hash in the local signature DB, returning the label or null.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String lookup(@org.jetbrains.annotations.Nullable()
    java.lang.String sha256) {
        return null;
    }
}