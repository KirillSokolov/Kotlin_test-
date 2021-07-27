package aeon.test.task.ui

import aeon.test.task.BuildConfig
import aeon.test.task.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

open class BaseActivity : AppCompatActivity() {

    var resId: Int = R.id.content

    override fun onCreate(savedInstanceState: Bundle?) {
        if (BuildConfig.DEBUG) {
            setContentView(R.layout.activity_start)
        } else {
            setContentView(R.layout.activity_start)
        }
        super.onCreate(savedInstanceState)
    }

    fun replaceFragment(
        fragment: Fragment?,
        tag: String?,
        fragmentManager: FragmentManager
    ) {
        try {
            if (fragment != null) {
                try {
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.slide_in_right, R.anim.slide_out_left,
                            R.anim.slide_in_left, R.anim.slide_out_right
                        )
                        .addToBackStack(tag)
                        .replace(resId, fragment, tag)
                        .commit()
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addFragment(fragment: Fragment?, tag: String?, fragmentManager: FragmentManager) {
        if (fragment != null) {
            try {
                fragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.left_to_right_sliding_1,
                        R.anim.left_to_right_sliding
                    )
                    .addToBackStack(tag)
                    .add(resId, fragment, tag)
                    .commit()
            } catch (e: java.lang.IllegalStateException) {
                e.printStackTrace()
            }
        }
    }
}