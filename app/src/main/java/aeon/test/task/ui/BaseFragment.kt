package aeon.test.task.ui

import aeon.test.task.restapi.ImageService
import aeon.test.task.restapi.LoginService
import aeon.test.task.restapi.ServiceGenerator
import aeon.test.task.viewmodel.ServiceViewModel
import aeon.test.task.viewmodel.ViewModelFactory
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.lang.ref.WeakReference

open class BaseFragment : Fragment() {

    private var parentActivity: WeakReference<BaseActivity>? = null

    protected fun setViewModel(context: Context): ServiceViewModel {
        return ViewModelProvider(
            this, ViewModelFactory(
                ServiceGenerator().createService(
                    ImageService::class.java, context
                )
            )
        ).get(ServiceViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentActivity = WeakReference(activity as BaseActivity?)
    }

    protected fun getParentActivity(): WeakReference<BaseActivity>{
        return parentActivity!!
    }

}