package com.example.examenib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
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

class MainFlora : ComponentActivity() {
    private lateinit var gestorDatos: GestorDatos
    var idParq = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_flora)

        gestorDatos = GestorDatos(this)

        val listView = findViewById<ListView>(R.id.lv_main_flora)
        if(intent.hasExtra("valIDParq")){
            idParq = intent.getIntExtra("valIDParq",0)
        }

        val button = findViewById<Button>(R.id.buttonPadre)
        val nombreParq = gestorDatos.obtenerParque(idParq)!!.nombre
        Log.d("nomPar", "$nombreParq")
        button.text = nombreParq

        actualizarLista()
        val buttonAgregar = findViewById<Button>(R.id.buttonAddFlora)
        buttonAgregar.setOnClickListener {
            val intent = Intent(this, FormularioFlora::class.java)
            intent.putExtra("AddFlora",idParq)
            startActivity(intent)
        }


        if (intent.hasExtra("baseFloraKey")) {
            val baseFloraRecibido = intent.getSerializableExtra("baseFloraKey") as? BaseFlora
            if (baseFloraRecibido != null) {
                // Ahora puedes usar baseFloraRecibido
                gestorDatos.crearFlora(baseFloraRecibido)
                val dat = gestorDatos.obtenerFloraPorIdParque(idParq)
                Log.d("FloraRecibida", "$baseFloraRecibido")
                Log.d("Data", "$dat")
                idParq = baseFloraRecibido.idParque!!
                actualizarLista()
            } else {
                Log.d("nulDatFlora", "Sin Datos")
            }

        }
        if (intent.hasExtra("baseParqueKeyEditar")) {

            val baseFloraRecibido = intent.getSerializableExtra("baseParqueKeyEditar") as? BaseFlora
            Log.d("EditarSer", "$baseFloraRecibido")
            if (baseFloraRecibido != null) {
                gestorDatos.actualizarFlora(baseFloraRecibido)
                idParq = baseFloraRecibido.idParque!!
                actualizarLista()
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
        val item = menu?.findItem(R.id.menu_ver)
        item?.isVisible = false
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_editar -> {
                val intent = Intent(this, FormularioFloraEditar::class.java)
                intent.putExtra("baseMain", posicionItemSeleccionado)
                intent.putExtra("idParq", idParq)
                startActivity(intent)
                Log.d("FinIntent","Finalizo el intent")
                return true
            }
            R.id.menu_eliminar -> {
                val floraId = gestorDatos.obtenerFloraPorIdParque(idParq)[posicionItemSeleccionado].idFlora
                val nombre = gestorDatos.obtenerFloraPorIdParque(idParq)[posicionItemSeleccionado].nombre
                gestorDatos.eliminarFlora(floraId)
                Log.d("eliminado", "Eliminando: $floraId")

                actualizarLista()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }





    private fun actualizarLista(): ListView {
        val arreglo = gestorDatos.obtenerFloraPorIdParque(idParq)
        val listView = findViewById<ListView>(R.id.lv_main_flora)
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

