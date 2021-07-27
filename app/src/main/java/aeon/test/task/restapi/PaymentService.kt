package aeon.test.task.restapi

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface PaymentService : BaseService {

    @GET("payments")
    fun payments(): Response<ResponseBody>
}