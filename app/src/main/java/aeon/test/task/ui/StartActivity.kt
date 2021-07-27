package aeon.test.task.ui

import aeon.test.task.BaseApplication
import aeon.test.task.R
import aeon.test.task.ui.home.HomeFragment
import aeon.test.task.ui.login.LoginFragment
import android.os.Bundle

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            if(BaseApplication.getPreferenceHelper().getLong("user_id", 0L) == 0L)
                addFragment(LoginFragment.newInstance(), "login", supportFragmentManager)
            else
                addFragment(HomeFragment.newInstance(), "home", supportFragmentManager)
        }
    }
}