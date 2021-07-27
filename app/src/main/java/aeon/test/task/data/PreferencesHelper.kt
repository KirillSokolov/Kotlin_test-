package aeon.test.task.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("CommitPrefEdits")
class PreferencesHelper(context: Context) {

    private var fileName: String = "app_test_preferences"

    private var prefs: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        prefs = getSharedPrefs(
            context
        )
        editor = prefs?.edit()
    }

    private fun getSharedPrefs(context: Context): SharedPreferences? {
        return context.getSharedPreferences(
            fileName,
            Context.MODE_PRIVATE
        )
    }

    fun putString(key: String?, value: String?): SharedPreferences.Editor? {
        return editor!!.putString(key, value)
    }

    fun putLong(key: String?, value: Long): SharedPreferences.Editor? {
        return editor!!.putLong(key, value)

    }

    fun getString(key: String?, defValue: String?): String? {
        return prefs!!.getString(key, defValue)
    }

    fun getLong(key: String?, defValue: Long?): Long {
        return prefs!!.getLong(key, defValue!!)
    }
}