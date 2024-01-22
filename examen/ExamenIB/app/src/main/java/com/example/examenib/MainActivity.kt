package com.example.examenib

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
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
import com.google.android.material.snackbar.Snackbar

class MainActivity : ComponentActivity() {
    private lateinit var gestorDatos: GestorDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        gestorDatos = GestorDatos(this)

        val listView = findViewById<ListView>(R.id.lv_main)
        actualizarLista()

        val buttonAgregar = findViewById<Button>(R.id.buttonAddParque)
        buttonAgregar.setOnClickListener {
            val intent = Intent(this, FormularioParque::class.java)
            startActivity(intent)
        }

        if (intent.hasExtra("baseParqueKey")) {
            val baseParqueRecibido = intent.getSerializableExtra("baseParqueKey") as? BaseParque
            if (baseParqueRecibido != null) {
                gestorDatos.crearParque(baseParqueRecibido)
                Log.d("Crear", "$baseParqueRecibido")
            }else{
                Log.d("nulDatParque", "Sin Datos")
            }


        }
        if (intent.hasExtra("baseParqueKeyEditar")) {

            val baseParqueRecibidoEd = intent.getSerializableExtra("baseParqueKeyEditar") as? BaseParque
            Log.d("EditarSer", "$baseParqueRecibidoEd")
            if (baseParqueRecibidoEd != null) {
                gestorDatos.actualizarParque(baseParqueRecibidoEd)
            }else{
                Log.d("nulDatParqueEdi", "Sin Datos")
            }

        }
        registerForContextMenu(actualizarLista())
    }


    var posicionItemSeleccionado = 0
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        Log.d("IDen", "${info}")
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_ver -> {
                val ParqID = gestorDatos.getParques()[posicionItemSeleccionado].idParque
                val intent = Intent(this, MainFlora::class.java)
                intent.putExtra("valIDParq",ParqID)
                startActivity(intent)
                return true
            }
            R.id.menu_editar -> {
                val intent = Intent(this, FormularioParqueEditar::class.java)
                intent.putExtra("baseMain", posicionItemSeleccionado)
                startActivity(intent)
                Log.d("FinIntent","Finalizo el intent")
                return true
            }
            R.id.menu_eliminar -> {
                val parqueId = gestorDatos.getParques()[posicionItemSeleccionado].idParque
                val nombre = gestorDatos.getParques()[posicionItemSeleccionado].nombre
                if (parqueId != null) {
                    gestorDatos.eliminarParque(parqueId)
                    Log.d("eliminado", "Eliminando: $parqueId")
                    actualizarLista()
                } else {
                    Log.d("NO eliminado", "No Eliminando: $parqueId")
                }
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }





    private fun actualizarLista():ListView {
        val arreglo = gestorDatos.getParques()
        val listView = findViewById<ListView>(R.id.lv_main)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        return listView
    }
}
