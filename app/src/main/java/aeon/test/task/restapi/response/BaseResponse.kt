package aeon.test.task.restapi.response

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

class BaseResponse<T> {

    @Nullable
    @SerializedName("response")
    private var data: List<T>? = null

    @Nullable
    fun getData(): List<T> {
        return data!!
    }

    fun setData(data: List<T>?) {
        this.data = data
    }

}