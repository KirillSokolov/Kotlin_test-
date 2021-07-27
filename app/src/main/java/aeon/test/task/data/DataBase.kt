package aeon.test.task.data

import aeon.test.task.data.dao.UserDao
import aeon.test.task.data.tables.User
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = true
)

abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao?

}