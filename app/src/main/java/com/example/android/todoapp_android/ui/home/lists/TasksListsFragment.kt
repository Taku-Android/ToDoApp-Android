package com.example.android.todoapp_android.ui.home.lists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.todoapp_android.database.MyDataBase
import com.example.android.todoapp_android.database.Task
import com.example.android.todoapp_android.databinding.FragmentTasksListsBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

class TasksListsFragment : Fragment() {


    lateinit var binding: FragmentTasksListsBinding
    lateinit var tasksAdapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTasksListsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasksAdapter = TasksAdapter(null)
        binding.rv.adapter = tasksAdapter
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->

            if (selected){
                currentDate.set(Calendar.DAY_OF_MONTH , date.day)
                currentDate.set(Calendar.MONTH , date.month-1)
                currentDate.set(Calendar.YEAR , date.year)
                getTasksList()

            }
        }
        binding.calendarView.selectedDate = CalendarDay.today()


    }

    val currentDate = Calendar.getInstance()
    init {
        currentDate.set(Calendar.HOUR , 0)
        currentDate.set(Calendar.MINUTE , 0)
        currentDate.set(Calendar.SECOND , 0)
        currentDate.set(Calendar.MILLISECOND , 0)
    }
    override fun onResume() {
        super.onResume()
        getTasksList()
    }

    fun getTasksList(){
        if (!isAdded){
            return
        }

        val tasks = MyDataBase
            .getDatabase(requireActivity())
            .tasksDao()
            .loadAllByDate(currentDate.timeInMillis)

        tasksAdapter.changeData(tasks)
    }

}