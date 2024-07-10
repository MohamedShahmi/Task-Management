package com.example.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ScreenTwo : AppCompatActivity() {
    private lateinit var TwoBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_two)
        // Initialize btn
        TwoBtn = findViewById(R.id.one_next)
        // Set OnClickListener for the button
        TwoBtn.setOnClickListener {
            val intent = Intent(this, ScreenThree::class.java)
            startActivity(intent)
        }
    }
}