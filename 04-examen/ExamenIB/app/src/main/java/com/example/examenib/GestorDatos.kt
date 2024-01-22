package com.example.examenib

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class GestorDatos(private val context: Context) {

    private val gson = Gson()
    private var Parques: MutableList<BaseParque> = mutableListOf()
    private var Flora: MutableList<BaseFlora> = mutableListOf()

    private val nombreArchivoParques = "parque.json"
    private val nombreArchivoFlora = "flora.json"

    init {
        copiarArchivoSiNoExiste(R.raw.parques, nombreArchivoParques)
        copiarArchivoSiNoExiste(R.raw.flora, nombreArchivoFlora)
        leerDatos()
    }

    private fun leerDatos() {
        try {
            val jsonFlora = leerDatosDesdeArchivo(nombreArchivoFlora)

            val typeFlora = object : TypeToken<List<BaseFlora>>() {}.type
            Flora = gson.fromJson(jsonFlora, typeFlora)

            val jsonParques = leerDatosDesdeArchivo(nombreArchivoParques)

            val typeParques = object : TypeToken<List<BaseParque>>() {}.type
            Parques = gson.fromJson(jsonParques, typeParques)
            Log.d("DEBUG", "jsonParques: $jsonParques")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    private fun escribirDatos() {
        try {
            val jsonParques = gson.toJson(Parques)
            escribirDatosEnArchivo(nombreArchivoParques, jsonParques)

            val jsonFlora = gson.toJson(Flora)
            escribirDatosEnArchivo(nombreArchivoFlora, jsonFlora)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun leerDatosDesdeArchivo(nombreArchivo: String): String {
        return try {
            val inputStream = context.openFileInput(nombreArchivo)
            val contenido = inputStream.bufferedReader().use {
                it.readText()
            }
            Log.d("LeerDatos", "Contenido leído de $nombreArchivo: $contenido")
            contenido
        } catch (e: IOException) {
            Log.e("LeerDatos", "Error al leer datos desde $nombreArchivo: ${e.message}")
            ""
        }
    }

    private fun escribirDatosEnArchivo(nombreArchivo: String, datos: String) {
        try {
            context.openFileOutput(nombreArchivo, Context.MODE_PRIVATE).use {
                it.write(datos.toByteArray())
            }
            Log.d("EscribirDatos", "Datos escritos en $nombreArchivo: $datos")
        } catch (e: IOException) {
            Log.e("EscribirDatos", "Error al escribir datos en $nombreArchivo: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun copiarArchivoDesdeRawARuta(rawResourceId: Int, nombreArchivo: String) {
        try {
            val inputStream: InputStream = context.resources.openRawResource(rawResourceId)
            val outputStream: OutputStream = context.openFileOutput(nombreArchivo, Context.MODE_PRIVATE)

            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            Log.d("CopiarArchivo", "Archivo $nombreArchivo copiado exitosamente.")
        } catch (e: IOException) {
            Log.e("CopiarArchivo", "Error al copiar archivo $nombreArchivo: ${e.message}")
        }
    }

    private fun copiarArchivoSiNoExiste(rawResourceId: Int, nombreArchivo: String) {
        val archivo = File(context.filesDir, nombreArchivo)
        if (!archivo.exists()) {
            copiarArchivoDesdeRawARuta(rawResourceId, nombreArchivo)
        }
    }

    fun crearParque(BaseParque: BaseParque) {
        Parques.add(BaseParque)
        escribirDatos()
    }

    fun crearFlora(BaseFlora: BaseFlora) {
        Flora.add(BaseFlora)
        escribirDatos()
    }

    fun obtenerParque(idParque: Int): BaseParque? {
        return Parques.find { it.idParque == idParque }
    }

    fun obtenerFlora(idFlora: Int): BaseFlora? {
        return Flora.find { it.idFlora == idFlora }
    }

    fun actualizarParque(BaseParque: BaseParque) {
        val index = Parques.indexOfFirst { it.idParque == BaseParque.idParque }
        if (index != -1) {
            Parques[index] = BaseParque
            escribirDatos()
        }
    }

    fun actualizarFlora(BaseFlora: BaseFlora) {
        val index = Flora.indexOfFirst { it.idFlora == BaseFlora.idFlora }
        if (index != -1) {
            Flora[index] = BaseFlora
            escribirDatos()
        }
    }

    fun eliminarParque(idParque: Int) {
        Parques.removeIf { it.idParque == idParque }
        escribirDatos()
    }

    fun eliminarFlora(idFlora: Int) {
        Flora.removeIf { it.idFlora == idFlora }
        escribirDatos()
    }

    fun obtenerUltimoIdParque(): Int? {
        return Parques.lastOrNull()?.idParque
    }

    fun obtenerUltimoIdFlora(): Int? {
        return Flora.lastOrNull()?.idFlora
    }

    fun obtenerUltimoIdFloraPorIdParque(idPar: Int): Int {
        val faunaFiltrada = Flora.toList().filter { it.idParque == idPar }
        val ultimoIdFauna: Int? = faunaFiltrada.lastOrNull()?.idFlora
        return ultimoIdFauna ?: 0
    }

    fun obtenerFloraPorIdParque(idParque: Int): List<BaseFlora> {
        return Flora.toList().filter { it.idParque == idParque }
    }

    fun getParques(): List<BaseParque> {
        return Parques.toList()
    }

    fun getFlora(): List<BaseFlora> {
        return Flora.toList()
    }
}