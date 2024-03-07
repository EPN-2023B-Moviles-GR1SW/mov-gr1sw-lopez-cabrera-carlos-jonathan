package com.example.examen02.flora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.examen02.R
import com.example.examen02.models.Flora
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateSingleFloraActivity : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextColor: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var editCheckBoxEnExtincion: CheckBox
    private lateinit var buttonActualizar: Button
    private var crudService = CRUDService(this)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_single_flora)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextColor = findViewById(R.id.editTextColor)
        editTextAltura = findViewById(R.id.editTextAlturaFloraUpdate)
        editCheckBoxEnExtincion = findViewById(R.id.checkBoxUpdateEnExtincion)
        buttonActualizar = findViewById(R.id.buttonActualizar)

        val nombreFlora = intent.getStringExtra("nombreFlora")
        cargarDatosFlora(nombreFlora)

        buttonActualizar.setOnClickListener {
            val nuevaFlora = Flora(
                nombre = editTextNombre.text.toString(),
                color = editTextColor.text.toString(),
                altura = editTextAltura.text.toString().toIntOrNull() ?: 0,
                enExtincion = editCheckBoxEnExtincion.isChecked
            )

            crudService.actualizarFlora(nombreFlora ?: "", nuevaFlora)
            finish()
        }
    }

    private fun cargarDatosFlora (nombreFlora: String?) {
        nombreFlora?.let { nombre ->
            CoroutineScope(Dispatchers.Main).launch {
                val sistemas = crudService.leerFlora()
                val sistema = sistemas.find { it.nombre == nombre }
                sistema?.let {
                    editTextNombre.setText(sistema.nombre)
                    editTextColor.setText(sistema.color)
                    editTextAltura.setText(sistema.altura.toString())
                    editCheckBoxEnExtincion.isChecked
                }
            }
        }
    }
}