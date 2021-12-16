package aeon.test.task.restapi.response

import com.google.gson.annotations.SerializedName

class ImageData : BaseData() {
    @SerializedName("id")
    private val id: String? = null

    @SerializedName("email")
    private val email: String? = null

    @SerializedName("name")
    private val name: String? = null

    @SerializedName("phone")
    private val phone: String? = null

    @SerializedName("token")
    private val token: String? = null

    fun getToken(): String? {
        return token
    }
}