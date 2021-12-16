package aeon.test.task.ui

import aeon.test.task.ui.login.ImagesFragment
import android.os.Bundle

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(ImagesFragment.newInstance(), "Images", supportFragmentManager)
    }
}