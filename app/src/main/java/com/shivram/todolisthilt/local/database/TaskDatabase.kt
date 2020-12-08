package com.shivram.todolisthilt.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.shivram.todolisthilt.di.ApplicationScope
import com.shivram.todolisthilt.local.dao.TaskDao
import com.shivram.todolisthilt.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Android Programming"))
                dao.insert(Task("Full Stack Developer"))
                dao.insert(Task("Mern Developer", important = true))
                dao.insert(Task("Web Developer", completed = true))
                dao.insert(Task("Ui/Ux Designer"))
                dao.insert(Task("Backend Developer", completed = true))
                dao.insert(Task("Front end Developer"))
                dao.insert(Task("Search Engine Optimization"))
            }
        }
    }
}