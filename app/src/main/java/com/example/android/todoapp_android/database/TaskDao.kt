package com.example.android.todoapp_android.database

import androidx.room.*


@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAll(): List<Task>

    @Query("SELECT * FROM tasks WHERE date :date")
    fun loadAllByDate(date: Int?): List<Task>


    /*
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User
    */



    @Update
    fun updateTask(task: Task)

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun delete(task: Task)


}