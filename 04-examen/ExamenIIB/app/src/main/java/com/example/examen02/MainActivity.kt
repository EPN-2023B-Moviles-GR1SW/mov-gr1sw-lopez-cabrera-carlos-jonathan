package com.example.examen02

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen02.parques.ParquesActivity
import com.example.examen02.flora.FloraActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnFlora = findViewById<Button>(R.id.btnFlora)
        val btnParques = findViewById<Button>(R.id.btnParques)

        btnFlora.setOnClickListener {
            val intent = Intent(this, FloraActivity::class.java)
            startActivity(intent)
        }
        btnParques.setOnClickListener {
            val intent = Intent(this, ParquesActivity::class.java)
            startActivity(intent)
        }
    }
}