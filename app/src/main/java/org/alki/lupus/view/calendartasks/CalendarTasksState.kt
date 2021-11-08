package org.alki.lupus.view.calendartasks

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import org.alki.lupus.domain.model.Task
import java.time.LocalDate

data class CalendarTasksState(
    val selectedDate: LocalDate = LocalDate.now(),
    val taskLists: Async<List<Task>> = Uninitialized
): MavericksState