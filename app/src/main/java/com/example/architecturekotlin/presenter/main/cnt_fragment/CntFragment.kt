
package com.example.architecturekotlin.presenter.main.cnt_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import com.example.architecturekotlin.databinding.FragmentCntBinding
import com.example.architecturekotlin.presenter.BaseFragment
import com.example.architecturekotlin.presenter.main.MainIntent
import com.example.architecturekotlin.presenter.main.MainState
import com.example.architecturekotlin.presenter.main.MainViewModel
import com.example.architecturekotlin.util.common.Logger

class CntFragment : BaseFragment<FragmentCntBinding>() {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCntBinding {
        return FragmentCntBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestIntent()
        handleState()
    }

    private fun requestIntent() {
        mainViewModel.setIntent(MainIntent.GetCnt)

        binding.cntMinusBtn.setOnClickListener {
            minusCnt()
        }

        binding.cntPlusBtn.setOnClickListener {
            plusCnt()
        }
    }

    private fun plusCnt() {
        val saveCnt: Int = binding.cntText.text.run {
            this.toString().toInt() + 1
        }
        mainViewModel.setIntent(MainIntent.SaveCnt(saveCnt))
    }

    private fun minusCnt() {
        val saveCnt: Int = binding.cntText.text.run {
            this.toString().toInt() - 1
        }
        mainViewModel.setIntent(MainIntent.SaveCnt(saveCnt))
    }

    private fun handleState() {
        mainViewModel.state.asLiveData().observe(viewLifecycleOwner) { state ->
            when(state) {
                is MainState.GetCnt.Success -> {
                    binding.cntModel = state.cnts
                }

                is MainState.SaveCnt.Success -> {
                    mainViewModel.setIntent(MainIntent.GetCnt)
                }

                is MainState.GetCnt.Fail -> {
                    Logger.e("state. get error : ${state.error.message}")
                }
                is MainState.SaveCnt.Fail -> {
                    Logger.e("state. set error : ${state.error.message}")
                }
            }
        }
    }


}