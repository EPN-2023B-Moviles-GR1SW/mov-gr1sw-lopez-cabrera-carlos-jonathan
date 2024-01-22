package com.example.examenib

import android.content.Intent
import android.os.Bundle
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

class FormularioFloraEditar : ComponentActivity() {
    private lateinit var gestorDatos: GestorDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_flora)
        gestorDatos = GestorDatos(this)

        val editTextNombreFlora: EditText = findViewById(R.id.editTextNombreFlora)
        val editTextTipoFlora: EditText = findViewById(R.id.editTextTipoFlora)
        val editTextColor: EditText = findViewById(R.id.editTextColor)
        val editTextAltura: EditText = findViewById(R.id.editTextAltura)
        val checkBoxExtincion: CheckBox = findViewById(R.id.checkBoxExtincion)

        val posicion = intent.getIntExtra("baseMain", 0)
        val idParq = intent.getIntExtra("idParq", 0)
        val floraSeleccionada = gestorDatos.obtenerFloraPorIdParque(idParq)[posicion]

        editTextNombreFlora.setText(floraSeleccionada.nombre)
        editTextTipoFlora.setText(floraSeleccionada.tipo)
        editTextColor.setText(floraSeleccionada.color)
        editTextAltura.setText(floraSeleccionada.altura?.toString() ?: "")
        checkBoxExtincion.isChecked = floraSeleccionada.enExtincion

        val buttonGuardarFlora = findViewById<Button>(R.id.buttonGuardarFlora)

        buttonGuardarFlora.setOnClickListener {
            val nombreFlora = editTextNombreFlora.text.toString()
            val tipoFlora = editTextTipoFlora.text.toString()
            val color = editTextColor.text.toString()
            val altura = if (editTextAltura.text.isNotEmpty()) editTextAltura.text.toString().toDouble() else null
            val enExtincion = checkBoxExtincion.isChecked

            val baseFlora = BaseFlora(
                idParque = idParq,
                idFlora = floraSeleccionada.idFlora,
                nombre = nombreFlora,
                tipo = tipoFlora,
                color = color,
                altura = altura,
                enExtincion = enExtincion
            )

            val intent = Intent(this, MainFlora::class.java)
            intent.putExtra("baseParqueKeyEditar", baseFlora)
            startActivity(intent)
            finish()
        }
    }
}

