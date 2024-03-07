package com.example.examen02.service

import android.content.Context
import com.example.examen02.models.Parque
import com.example.examen02.models.Flora
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class CRUDService(private val context: Context) {
    private val db: FirebaseFirestore = Firebase.firestore

    fun addFlora(flora: Flora) {
        db.collection("flora").add(flora)
    }

    suspend fun leerFlora(): List<Flora> {
        return try {
            db.collection("flora").get().await().documents.mapNotNull { snapshot ->
                snapshot.data?.let { data ->
                    Flora(
                        nombre = data["nombre"] as? String ?: "",
                        color = data["color"] as? String ?: "",
                        enExtincion = data["enExtincion"] as? Boolean ?: false,
                        altura = data["altura"] as? Double ?: 0,
                        parques = mutableListOf()
                    )
                }
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    fun actualizarFlora(nombre: String, nuevaFlora: Flora) {
        db.collection("flora").whereEqualTo("nombre", nombre).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("flora").document(document.id).set(nuevaFlora)
                }
            }
    }

    fun eliminarFlora(nombre: String) {
        db.collection("flora").whereEqualTo("nombre", nombre).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("flora").document(document.id).delete()
                }
            }
    }

    fun addParque(parque: Parque) {
        db.collection("parques").add(parque)
    }

    suspend fun leerParques(): List<Parque> {
        return try {
            db.collection("parques").get().await().documents.mapNotNull { snapshot ->
                snapshot.data?.let { data ->
                    val nombre = data["nombre"] as? String ?: ""
                    val tipo = data["tipo"] as? String ?: ""
                    val altura = data["extension"] as? Double ?: 0
                    val esProtegido = data["esProtegido"] as? Boolean ?: false

                    Parque(nombre, tipo, altura, esProtegido)
                }
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    fun actualizarParque(nombre: String, nuevoParque: Parque) {
        db.collection("parques").whereEqualTo("nombre", nombre).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("parques").document(document.id).set(nuevoParque)
                }
            }
    }

    fun eliminarPlaneta(nombre: String) {
        db.collection("parques").whereEqualTo("nombre", nombre).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("parques").document(document.id).delete()
                }
            }
    }

}