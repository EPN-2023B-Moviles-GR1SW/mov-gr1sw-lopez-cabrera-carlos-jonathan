package com.example.examen02.flora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.examen02.R
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateFloraActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val crudService = CRUDService(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_flora)

        listView = findViewById(R.id.listViewFlora)
        mostrarSistemas()
    }

    private fun mostrarSistemas() {
        CoroutineScope(Dispatchers.Main).launch {
            val floras = crudService.leerFlora()
            val adapter = ArrayAdapter(this@UpdateFloraActivity, android.R.layout.simple_list_item_1, floras.map { it.nombre })
            listView.adapter = adapter

            listView.setOnItemClickListener { _, _, position, _ ->
                val intent = Intent(this@UpdateFloraActivity, UpdateSingleFloraActivity::class.java).apply {
                    putExtra("flora", floras[position].nombre)
                }
                startActivity(intent)
            }
        }
    }
}