package com.example.taskapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskapp.model.Task


@Database(entities = [Task::class], version = 1)
    abstract class TaskDatabase:RoomDatabase() {
     abstract fun getTaskDao():TaskDao

     companion object{
         @Volatile
         private var instance:TaskDatabase?=null
         private val LOCK = Any()

         operator fun invoke(context: Context) = instance ?:
         synchronized(LOCK){
             instance ?:
             createDatabase(context).also{
                 instance = it
             }
         }
         private fun createDatabase(context: Context)=
             Room.databaseBuilder(
                 context.applicationContext,
                 TaskDatabase::class.java,
                 "task_db"
             ).build()
     }
}