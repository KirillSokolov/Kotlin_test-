package aeon.test.task.model

import aeon.test.task.data.dao.UserDao
import aeon.test.task.data.tables.User
import aeon.test.task.restapi.response.BaseData
import aeon.test.task.restapi.response.UserData

class UserModel : BaseModel(), Model {
    var userDao: UserDao? = null
    lateinit var user: User

    init {
        userDao = db?.userDao()
    }

    override fun create() {
        userDao?.insert(user)
        user = userDao?.selectByToken(user.getToken()!!)?.blockingGet()!!
        ph?.putLong("user_id", user.getUserId())?.commit()
    }

    override fun update() {
        userDao?.update(user)
    }

    override fun convertFromResponse(data: BaseData) {
        val userData = (data as UserData)
        user = User(userData.getToken()!!)
    }
}