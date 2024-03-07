package com.example.examen02.parques

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.examen02.R
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReadParquesActivity : AppCompatActivity() {
    private lateinit var listViewParques : ListView
    private lateinit var crudService: CRUDService
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_parque)

        listViewParques = findViewById(R.id.listViewParques)
        crudService = CRUDService(this)

        CargarParques()
    }

    private fun CargarParques() {
        CoroutineScope(Dispatchers.Main).launch {
            val parques = crudService.leerParques()

            val adapter = ArrayAdapter(this@ReadParquesActivity, android.R.layout.simple_list_item_1, parques.map { "${it.nombre} - ${it.tipo} - extension: ${it.extencion.toString()}km - protegido: ${it.esProtegido}  " })
            listViewParques.adapter = adapter
        }
    }
}