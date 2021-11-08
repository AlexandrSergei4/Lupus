package org.alki.lupus.view.createtask

import androidx.lifecycle.ViewModel
import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.Dispatchers
import org.alki.lupus.domain.TaskService
import org.alki.lupus.domain.model.Task
import java.time.LocalDate
import java.time.LocalDateTime

class CreateTaskViewModel :
    ViewModel() {
    val taskService = TaskService.getInstance();
    var startDate = LocalDate.now().toString()
    var finishDate = LocalDate.now().plusDays(1).toString()
    var name = "Great Task!"
    var description = "Well done is better then well said"

    fun createTask()
    {
        val task = Task(null, LocalDate.parse(startDate), LocalDate.parse(finishDate), name, description)
        taskService.saveTask(task)
    }
}