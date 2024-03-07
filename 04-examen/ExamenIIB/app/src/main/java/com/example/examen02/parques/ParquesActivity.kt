package com.example.examen02.parques

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen02.R

class ParquesActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planetas)

        val btnAddParque = findViewById<Button>(R.id.btnAddParque)
        val btnReadParque = findViewById<Button>(R.id.btnReadParque)
        val btnDeleteParque = findViewById<Button>(R.id.btnDeleteParque)
        val btnUpdateParque = findViewById<Button>(R.id.btnUpdateParque)

        btnAddParque.setOnClickListener {
            val intent = Intent(this, AddParqueActivity::class.java)
            startActivity(intent)
        }
        btnReadParque.setOnClickListener {
            val intent = Intent(this, ReadParquesActivity::class.java)
            startActivity(intent)
        }
        btnDeleteParque.setOnClickListener {
            val intent = Intent(this, DeleteParqueActivity::class.java)
            startActivity(intent)
        }
        btnUpdateParque.setOnClickListener {
            val intent = Intent(this, UpdateParquesActivity::class.java)
            startActivity(intent)
        }
    }
}