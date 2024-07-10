package com.example.taskapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskapp.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM TASK ORDER BY id DESC")
    fun getAllTask(): LiveData<List<Task>>

    @Query("SELECT * FROM TASK WHERE taskTitle LIKE :query OR taskDesc LIKE :query")
    fun searchTask(query: String?): LiveData<List<Task>>
}