package com.soc.agent.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.soc.agent.database.entity.IocEntity
import com.soc.agent.database.entity.PolicyEntity

@Dao
interface PolicyDao {

    /** Insert/update server policies (matched on the server policy id). */
    @Upsert
    suspend fun upsertPolicies(policies: List<PolicyEntity>)

    /** All locally cached policies. */
    @Query("SELECT * FROM policies ORDER BY id ASC")
    suspend fun getPolicies(): List<PolicyEntity>

    @Query("DELETE FROM policies")
    suspend fun clearPolicies()

    /**
     * Insert/update IOCs. REPLACE handles the unique (value, type)
     * constraint (later blocklist entries overwrite earlier duplicates).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertIocs(iocs: List<IocEntity>)

    /** Active IOCs of any type, grouped by type. */
    @Query("SELECT * FROM iocs WHERE active = 1 ORDER BY type ASC, value ASC")
    suspend fun getIocs(): List<IocEntity>

    /** Active IOCs for blocklist enforcement. */
    @Query("SELECT * FROM iocs WHERE active = 1 ORDER BY type ASC, value ASC")
    suspend fun getBlocklist(): List<IocEntity>

    @Query("SELECT value FROM iocs WHERE type = 'domain' AND active = 1 ORDER BY value ASC")
    suspend fun getBlockedDomains(): List<String>

    @Query("SELECT value FROM iocs WHERE type = 'url' AND active = 1 ORDER BY value ASC")
    suspend fun getBlockedUrls(): List<String>

    @Query("SELECT value FROM iocs WHERE type = 'hash' AND active = 1 ORDER BY value ASC")
    suspend fun getBlockedHashes(): List<String>

    @Query("DELETE FROM iocs")
    suspend fun clearIocs()
}