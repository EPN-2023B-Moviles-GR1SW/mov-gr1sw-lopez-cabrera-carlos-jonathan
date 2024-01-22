package com.example.examenib

import java.io.Serializable

data class BaseFlora(
    val idParque:Int?,
    val idFlora:Int,
    val nombre: String,
    val tipo: String,
    val color: String,
    val altura: Double?,
    val enExtincion: Boolean
) : Serializable {
    override fun toString(): String {
        return """
            |id: $idParque
            |idFlora: $idFlora
            |Nombre: $nombre
            |Tipo: $tipo
            |Color: $color
            |Altura: $altura
            |En Extinción: $enExtincion
        """.trimMargin()
    }
}