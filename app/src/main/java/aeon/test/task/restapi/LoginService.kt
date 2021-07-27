package aeon.test.task.restapi

import aeon.test.task.restapi.request.LoginRequest
import aeon.test.task.restapi.response.BaseResponse
import aeon.test.task.restapi.response.UserData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService : BaseService {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<BaseResponse<UserData>>

    @POST("api/session")
    suspend fun register(@Body request: LoginRequest): Response<ResponseBody>

}