package base.smartservices.com.cache.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import base.smartservices.com.cache.ioThread
import base.smartservices.com.cache.model.CachedMinion

@Database(entities = [(CachedMinion::class)], version = 1)
abstract class MinionDatabase: RoomDatabase() {
    abstract fun minionDao(): MinionDao

    companion object {
        @Volatile private var INSTANCE: MinionDatabase? = null

        fun getInstance(context: Context): MinionDatabase =
            INSTANCE?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MinionDatabase::class.java, "minionDB.db")
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        ioThread {
                            getInstance(context).minionDao().saveMinions(PREPOPULATE_DATA)
                        }
                    }
                })
                .build()

        val PREPOPULATE_DATA = listOf(
            CachedMinion("Nabile Coulibaly", "Software Engineer", "https://images"),
            CachedMinion("Avery Coulibaly", "Son", "https://images")
        )
    }
}