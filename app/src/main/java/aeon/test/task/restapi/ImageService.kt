package aeon.test.task.restapi

import aeon.test.task.restapi.response.BaseResponse
import aeon.test.task.restapi.response.ImageData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ImageService: BaseService {

    @GET("/android-dev-task.php")
    fun images(): okhttp3.Response
}