package org.alki.lupus.domain

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import org.alki.lupus.db.entity.TaskRecord
import org.alki.lupus.domain.model.Task
import java.time.LocalDateTime


interface TaskService {
    companion object{
        var INSTANCE: TaskService? = null

        fun getInstance(): TaskService {
            return INSTANCE!!;
        }
    }


    fun getTaskPerDate(dateTime: LocalDateTime): Flow<List<Task>>
    fun saveTask(task:Task)

}