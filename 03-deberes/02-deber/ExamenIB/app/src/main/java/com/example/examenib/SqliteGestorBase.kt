package com.example.examenib

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteGestorBase(
    context: Context?
) : SQLiteOpenHelper(
    context,
    "moviles", // nombre BDD
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaParque = """
               CREATE TABLE PARQUE(
               idParque INTEGER PRIMARY KEY AUTOINCREMENT,
               nombre VARCHAR(50),
               extension REAL,
               tipo VARCHAR(50),
               proteccion INTEGER,
               accesibilidad VARCHAR(50)
               ) 
            """.trimIndent()

        val scriptSQLCrearTablaFlora = """
               CREATE TABLE FLORA(
               idFlora INTEGER PRIMARY KEY AUTOINCREMENT,
               idParque INTEGER,
               nombre VARCHAR(50),
               tipo VARCHAR(50),
               color VARCHAR(50),
               altura REAL,
               enExtincion INTEGER,
               FOREIGN KEY (idParque) REFERENCES PARQUE(idParque)
               ) 
            """.trimIndent()

        db?.execSQL(scriptSQLCrearTablaParque)
        db?.execSQL(scriptSQLCrearTablaFlora)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Manejar la actualización de la base de datos si es necesario
    }

    // Métodos CRUD personalizados para Parque y Flora

    fun crearParque(nombre: String, extension: Float, tipo: String, proteccion: Boolean, accesibilidad: String): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("extension", extension)
        valoresAGuardar.put("tipo", tipo)
        valoresAGuardar.put("proteccion", if (proteccion) 1 else 0)
        valoresAGuardar.put("accesibilidad", accesibilidad)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "PARQUE", // Nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return resultadoGuardar.toInt() != -1
    }

    fun crearFlora(idParque: Int, nombre: String, tipo: String, color: String, altura: Double?, enExtincion: Boolean): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("idParque", idParque)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("tipo", tipo)
        valoresAGuardar.put("color", color)
        valoresAGuardar.put("altura", altura)
        valoresAGuardar.put("enExtincion", if (enExtincion) 1 else 0)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "FLORA", // Nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return resultadoGuardar.toInt() != -1
    }

    fun actualizarParque(
        idParque: Int,
        nombre: String,
        extension: Float,
        tipo: String,
        proteccion: Boolean,
        accesibilidad: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("extension", extension)
        valoresAActualizar.put("tipo", tipo)
        valoresAActualizar.put("proteccion", if (proteccion) 1 else 0)
        valoresAActualizar.put("accesibilidad", accesibilidad)

        val parametrosConsultaActualizar = arrayOf(idParque.toString())
        val resultadoActualizacion = conexionEscritura.update(
            "PARQUE", // Nombre tabla
            valoresAActualizar, // Valores
            "idParque=?", // Consulta Where
            parametrosConsultaActualizar
        )

        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun actualizarFlora(
        idFlora: Int,
        idParque: Int,
        nombre: String,
        tipo: String,
        color: String,
        altura: Double?,
        enExtincion: Boolean
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("idParque", idParque)
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("tipo", tipo)
        valoresAActualizar.put("color", color)
        valoresAActualizar.put("altura", altura)
        valoresAActualizar.put("enExtincion", if (enExtincion) 1 else 0)

        val parametrosConsultaActualizar = arrayOf(idFlora.toString())
        val resultadoActualizacion = conexionEscritura.update(
            "FLORA", // Nombre tabla
            valoresAActualizar, // Valores
            "idFlora=?", // Consulta Where
            parametrosConsultaActualizar
        )

        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun eliminarParque(idParque: Int): Boolean {
        val conexionEscritura = writableDatabase

        val condicionWhere = "idParque=?"
        val parametrosConsultaDelete = arrayOf(idParque.toString())

        val resultadoEliminacion = conexionEscritura.delete(
            "PARQUE", // Nombre tabla
            condicionWhere, // Consulta Where
            parametrosConsultaDelete
        )

        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    fun eliminarFlora(idFlora: Int): Boolean {
        val conexionEscritura = writableDatabase

        val condicionWhere = "idFlora=?"
        val parametrosConsultaDelete = arrayOf(idFlora.toString())

        val resultadoEliminacion = conexionEscritura.delete(
            "FLORA", // Nombre tabla
            condicionWhere, // Consulta Where
            parametrosConsultaDelete
        )

        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    fun obtenerUltimoIdParque(): Int? {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
            SELECT idParque FROM PARQUE ORDER BY idParque DESC LIMIT 1
        """.trimIndent()

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)
        val ultimoIdParque: Int? = if (resultadoConsultaLectura.moveToFirst()) {
            resultadoConsultaLectura.getInt(0)
        } else {
            null
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return ultimoIdParque
    }

    fun obtenerUltimoIdFloraPorIdParque(idParque: Int): Int {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
            SELECT idFlora FROM FLORA WHERE idParque = ? ORDER BY idFlora DESC LIMIT 1
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(idParque.toString())

        val resultadoConsultaLectura =
            baseDatosLectura.rawQuery(scriptConsultaLectura, parametrosConsultaLectura)

        val ultimoIdFlora: Int = if (resultadoConsultaLectura.moveToFirst()) {
            resultadoConsultaLectura.getInt(0)
        } else {
            0
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return ultimoIdFlora
    }

    fun getFloraPorIdParque(idParque: Int): List<BaseFlora> {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
            SELECT * FROM FLORA WHERE idParque = ?
        """.trimIndent()

        val parametrosConsultaLectura = arrayOf(idParque.toString())

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, parametrosConsultaLectura)

        val floraBases = mutableListOf<BaseFlora>()

        while (resultadoConsultaLectura.moveToNext()) {
            val idFlora = resultadoConsultaLectura.getInt(0)
            val idParque = resultadoConsultaLectura.getInt(1)
            val nombre = resultadoConsultaLectura.getString(2)
            val tipo = resultadoConsultaLectura.getString(3)
            val color = resultadoConsultaLectura.getString(4)
            val altura = resultadoConsultaLectura.getDouble(5)
            val enExtincion = resultadoConsultaLectura.getInt(6) == 1

            val floraBase = BaseFlora(idParque, idFlora, nombre, tipo, color, altura, enExtincion)
            floraBases.add(floraBase)
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return floraBases.toList()
    }

    fun getParques(): List<BaseParque> {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
        SELECT * FROM PARQUE
    """.trimIndent()

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)

        val parqueBases = mutableListOf<BaseParque>()

        while (resultadoConsultaLectura.moveToNext()) {
            val idParque = resultadoConsultaLectura.getInt(0)
            val nombre = resultadoConsultaLectura.getString(1)
            val extension = resultadoConsultaLectura.getFloat(2)
            val tipo = resultadoConsultaLectura.getString(3)
            val proteccion = resultadoConsultaLectura.getInt(4) == 1
            val accesibilidad = resultadoConsultaLectura.getString(5)

            val parqueBase = BaseParque(idParque, nombre, extension, tipo, proteccion, accesibilidad)
            parqueBases.add(parqueBase)
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return parqueBases.toList()
    }

    fun obtenerParque(idParque: Int): BaseParque? {
        val baseDatosLectura = readableDatabase

        val scriptConsultaLectura = """
        SELECT * FROM PARQUE WHERE idParque = ?
    """.trimIndent()

        val parametrosConsultaLectura = arrayOf(idParque.toString())

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura
        )

        var parqueBase: BaseParque? = null

        if (resultadoConsultaLectura.moveToFirst()) {
            val nombre = resultadoConsultaLectura.getString(1)
            val extension = resultadoConsultaLectura.getFloat(2)
            val tipo = resultadoConsultaLectura.getString(3)
            val proteccion = resultadoConsultaLectura.getInt(4) == 1
            val accesibilidad = resultadoConsultaLectura.getString(5)

            parqueBase = BaseParque(idParque, nombre, extension, tipo, proteccion, accesibilidad)
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return parqueBase
    }

}