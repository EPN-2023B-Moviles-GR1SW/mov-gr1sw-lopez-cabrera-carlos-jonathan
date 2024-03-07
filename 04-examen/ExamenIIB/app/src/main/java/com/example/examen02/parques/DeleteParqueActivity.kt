package com.example.examen02.parques

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.Parque
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DeleteParqueActivity : AppCompatActivity() {
    private lateinit var spinnerParques: Spinner
    private lateinit var botonEliminar: Button
    private lateinit var crudService: CRUDService
    private var parques: List<Parque> = listOf()
    private var parqueSeleccionado: Parque? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_parque)

        spinnerParques = findViewById(R.id.spinnerPlanetas)
        botonEliminar = findViewById(R.id.botonEliminar)
        crudService = CRUDService(this)

        cargarParques()
        configurarSpinnerParques()
    }

    private fun cargarParques() {
        CoroutineScope(Dispatchers.Main).launch {
            parques = crudService.leerParques()
            val adapter = ArrayAdapter(
                this@DeleteParqueActivity,
                android.R.layout.simple_spinner_item,
                parques.map { it.nombre })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerParques.adapter = adapter
        }
    }

    private fun configurarSpinnerParques() {
        spinnerParques.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                parqueSeleccionado = parques[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                parqueSeleccionado = null
            }
        }
    }

    fun onEliminarPlanetaClick(view: View) {
        parqueSeleccionado?.let {
            try {
                crudService.eliminarPlaneta(it.nombre)
                Toast.makeText(this, "Parque eliminado con éxito", Toast.LENGTH_SHORT).show()
                cargarParques()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al eliminar el Parque", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Por favor selecciona un Parque", Toast.LENGTH_SHORT).show()
    }
}