package com.example.examen02.flora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen02.R

class FloraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flora)

        val btnAddSistema = findViewById<Button>(R.id.btnAddSistema)
        val btnReadSistema = findViewById<Button>(R.id.btnReadSistemas)
        val btnDeleteSistema = findViewById<Button>(R.id.btnDeleteSistema)
        val btnUpdateSistema = findViewById<Button>(R.id.btnUpdateSistema)

        btnAddSistema.setOnClickListener {
            val intent = Intent(this, AddFloraActivity::class.java)
            startActivity(intent)
        }
        btnReadSistema.setOnClickListener {
            val intent = Intent(this, ReadFloraActivity::class.java)
            startActivity(intent)
        }
        btnDeleteSistema.setOnClickListener {
            val intent = Intent(this, DeleteFloraActivity::class.java)
            startActivity(intent)
        }
        btnUpdateSistema.setOnClickListener {
            val intent = Intent(this, UpdateFloraActivity::class.java)
            startActivity(intent)
        }
    }
}