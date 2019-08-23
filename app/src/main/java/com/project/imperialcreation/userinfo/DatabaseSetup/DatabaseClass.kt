package com.project.imperialcreation.userinfo.DatabaseSetup

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.project.imperialcreation.userinfo.Model.User
import android.arch.persistence.room.Database
import android.content.Context
@Database(entities = arrayOf(User::class), version = 1,exportSchema = false)
abstract class DatabaseClass : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        /*  @Volatile private var instance: DatabaseClass? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
                DatabaseClass::class.java, "todo-list.db")
                .build()
    }*/

        private var INSTANCE: DatabaseClass? = null

        fun getAppDatabase(context: Context): DatabaseClass {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseClass::class.java, "user-database")
                        // allow queries on the main thread.
                        // Don't do this on a real app! See PersistenceBasicSample for an example.
                        // .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE as DatabaseClass
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
