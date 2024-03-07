package com.example.examen02.models

data class Parque(
    var nombre: String,
    var tipo: String,
    var extencion: Number,
    var esProtegido: Boolean
) {
    override fun toString(): String {
        return """
            Nombre: ${nombre}
            Tipo: ${tipo}
            Altura: ${extencion}
            Es Protegido? ${esProtegido}
        """.trimIndent()
    }
}