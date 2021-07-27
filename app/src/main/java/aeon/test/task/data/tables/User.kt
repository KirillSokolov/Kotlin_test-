package aeon.test.task.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = [Index(value = ["user_id"], unique = true)])

class User(token: String) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id", defaultValue = "0")
    private var userId = 0L

    @ColumnInfo(name = "combination", defaultValue = "")
    private var combination = ""

    @ColumnInfo(name = "email", defaultValue = "")
    private var email = ""

    @ColumnInfo(name = "last_sync_time", defaultValue = "0")
    private var lastSyncTime = 0L

    @ColumnInfo(name = "token", defaultValue = "")
    private var token = ""

    init {
        setToken(token)
    }

    fun getUserId(): Long {
        return userId
    }

    fun setUserId(userId: Long) {
        this.userId = userId
    }

    fun getCombination(): String {
        return combination
    }

    fun setCombination(combination: String) {
        this.combination = combination
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getLastSyncTime(): Long? {
        return lastSyncTime
    }

    fun setLastSyncTime(lastSyncTime: Long) {
        this.lastSyncTime = lastSyncTime
    }

    fun getToken(): String? {
        return token
    }

    fun setToken(token: String) {
        this.token = token
    }
}