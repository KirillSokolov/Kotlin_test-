package aeon.test.task.viewmodel

import aeon.test.task.model.Model
import aeon.test.task.model.ImageModel
import aeon.test.task.restapi.BaseService
import aeon.test.task.restapi.ImageService
import aeon.test.task.restapi.response.ImageData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class ServiceViewModel constructor(private val serviceClass: BaseService) : ViewModel() {

    var job: Job? = null
    var model: Model = ImageModel()
    val liveData :LiveData<MutableList<ImageData>> = getData()
    val listImages = MutableLiveData<MutableList<ImageData>>()

    private fun getData(): LiveData<MutableList<ImageData>> {
        return listImages
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getImages() {
        if (serviceClass is ImageService) {
            job = CoroutineScope(Dispatchers.IO).plus(exceptionHandler).launch {
                val response = serviceClass.images()
                withContext(Dispatchers.Main) {
                  //  if (response.isSuccessful) {
                      //  model.convertFromResponse(response.body()?.getData()!!)
                     //   model.create()
                     //   listImages.postValue(response.body()?.getData()!! as MutableList<ImageData>?)
                  //  } else {
                  //      onError("Error : ${response.message()} ")
                  //  }
                }
            }
        }
    }

    private fun onError(message: String) {
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}