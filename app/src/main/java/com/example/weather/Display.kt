package com.example.weather

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Display : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val btnCallNumber: Button = findViewById(R.id.btnCallNumber)
        btnCallNumber.setOnClickListener {

            val intent =  Intent(Intent.ACTION_DEFAULT, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, 1);

        }



        val btnEndQuiz: Button = findViewById(R.id.btnEndQuiz)
        btnEndQuiz.setOnClickListener {

            finish()

        }

    }
}