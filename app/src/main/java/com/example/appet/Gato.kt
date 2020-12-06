package com.example.appet

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Gato(
    propietario: String,
    nombre: String,
    sexo: String,
    raza: String,
    colorPelo: String,
    peso: Double,
    edad: Int
) : Mascota(propietario, nombre, sexo, raza, colorPelo, peso, edad) {

    var moquillo : Int = 0
    var moquilloFecha : String = " "
    var tripleFelina : Int = 0
    var tripleFelinaFecha : String = " "
    var leucemia : Int = 0
    var leucemiaFecha : String = " "
    var rabia : Int = 0
    var rabiaFecha : String = " "

    override fun saveInformation() {
        savePrincipalInfo("Gato")
        val db = FirebaseFirestore.getInstance()

        val data = hashMapOf("moquillo" to moquillo, "moquilloFecha" to moquilloFecha, "tripleFelina" to tripleFelina, "tripleFelinaFecha" to tripleFelinaFecha, "leucemia" to leucemia,
            "leucemiaFecha" to leucemiaFecha, "rabia" to rabia, "rabiaFecha" to rabiaFecha)
        db.collection("vacunas").document(propietario ?: "").collection("Gato").document(nombre ?: "").set(
            data
        )
    }
}