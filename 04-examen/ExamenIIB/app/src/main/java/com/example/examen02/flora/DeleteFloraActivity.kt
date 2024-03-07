package com.example.examen02.flora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen02.R
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteFloraActivity : AppCompatActivity() {

    private lateinit var  recyclerView: RecyclerView
    private lateinit var adaptador: FloraEliminarAdapter
    private lateinit var crudService: CRUDService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_flora)

        recyclerView = findViewById(R.id.floraEliminarView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        crudService = CRUDService(this)

        actualizarLista()
    }

    private fun eliminarSistema(nombre: String) {
        crudService.eliminarFlora(nombre)
        actualizarLista()
    }

    private fun actualizarLista() {
        CoroutineScope(Dispatchers.Main).launch {
            val sistemas = crudService.leerFlora()
            adaptador = FloraEliminarAdapter(sistemas, this@DeleteFloraActivity::eliminarSistema)
            recyclerView.adapter = adaptador
        }
    }
}