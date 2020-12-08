package com.shivram.todolisthilt.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shivram.todolisthilt.local.dao.TaskDao
import com.shivram.todolisthilt.util.SortOrder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

class TaskViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
) : ViewModel(){
    val searchQuery = MutableStateFlow("")

    val sortOrder = MutableStateFlow(SortOrder.BY_DATE)

    val hideCompleted = MutableStateFlow(false)

//    this can be combine multiple flows into the single flows
    private val tasksFlow = combine(
        searchQuery,
        sortOrder,
        hideCompleted
    ) { query, sortOrder, hideCompleted ->
        Triple(query, sortOrder, hideCompleted)
    }.flatMapLatest {
        (query, sortOrder, hideCompleted) ->
        taskDao.getTasks(query, sortOrder, hideCompleted)
    }


    val tasks = tasksFlow.asLiveData()
}