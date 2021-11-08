package org.alki.lupus.view.calendartasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import org.alki.lupus.databinding.FragmentCalendarTasksBinding
import org.alki.lupus.toLong
import timber.log.Timber


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

        binding.calendarView.setOnClickListener{
        }
        binding.createTaskButton.setOnClickListener{
            findNavController().navigate(CalendarTasksFragmentDirections.actionCalendarTasksFragmentToCreateTaskFragment())
        }
    }
    override fun invalidate() {
        Timber.d("Invalidate")
        withState(viewModel)
        {
           binding.calendarView.date = it.selectedDate.toLong()
        }
    }

}