package org.alki.lupus.view.calendartasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import org.alki.lupus.databinding.FragmentCalendarTasksBinding
import org.alki.lupus.toLong
import org.alki.lupus.view.common.TasksListAdapter
import timber.log.Timber
import java.time.LocalDate


class CalendarTasksFragment: Fragment(), MavericksView{
    private val viewModel: CalendarTasksViewModel by fragmentViewModel()
    private var _binding: FragmentCalendarTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCalendarTasksBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarView.setOnDateChangeListener { cv, y, m, d ->
            viewModel.selectCalendarDate(LocalDate.of(y, m+1, d))
        }
        binding.tasksList.adapter = TasksListAdapter()
        binding.createTaskButton.setOnClickListener{
            //TODO get date from viewModel
            withState(viewModel)
            {
                findNavController().navigate(CalendarTasksFragmentDirections.actionCalendarTasksFragmentToCreateTaskFragment(it.selectedDate.toLong()))
            }
        }
    }

    override fun invalidate() {
        Timber.d("Invalidate")
        withState(viewModel)
        {
            binding.calendarView.date = it.selectedDate.toLong()
            (binding.tasksList.adapter as TasksListAdapter).submitList(it.taskLists.invoke())
        }
    }

}