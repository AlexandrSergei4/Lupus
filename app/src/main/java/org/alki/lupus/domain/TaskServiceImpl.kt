package org.alki.lupus.domain

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alki.lupus.db.LupusDatabase
import org.alki.lupus.db.entity.TaskRecord
import org.alki.lupus.db.getDatabase
import org.alki.lupus.domain.model.Task
import org.alki.lupus.toTask
import org.alki.lupus.toTaskRecord
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class TaskServiceImpl private constructor(context: Context) : TaskService {

    companion object {
        fun create(context: Context) {
            synchronized(TaskService::class.java)
            {
                if (TaskService.INSTANCE == null) {
                    TaskService.INSTANCE = TaskServiceImpl(context)
                }
            }
        }

    }

    private val db: LupusDatabase = getDatabase(context)

    override fun getTaskPerDate(dateTime: LocalDateTime): Flow<List<Task>> {
        var targetDate = Timestamp.from(dateTime.truncatedTo(ChronoUnit.DAYS).atZone(ZoneId.of("UTC")).toInstant()).time
        return db.taskRecordDao.getTaskPerDate(targetDate).map {
            it.map(TaskRecord::toTask)
        }
    }

    override fun saveTask(task: Task) {
        db.taskRecordDao.insert(task.toTaskRecord())
    }
}