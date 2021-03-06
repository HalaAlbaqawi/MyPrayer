package com.example.prayerproject.main

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.prayerproject.R
import com.example.prayerproject.Service.MyService
import com.example.prayerproject.repositories.ApiServiceDuaaRepository
import com.example.prayerproject.repositories.ApiServiceQiblaRepository
import com.example.prayerproject.repositories.ApiServiceRepository

lateinit var handler: Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startStopService()

        //initializing the companion object on repositories
        ApiServiceRepository.init(this)
        ApiServiceDuaaRepository.init(this)
        ApiServiceQiblaRepository.init(this)
        window.navigationBarColor =
            this.resources.getColor(R.color.black) // this is for the navigation bar color of the android system
        handler = Handler()
        handler.postDelayed({

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)


    }

    private fun startStopService() {


            startService(Intent(this, MyService::class.java))

    }

    private fun isMyServiceRunning(mClass: Class<MyService>): Boolean {

        val manager: ActivityManager = getSystemService(
            Context.ACTIVITY_SERVICE
        ) as ActivityManager
        for (service: ActivityManager.RunningServiceInfo in
        manager.getRunningServices(Integer.MAX_VALUE)) {

            if (mClass.name.equals(service.service.className)) {
                return true

            }
        }
        return false
    }

}