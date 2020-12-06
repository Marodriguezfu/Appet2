package com.example.appet

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

/**
 * Un perro de mascota.
 *
 * Esta clase hereda de la clase abstracta Mascota y permite organizar
 * la información correspondiente a un perro, y almacenarla en Firebase.
 *
 * @property propietario el correo del propietario.
 * @constructor Crea un perro con la información indispensable dentro
 * de la app.
 */
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

    /**
     * Envia los atributos de un obbjeto de la clase perro
     * a una base de datos de Firebase.
     */
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