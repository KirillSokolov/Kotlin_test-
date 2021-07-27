package aeon.test.task.viewmodel

import aeon.test.task.restapi.BaseService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory constructor(private val baseService: BaseService): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ServiceViewModel::class.java)) {
            ServiceViewModel(baseService) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }


}