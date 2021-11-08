package org.alki.lupus.view.createtask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.alki.lupus.domain.TaskService
import java.time.LocalDate

class CreateTaskViewModel :
    ViewModel() {
    val taskService = TaskService.getInstance();
    val startDate = MutableLiveData<String>().apply {value = LocalDate.now().toString() }
    val finishDate =MutableLiveData<String>().apply {value = LocalDate.now().plusDays(1).toString() }
    val name = MutableLiveData<String>().apply { value = "Great Task!"  }
    val description = MutableLiveData<String>().apply {value = "Well done is better then well said"}

}