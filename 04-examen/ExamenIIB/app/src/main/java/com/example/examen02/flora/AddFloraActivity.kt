package com.example.examen02.flora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.Flora
import com.example.examen02.service.CRUDService
import java.lang.Exception

class AddFloraActivity : AppCompatActivity() {
    private lateinit var checkBoxEnExtincion: CheckBox

    private lateinit var crudService: CRUDService

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_flora)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombreFlora)
        val editTextColor = findViewById<EditText>(R.id.editTextColorFlora)
        val editTextAltura = findViewById<EditText>(R.id.editTextAlturaFlora)
        checkBoxEnExtincion = findViewById(R.id.checkBoxTieneVida)
        val botonAgregar = findViewById<Button>(R.id.buttonAgregarFlora)

        botonAgregar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val color = editTextColor.text.toString()
            val altura = editTextAltura.text.toString().toIntOrNull() ?: 0
            val enExtincion = checkBoxEnExtincion.isChecked
            val nuevaFlora = Flora(
                nombre, color, altura, enExtincion
            )

            try {
                val crudService = CRUDService(this)
                crudService.addFlora(nuevaFlora)
                Toast.makeText(this, "Flora creada con éxito", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al crear la Flora", Toast.LENGTH_SHORT).show()
            }
        }

    }
}