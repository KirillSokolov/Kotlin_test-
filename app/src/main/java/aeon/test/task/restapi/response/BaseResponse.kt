package aeon.test.task.restapi.response

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName


class BaseResponse<T> {

    @Nullable
    @SerializedName("response")
    private var data: T? = null


    @Nullable
    fun getData(): T {
        return data!!
    }

    fun setData(data: T?) {
        this.data = data
    }

}