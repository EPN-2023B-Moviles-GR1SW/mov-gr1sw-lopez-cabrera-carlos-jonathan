package com.example.examenib

import java.io.Serializable

data class BaseParque(
    val idParque: Int?,
    val nombre: String,
    val extension: Float,
    val tipo: String,
    val proteccion: Boolean,
    val accesibilidad: String
) : Serializable {
    override fun toString(): String {
        return """
            |idParque: $idParque
            |Nombre: $nombre
            |Extensión: $extension
            |Tipo: $tipo
            |Protección: $proteccion
            |Accesibilidad: $accesibilidad
        """.trimMargin()
    }
}