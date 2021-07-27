package aeon.test.task.data.dao

import aeon.test.task.data.tables.User
import androidx.room.*
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: User)

    @Query("SELECT * FROM users WHERE  user_id = :userId")
    fun selectById(userId: Long): Single<User?>?

    @Query("SELECT * FROM users WHERE token = :token")
    fun selectByToken(token: String): Maybe<User?>?
}