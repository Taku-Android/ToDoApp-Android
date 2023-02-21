package com.example.android.todoapp_android.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.android.todoapp_android.R
import com.example.android.todoapp_android.databinding.ActivityHomeBinding
import com.example.android.todoapp_android.databinding.FragmentTasksListsBinding
import com.example.android.todoapp_android.ui.addtask.AddTaskBottomSheet
import com.example.android.todoapp_android.ui.addtask.OnDismissListener
import com.example.android.todoapp_android.ui.home.lists.TasksListsFragment
import com.example.android.todoapp_android.ui.home.settings.SettingsFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    val tasksListsFragment = TasksListsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setCurrentFragment(tasksListsFragment)

        binding.bottomNavView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_list->setCurrentFragment(tasksListsFragment)
                R.id.ic_settings->setCurrentFragment(SettingsFragment())
            }
            true
        }

        binding.addBtn.setOnClickListener {
            showAddTaskBottomSheet()
        }

    }

    private fun showAddTaskBottomSheet() {

        val addTaskBottomSheet = AddTaskBottomSheet()
        addTaskBottomSheet.onDismissListener = OnDismissListener {
            tasksListsFragment.getTasksList()
        }
        addTaskBottomSheet.show(supportFragmentManager , null)

    }


    private fun setCurrentFragment(firstFragment: Fragment) {

        supportFragmentManager.beginTransaction().
        replace(R.id.fragment_container, firstFragment).commit()

    }

}