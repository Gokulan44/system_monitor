package com.soc.agent.security;

import kotlinx.coroutines.Dispatchers;
import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * SHA-256 hashing helpers. File hashing reads in 64 KB chunks so large APKs can
 * be processed without loading the whole file into memory, and runs on the IO
 * dispatcher because it is a blocking operation.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\bJ\f\u0010\u0016\u001a\u00020\b*\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/soc/agent/security/Sha256;", "", "()V", "CHUNK_SIZE", "", "HEX_CHARS", "", "hashBytes", "", "bytes", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hashFile", "file", "Ljava/io/File;", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hashStream", "input", "Ljava/io/InputStream;", "(Ljava/io/InputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hashString", "s", "toHex", "app_debug"})
public final class Sha256 {
    private static final int CHUNK_SIZE = 65536;
    @org.jetbrains.annotations.NotNull()
    private static final char[] HEX_CHARS = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.soc.agent.security.Sha256 INSTANCE = null;
    
    private Sha256() {
        super();
    }
    
    /**
     * Computes the lowercase hex SHA-256 of a file's contents.
     *
     * @throws Exception if the file cannot be read.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object hashFile(@org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    /**
     * Computes the SHA-256 hex of an in-memory byte array (used for DEX entries
     * and small scan targets). Runs on the IO dispatcher for uniformity.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object hashBytes(@org.jetbrains.annotations.NotNull()
    byte[] bytes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    /**
     * Computes the SHA-256 hex of a UTF-8 string (used for PIN hashing and IOC
     * string matching).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String hashString(@org.jetbrains.annotations.NotNull()
    java.lang.String s) {
        return null;
    }
    
    /**
     * Helpers to compute SHA-256 from any open stream in chunks.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object hashStream(@org.jetbrains.annotations.NotNull()
    java.io.InputStream input, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    /**
     * Lower-case hex encoding of a byte array.
     */
    private final java.lang.String toHex(byte[] $this$toHex) {
        return null;
    }
}