package aeon.test.task.ui.login

import aeon.test.task.BuildConfig
import aeon.test.task.R
import aeon.test.task.databinding.FragmentImagesBinding
import aeon.test.task.databinding.FragmentLoginBinding
import aeon.test.task.ui.BaseFragment
import aeon.test.task.viewmodel.ServiceViewModel
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ravikoradiya.liveadapter.LiveAdapter
import okhttp3.internal.http2.Header

class ImagesFragment : BaseFragment() {

    private lateinit var binding: FragmentImagesBinding
    lateinit var viewModel: ServiceViewModel
    //private var rvImages: RecyclerView
    companion object {
        fun newInstance(): ImagesFragment {
            return ImagesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = context?.let { setViewModel(it) }!!
        viewModel.getImages()
        viewModel.listImages.observe(viewLifecycleOwner, {
            LiveAdapter(viewModel.liveData, viewLifecycleOwner)
                .map<Header>(R.layout.item_header)
                .map<Point>(R.layout.item_point)
                .into(binding.rvImages)
        })
    }


}