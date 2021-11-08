package org.alki.lupus.view.createtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.alki.lupus.databinding.FragmentCreateTaskBinding
import org.alki.lupus.domain.TaskService
import org.alki.lupus.domain.model.Task
import org.alki.lupus.toLocalDate
import java.time.LocalDate

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateTaskFragment : Fragment() {
    private val viewModel: CreateTaskViewModel by viewModels()
    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!
    private val tasksService = TaskService.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startDate = CreateTaskFragmentArgs.fromBundle(requireArguments()).startDate
        binding.vm = viewModel
        viewModel.startDate.value = (startDate.toLocalDate().toString())
        viewModel.finishDate.value =(startDate.toLocalDate().plusDays(1).toString())
        binding.buttonCreate.setOnClickListener {
            tasksService.saveTask(
                Task(
                    null,
                    LocalDate.parse(viewModel.startDate.value),
                    LocalDate.parse(viewModel.finishDate.value),
                    viewModel.name.value?:"",
                    viewModel.description.value?:""
                )
            )
            findNavController().popBackStack()
        }
    }

}