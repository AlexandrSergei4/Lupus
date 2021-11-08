package org.alki.lupus.view.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.alki.lupus.databinding.TaskViewHolderBinding
import org.alki.lupus.domain.model.Task

class TasksListAdapter() :
    ListAdapter<Task, TasksListAdapter.TaskViewHolder>(TaskDiffCallback()) {

    class TaskViewHolder(private var binding: TaskViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(task: Task) {
            binding.taskName.text = task.name
            binding.taskDescr.text = task.description
            binding.taskFinishDate.text = "${task.date_finish.dayOfMonth} / ${task.date_finish.monthValue}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


class TaskDiffCallback:DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}