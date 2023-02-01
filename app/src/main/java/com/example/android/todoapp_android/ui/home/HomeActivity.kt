package com.example.android.todoapp_android.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.android.todoapp_android.R
import com.example.android.todoapp_android.databinding.ActivityHomeBinding
import com.example.android.todoapp_android.databinding.FragmentTasksListsBinding
import com.example.android.todoapp_android.ui.home.lists.TasksListsFragment
import com.example.android.todoapp_android.ui.home.settings.SettingsFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setCurrentFragment(TasksListsFragment())

        binding.bottomNavView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_list->setCurrentFragment(TasksListsFragment())
                R.id.ic_settings->setCurrentFragment(SettingsFragment())
            }
            true
        }

    }

    private fun setCurrentFragment(firstFragment: Fragment) {

        supportFragmentManager.beginTransaction().
        replace(R.id.fragment_container, firstFragment).commit()

    }

}