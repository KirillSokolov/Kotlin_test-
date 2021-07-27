package aeon.test.task.ui.login

import aeon.test.task.BuildConfig
import aeon.test.task.R
import aeon.test.task.databinding.FragmentLoginBinding
import aeon.test.task.ui.BaseFragment
import aeon.test.task.ui.home.HomeFragment
import aeon.test.task.viewmodel.ServiceViewModel
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: ServiceViewModel

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = context?.let { setViewModel(it) }!!
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
        binding.btnRegister.setOnClickListener {
            viewModel.register(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        binding.tvVersion.text = getString(
            R.string.version,
            context?.let { getAppVersion(it) }, BuildConfig.VERSION_CODE
        )

        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            getParentActivity().get()?.replaceFragment(HomeFragment.newInstance(), "home", childFragmentManager)
        })
    }


    private fun getAppVersion(context: Context): String {
        var version = ""
        try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            version = pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return version
    }
}