package com.gyeong.architecturekotlin.presenter.main.todo_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
//import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gyeong.architecturekotlin.R
import com.gyeong.architecturekotlin.databinding.ItemTodoBinding
import com.gyeong.architecturekotlin.domain.model.TodoModel
import com.gyeong.architecturekotlin.presenter.main.MainIntent
import com.gyeong.architecturekotlin.presenter.main.MainViewModel
import javax.inject.Inject

class TodoAdapter @Inject constructor(

) : ListAdapter<TodoModel, RecyclerView.ViewHolder>(TodoDiffCallback()) {
    var viewModel: MainViewModel? = null
    lateinit var findNavController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemTodoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_todo,
            parent,
            false
        )
        findNavController = parent.findNavController()

        val holder = TodoHolder(binding)
        binding.holder = holder

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TodoHolder).bind(getItem(position))
    }

    inner class TodoHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TodoModel) {
            binding.apply {
                todo = item
                executePendingBindings()
            }
        }

        fun goToDetailFragment(todoModel: TodoModel) {
            viewModel?.setIntent(MainIntent.SendTodoDetail(todoModel))

            val action = TodoFragmentDirections
                .actionTodoFragmentToTodoDetailFragment()

            findNavController.navigate(action)
        }
    }
}

private class TodoDiffCallback : DiffUtil.ItemCallback<TodoModel>() {
    override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem == newItem
    }
}