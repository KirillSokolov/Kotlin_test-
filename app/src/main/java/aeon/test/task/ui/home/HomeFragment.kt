package aeon.test.task.ui.home

import aeon.test.task.databinding.FragmentHomeBinding
import aeon.test.task.ui.BaseFragment
import aeon.test.task.viewmodel.ServiceViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HomeFragment: BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: ServiceViewModel

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = context?.let { setViewModel(it) }!!
    }

}