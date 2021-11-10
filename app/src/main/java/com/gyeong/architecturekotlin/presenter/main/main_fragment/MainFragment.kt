package com.gyeong.architecturekotlin.presenter.main.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gyeong.architecturekotlin.databinding.FragmentMainBinding
import com.gyeong.architecturekotlin.presenter.BaseFragment
import com.gyeong.architecturekotlin.presenter.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.presenter = this
    }

    fun goCntFragment() {
        val action = MainFragmentDirections.actionMainFragmentToCntFragment()
        findNavController().navigate(action)
    }

    fun goTodoFragment() {
        val action = MainFragmentDirections.actionMainFragmentToTodoFragment()
        findNavController().navigate(action)
    }

    fun goWalkFragment() {
        val action = MainFragmentDirections.actionMainFragmentToWalkFragment()
        findNavController().navigate(action)
    }

    fun goBarcodeFragment() {
        val action = MainFragmentDirections.actionMainFragmentToBarcodeFragment()
        findNavController().navigate(action)
    }
}
