package aeon.test.task.viewmodel

import aeon.test.task.BaseApplication
import aeon.test.task.model.Model
import aeon.test.task.model.UserModel
import aeon.test.task.restapi.BaseService
import aeon.test.task.restapi.LoginService
import aeon.test.task.restapi.PaymentService
import aeon.test.task.restapi.request.LoginRequest
import aeon.test.task.restapi.response.UserData
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class ServiceViewModel constructor(private val serviceClass: BaseService) : ViewModel() {

    var job: Job? = null
    var model: Model = UserModel()
    val liveData = MutableLiveData<UserData>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun login(login: String, password: String) {
        if (serviceClass is LoginService) {
            val loginRequest = LoginRequest(login, password)
            job = CoroutineScope(Dispatchers.IO).plus(exceptionHandler).launch {
                val response = serviceClass.login(loginRequest)
                withContext(Dispatchers.IO) {
                    model.convertFromResponse(response.body()?.getData()!!)
                    model.create()
                    liveData.postValue(response.body()?.getData()!!)
                }
            }
        }
    }

    fun register(login: String, password: String) {
        if (serviceClass is LoginService) {
            val loginRequest = LoginRequest(login, password)
            job = CoroutineScope(Dispatchers.IO).plus(exceptionHandler).launch {
                val response = serviceClass.register(loginRequest);
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {

                    } else {
                    }
                }
            }
        }
    }

    fun logout() {

    }

    fun getPayments() {
        if (serviceClass is PaymentService) {
            job = CoroutineScope(Dispatchers.IO).plus(exceptionHandler).launch {
                val response = serviceClass.payments();
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                    } else {
                        onError("Error : ${response.message()} ")
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        //  errorMessage.value = message
        //  loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}