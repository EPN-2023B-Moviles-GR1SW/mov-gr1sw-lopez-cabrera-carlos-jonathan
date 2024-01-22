package com.example.examenib

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.examenib.ui.theme.ExamenIBTheme

class FormularioParqueEditar : ComponentActivity() {
    private lateinit var gestorDatos: GestorDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_parque)

        gestorDatos = GestorDatos(this)

        val editTextAccesibilidad: EditText = findViewById(R.id.editTextAccesibilidad)
        val editTextExtension: EditText = findViewById(R.id.editTextExtension)
        val editTextNombre: EditText = findViewById(R.id.editTextNombreParque)
        val checkBoxProteccion: CheckBox = findViewById(R.id.checkBoxProteccion)
        val editTextTipo: EditText =findViewById(R.id.editTextTipo)

        val posicion = intent.getIntExtra("baseMain",0)
        val parqueSeleccionado = gestorDatos.getParques()[posicion]
        val idNext = parqueSeleccionado.idParque
        val nombre = parqueSeleccionado.nombre
        val extension = parqueSeleccionado.extension
        val tipo = parqueSeleccionado.tipo
        val proteccion = parqueSeleccionado.proteccion
        val accesibilidad = parqueSeleccionado.accesibilidad


        editTextNombre.setText(nombre)
        editTextExtension.setText(extension.toString())
        editTextTipo.setText(tipo)
        checkBoxProteccion.isChecked = proteccion
        editTextAccesibilidad.setText(accesibilidad)

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
            Log.d("Edit", "${baseParque.idParque}")
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("baseParqueKeyEditar", baseParque)
            startActivity(intent)
            finish()
        }


    }
}
