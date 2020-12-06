package com.example.appet

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Perro(
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
    var hepatitis : Int = 0
    var hepatitisFecha : String = " "
    var parvovirosis : Int = 0
    var parvovirosisFecha : String = " "
    var leptospirosis : Int = 0
    var leptospirosisFecha : String = " "
    var rabia : Int = 0
    var rabiaFecha : String = " "


    override fun saveInformation() {
        savePrincipalInfo("Perro")
        val db = FirebaseFirestore.getInstance()

        val data = hashMapOf("moquillo" to moquillo, "moquilloFecha" to moquilloFecha, "hepatitis" to hepatitis, "hepatitisFecha" to hepatitisFecha, "parvovirosis" to parvovirosis,
            "parvovirosisFecha" to parvovirosisFecha, "leptospirosis" to leptospirosis, "leptospirosisFecha" to leptospirosisFecha, "rabia" to rabia, "rabiaFecha" to rabiaFecha)
        db.collection("vacunas").document(propietario ?: "").collection("Perro").document(nombre ?: "").set(
            data
        )
    }
}