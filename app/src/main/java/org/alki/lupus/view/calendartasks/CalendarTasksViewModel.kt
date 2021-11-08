package org.alki.lupus.view.calendartasks

import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.Dispatchers
import org.alki.lupus.domain.TaskService
import org.alki.lupus.domain.model.Task
import java.time.LocalDate
import java.time.LocalDateTime

class CalendarTasksViewModel(initialState: CalendarTasksState) :
    MavericksViewModel<CalendarTasksState>(initialState) {

    fun selectCalendarDate(selectedDate: LocalDate) {
        setState {
            copy(selectedDate = selectedDate)
        }
        TaskService.getInstance().getTaskPerDate(selectedDate.atStartOfDay())
            .execute(Dispatchers.IO){
                copy(taskLists = it)
            }
    }

    fun createTaks(selectedDate: LocalDate) {
        val task = Task(null, LocalDateTime.now(), selectedDate.atTime(10,10), "SampleTask","Description")
        TaskService.getInstance().saveTask(task)
        selectCalendarDate(selectedDate)
    }
}