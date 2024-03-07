package com.example.examen02.parques

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.Parque
import com.example.examen02.models.Flora
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddParqueActivity : AppCompatActivity() {
    private lateinit var spinnerFloras: Spinner
    private lateinit var editTextNombre: EditText
    private lateinit var editTextTipo: EditText
    private lateinit var editTextExtencion: EditText
    private lateinit var checkboxEsProtegido: CheckBox

    private lateinit var crudService: CRUDService
    private var floraList: List<Flora> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_parque)

        spinnerFloras = findViewById(R.id.spinnerSistemas)
        editTextNombre = findViewById(R.id.editTextNombreParque)
        editTextTipo = findViewById(R.id.editTextTipoParque)
        editTextExtencion = findViewById(R.id.editTextExtensionParque)
        checkboxEsProtegido = findViewById(R.id.checkBoxTieneVida)

        crudService = CRUDService(this)

        cargarFloraList()
        configurarSpinnerFlora()
    }

    private fun cargarFloraList() {
        CoroutineScope(Dispatchers.Main).launch {
            floraList = crudService.leerFlora()
            val adapter = ArrayAdapter(
                this@AddParqueActivity,
                android.R.layout.simple_spinner_item,
                floraList.map { it.nombre })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFloras.adapter = adapter
        }
    }

    private fun configurarSpinnerFlora() {
        spinnerFloras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    fun onAgregarParqueClick(view: View) {
        val floraSeleccionada = floraList[spinnerFloras.selectedItemPosition]
        val nombre = editTextNombre.text.toString()
        val tipo = editTextTipo.text.toString()
        val extencion = editTextExtencion.text.toString().toIntOrNull() ?: 1
        val esProtegido = checkboxEsProtegido.isChecked

        val parque = Parque(nombre, tipo, extencion, esProtegido)
        floraSeleccionada.agregarPlaneta(parque)
        crudService.actualizarFlora(floraSeleccionada.nombre, floraSeleccionada)
        crudService.addParque(parque)

        Toast.makeText(this, "Agregado flora ${floraSeleccionada.nombre}", Toast.LENGTH_SHORT).show()
    }

}