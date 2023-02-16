package com.example.android.todoapp_android.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int? = null ,
    @ColumnInfo(name = "task_title") val title: String? = null,
    val description: String? = null,
    val date: Long? = null,
    val isDone:Boolean? = false ,
)