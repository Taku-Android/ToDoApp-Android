package com.example.android.todoapp_android.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.android.todoapp_android.R
import com.example.android.todoapp_android.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable { //This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(i)
            // close this activity
            finish()
        }, 2000)



    }
}