package com.gyeong.architecturekotlin.presenter.main.todo_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import com.gyeong.architecturekotlin.databinding.FragmentTodoBinding
import com.gyeong.architecturekotlin.presenter.BaseFragment
import com.gyeong.architecturekotlin.presenter.main.MainIntent
import com.gyeong.architecturekotlin.presenter.main.MainState
import com.gyeong.architecturekotlin.presenter.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodoFragment : BaseFragment<FragmentTodoBinding>() {

    @Inject lateinit var adapter: TodoAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTodoBinding {
        return FragmentTodoBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        adapter.viewModel = viewModel
        requestIntent()
        handleState()
    }

    private fun requestIntent() {
        viewModel.setIntent(MainIntent.GetTodos)
    }

    private fun handleState() {
        viewModel.state.asLiveData().observe(viewLifecycleOwner) { state ->
            when (state) {
                is MainState.Todos.Success -> {
                    adapter.submitList(state.todos)
                }
            }
        }
    }
}