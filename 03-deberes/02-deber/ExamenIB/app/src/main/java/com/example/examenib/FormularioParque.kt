package com.example.examenib

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class FormularioParque : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var gestorDatos: GestorDatos
    var idNext = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_parque)
        gestorDatos = GestorDatos(this)

        val editTextAccesibilidad: EditText = findViewById(R.id.editTextAccesibilidad)
        val editTextExtension: EditText = findViewById(R.id.editTextExtension)
        val editTextNombre: EditText = findViewById(R.id.editTextNombreParque)
        val checkBoxProteccion: CheckBox = findViewById(R.id.checkBoxProteccion)
        val editTextTipo: EditText =findViewById(R.id.editTextTipo)
        // Crear un objeto BaseParque con los datos ingresados por el usuario

        val idP = EBaseDeDatos.tablaEntrenador!!.obtenerUltimoIdParque()
        idNext = idP?.plus(1)!!


        val buttonEnviarDatos = findViewById<Button>(R.id.buttonGuardarParque)

        buttonEnviarDatos.setOnClickListener {
            val baseParque = BaseParque(
                idParque = idNext,
                nombre = editTextNombre.text.toString(),
                extension = editTextExtension.text.toString().toFloat(),
                tipo = editTextTipo.text.toString(),
                proteccion = checkBoxProteccion.isChecked,
                accesibilidad = editTextAccesibilidad.text.toString()
            )

            Log.d("New", "$baseParque")
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("baseParqueKey", baseParque)
            startActivity(intent)
            finish()
        }


    }
}