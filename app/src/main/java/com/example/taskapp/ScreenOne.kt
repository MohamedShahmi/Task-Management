package com.example.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ScreenOne : AppCompatActivity() {
    private lateinit var logoLoad: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_one)
        // Initialize medlogoLoad
        logoLoad = findViewById(R.id.medlogoLod)

        logoLoad.alpha = 0f
        logoLoad.animate().setDuration(5000).alpha(1f).withEndAction {
            val i = Intent(this, ScreenTwo::class.java) // Replace NextActivity with the actual name of your next activity
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}