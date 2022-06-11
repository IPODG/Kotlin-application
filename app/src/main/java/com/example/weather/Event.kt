package com.example.weather

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Event : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_event)

    val btnStartQuiz: Button = findViewById(R.id.btnStartQuiz)
    btnStartQuiz.setOnClickListener {
        val quizIntent: Intent = Intent(this, Display::class.java)

        startActivity(quizIntent)
    }
    }
}