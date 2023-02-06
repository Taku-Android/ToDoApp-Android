package com.example.android.todoapp_android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class] , version = 1 , exportSchema = false)
abstract class MyDataBase : RoomDatabase() {

    abstract fun tasksDao(): TaskDao

    companion object{

        private var myDataBase:MyDataBase? = null
        private val DATABASE_NAME = "tasksDataBase"

        fun getDatabase(context: Context):MyDataBase{
            if (myDataBase==null){

                myDataBase = Room.databaseBuilder(
                    context,
                    MyDataBase::class.java ,
                    DATABASE_NAME ,


                    ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

            }
            return myDataBase!!
        }

    }


}