package com.example.mamamboga.activity

import com.example.mamamboga.R
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        object : CountDownTimer(1500, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java) //HomeActivity::class.java //LoginActivity::class.java
                startActivity(intent)
                finish()
            }
        }.start()
    }
}