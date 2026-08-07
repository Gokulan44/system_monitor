package com.soc.agent.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.soc.agent.database.dao.LockedAppDao
import com.soc.agent.database.dao.UnlockHistoryDao
import com.soc.agent.database.dao.FailedAttemptDao
import com.soc.agent.database.dao.IntruderSelfieDao
import com.soc.agent.database.dao.SecuritySettingsDao
import com.soc.agent.database.dao.BackupDao
import com.soc.agent.database.entity.LockedAppEntity
import com.soc.agent.database.entity.UnlockHistoryEntity
import com.soc.agent.database.entity.FailedAttemptEntity
import com.soc.agent.database.entity.IntruderSelfieEntity
import com.soc.agent.database.entity.SecuritySettingsEntity
import com.soc.agent.database.entity.BackupEntity

/**
 * Room database for App Lock module. Isolated from the main [AppDatabase]
 * for modularity — locked apps, unlock history, failed attempts, intruder
 * selfies, security settings, and backups live here.
 *
 * Access via [LockDatabase.getInstance]. All DAOs are exposed as abstract
 * val properties.
 */
@Database(
    entities = [
        LockedAppEntity::class,
        UnlockHistoryEntity::class,
        FailedAttemptEntity::class,
        IntruderSelfieEntity::class,
        SecuritySettingsEntity::class,
        BackupEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LockDatabase : RoomDatabase() {

    abstract fun lockedAppDao(): LockedAppDao
    abstract fun unlockHistoryDao(): UnlockHistoryDao
    abstract fun failedAttemptDao(): FailedAttemptDao
    abstract fun intruderSelfieDao(): IntruderSelfieDao
    abstract fun securitySettingsDao(): SecuritySettingsDao
    abstract fun backupDao(): BackupDao

    companion object {
        private const val DB_NAME = "lock_database.db"

        @Volatile
        private var INSTANCE: LockDatabase? = null

        fun getInstance(context: Context): LockDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    LockDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}