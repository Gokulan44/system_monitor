package com.soc.agent.database;

import androidx.room.TypeConverter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Room type converters that serialise list/map columns to JSON strings and
 * back. Used by entities that carry List<Double>, List<String>, List<Int> or
 * Map<String, Any> columns (CPU history, DNS servers, policy rules, ...).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u001e\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0018\u0010\r\u001a\u00020\u00072\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0007J\u0018\u0010\u000f\u001a\u00020\u00072\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004H\u0007J\u001e\u0010\u0010\u001a\u00020\u00072\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bH\u0007J\u0018\u0010\u0012\u001a\u00020\u00072\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004H\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/soc/agent/database/Converters;", "", "()V", "fromDoubleList", "", "", "value", "", "fromIntList", "", "fromRulesJson", "", "fromStringList", "toDoubleList", "list", "toIntList", "toRulesJson", "map", "toStringList", "app_debug"})
public final class Converters {
    
    public Converters() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> fromStringList(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toStringList(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> list) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Double> fromDoubleList(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toDoubleList(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Double> list) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> fromIntList(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toIntList(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> list) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Object> fromRulesJson(@org.jetbrains.annotations.Nullable()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toRulesJson(@org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> map) {
        return null;
    }
}