package com.gyeong.architecturekotlin.presenter.main.todo_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import com.gyeong.architecturekotlin.databinding.FragmentTodoDetailBinding
import com.gyeong.architecturekotlin.presenter.BaseFragment
import com.gyeong.architecturekotlin.presenter.main.MainIntent
import com.gyeong.architecturekotlin.presenter.main.MainState
import com.gyeong.architecturekotlin.presenter.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoDetailFragment : BaseFragment<FragmentTodoDetailBinding>() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTodoDetailBinding {
        return FragmentTodoDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestIntent()
        handleState()
    }

    private fun requestIntent() {
        viewModel.setIntent(MainIntent.GetTodoDetail(
            viewModel.todo.value?.id ?: 0
        ))
    }

    private fun handleState() {
        viewModel.state.asLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is MainState.TodoDetail.Success -> {
                    binding.todoDetail.text = state.todo.title
                }
                is MainState.TodoDetail.Fail -> {
                    Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}