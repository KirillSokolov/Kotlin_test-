package aeon.test.task

import aeon.test.task.data.Database
import aeon.test.task.data.PreferencesHelper
import android.app.Application
import android.content.Context
import androidx.room.Room
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

class BaseApplication  : Application() {

    var executor: ExecutorService = Executors.newFixedThreadPool(1)


    override fun onCreate() {
        super.onCreate()
        executor.execute(FutureTask<Void>(Initialization()))
    }

    init {
        instance = this
    }

    companion object {
        private const val dbName:String = "DB_NAME"

        private var instance: BaseApplication? = null

        private fun getApplicationContext() : Context {
            return instance!!.applicationContext
        }

        fun getDataBase() : Database {
            return Room.databaseBuilder(
                getApplicationContext(),
                Database::class.java, dbName).build()
        }

        fun getPreferenceHelper() : PreferencesHelper {
            return PreferencesHelper(getApplicationContext())
        }
    }

    internal class Initialization : Callable<Void?> {
        @Throws(Exception::class)
        override fun call(): Void? {
            //Todo:init
            return null
        }
    }
}