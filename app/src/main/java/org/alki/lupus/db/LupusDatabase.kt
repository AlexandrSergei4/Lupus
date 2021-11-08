package org.alki.lupus.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.alki.lupus.db.entity.TaskRecord
import java.time.LocalDate


@Dao
interface TaskRecordDao {

    @Query("select * from TaskRecord")
    fun getAll(): LiveData<List<TaskRecord>>

    @Query("select * from TaskRecord where date_start <= :targetDate and date_finish >= :targetDate")
    fun getTaskPerDate(targetDate: Long): Flow<List<TaskRecord>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task: TaskRecord)
}

@Database
    (
    entities = [TaskRecord::class],
    version = 1,
    exportSchema = true
)
abstract class LupusDatabase : RoomDatabase() {
    abstract val taskRecordDao: TaskRecordDao
}


private lateinit var INSTANCE: LupusDatabase

fun getDatabase(context: Context): LupusDatabase {
    synchronized(LupusDatabase::class.java)
    {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                LupusDatabase::class.java, "lupus"
            ).build()
        }
        return INSTANCE
    }

}