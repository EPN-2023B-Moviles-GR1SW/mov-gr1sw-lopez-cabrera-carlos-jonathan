package com.example.examen02.models

data class Flora(
    var nombre: String,
    var color: String,
    var altura: Number,
    var enExtincion: Boolean,
    var parques: MutableList<Parque> = mutableListOf()
) {
    override fun toString(): String {
        return """
            Nombre: ${nombre}
            Color: ${color}
            Altura: ${altura}
            En Extincion: ${enExtincion}
        """.trimIndent()
    }

    fun agregarPlaneta(parque: Parque) {
        parques.add(parque)
    }
}