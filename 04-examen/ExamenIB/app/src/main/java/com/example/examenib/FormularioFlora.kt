package com.example.examenib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class FormularioFlora : AppCompatActivity() {
    private lateinit var gestorDatos: GestorDatos
    var idParq = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_flora)
        gestorDatos = GestorDatos(this)
        idParq = intent.getIntExtra("AddFlora",0)

        val editTextNombreFlora: EditText = findViewById(R.id.editTextNombreFlora)
        val editTextTipoFlora: EditText = findViewById(R.id.editTextTipoFlora)
        val editTextColor: EditText = findViewById(R.id.editTextColor)
        val editTextAltura: EditText = findViewById(R.id.editTextAltura)
        val checkBoxExtincion: CheckBox = findViewById(R.id.checkBoxExtincion)

        val id = gestorDatos.obtenerUltimoIdFloraPorIdParque(idParq)
        val idFlora = id.plus(1)

        val buttonGuardarFlora = findViewById<Button>(R.id.buttonGuardarFlora)

        buttonGuardarFlora.setOnClickListener {
            val nombreFlora = editTextNombreFlora.text.toString()
            val tipoFlora = editTextTipoFlora.text.toString()
            val color = editTextColor.text.toString()
            val altura = if (editTextAltura.text.isNotEmpty()) editTextAltura.text.toString().toDouble() else null
            val enExtincion = checkBoxExtincion.isChecked


            val baseFlora = BaseFlora(
                idParque = idParq,
                idFlora = idFlora,
                nombre = nombreFlora,
                tipo = tipoFlora,
                color = color,
                altura = altura,
                enExtincion = enExtincion
            )

            val intent = Intent(this, MainFlora::class.java)
            intent.putExtra("baseFloraKey", baseFlora)
            startActivity(intent)
            finish()
        }

    }
}