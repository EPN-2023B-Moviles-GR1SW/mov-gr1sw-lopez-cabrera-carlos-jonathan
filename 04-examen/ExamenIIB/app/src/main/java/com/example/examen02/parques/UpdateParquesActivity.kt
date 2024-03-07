package com.example.examen02.parques

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.Parque
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateParquesActivity : AppCompatActivity() {
    private lateinit var spinnerParques: Spinner
    private lateinit var editTextNombre: EditText
    private lateinit var editTextTipo: EditText
    private lateinit var editTextExtension: EditText
    private lateinit var checkboxEsProtegido: CheckBox
    private lateinit var botonActualizar: Button

    private lateinit var crudService: CRUDService
    private var parques: List<Parque> = listOf()
    private var parqueSeleccionado: Parque? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_parque)

        spinnerParques = findViewById(R.id.spinnerPlanetasUpdate)
        editTextNombre = findViewById(R.id.editTextNombrePlanetaUpdate)
        editTextTipo = findViewById(R.id.editTextDescripcionPlanetaUpdate)
        editTextExtension = findViewById(R.id.editTextAlturaUpdate)
        checkboxEsProtegido = findViewById(R.id.checkBoxFloraEnExtinsionUpdate)
        botonActualizar = findViewById(R.id.botonActualizarPlaneta)

        crudService = CRUDService(this)

        cargarParques()
        configurarSpinnerParques()
    }

    private fun cargarParques() {
        CoroutineScope(Dispatchers.Main).launch {
            parques = crudService.leerParques()
            val adapter = ArrayAdapter(
                this@UpdateParquesActivity,
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
                cargarDatosParque(parqueSeleccionado!!)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                parqueSeleccionado = null
            }
        }
    }

    private fun cargarDatosParque(parque: Parque) {
        editTextNombre.setText(parque.nombre)
        editTextTipo.setText(parque.tipo)
        editTextExtension.setText(parque.extencion.toString())
        checkboxEsProtegido.isChecked = parque.esProtegido
    }

    fun onActualizarPlanetaClick(view: View) {
        parqueSeleccionado?.let {
            val nombre = editTextNombre.text.toString()
            val tipo = editTextTipo.text.toString()
            val extension = editTextExtension.text.toString().toIntOrNull() ?: 1
            val esProtegido = checkboxEsProtegido.isChecked

            val parqueActualizado = Parque(
                nombre, tipo, extension, esProtegido
            )

            try {
                crudService.actualizarParque(it.nombre, parqueActualizado)
                Toast.makeText(this, "Parque actualizado con éxito!", Toast.LENGTH_SHORT).show()
                cargarParques()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al actualizar el Parque", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Por favor selecciona un Parque", Toast.LENGTH_SHORT).show()
    }
}