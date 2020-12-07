package com.example.appet

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

/**
 * Un gato de mascota.
 *
 * Esta clase hereda de la clase abstracta Mascota y permite organizar
 * la información correspondiente a un gato, y almacenarla en Firebase.
 *
 * @property propietario el correo del propietario.
 * @constructor Crea un perro con la información indispensable dentro
 * de la app.
 */
class Gato(
    propietario: String,
    nombre: String,
    sexo: String,
    raza: String,
    colorPelo: String,
    peso: String,
    birthDate: String
) : Mascota(propietario, nombre, sexo, raza, colorPelo, peso, birthDate) {

    var leucemia : String = " "
    var leucemiaFecha : String = " "
    var rinotraqueitis : String = " "
    var rinotraqueitisFecha : String = " "
    var panleucopenia : String = " "
    var panleucopeniaFecha : String = " "
    var calcivirosis : String = " "
    var calcivirosisFecha : String = " "
    var rabia : String = " "
    var rabiaFecha : String = " "

    /**
     * Envia los atributos de un obbjeto de la clase gato
     * a una base de datos de Firebase
     */
    override fun saveInformation() {
        savePrincipalInfo("Gato")
        val db = FirebaseFirestore.getInstance()

        val data = hashMapOf("rinotraqueitis" to rinotraqueitis, "rinotraqueitisFecha" to rinotraqueitisFecha, "panleucopenia" to panleucopenia, "panleucopeniaFecha" to panleucopeniaFecha, "leucemia" to leucemia,
            "leucemiaFecha" to leucemiaFecha,"calcivirosis" to calcivirosis, "calcivirosisFecha" to calcivirosisFecha, "rabia" to rabia, "rabiaFecha" to rabiaFecha)
        db.collection("vacunas").document(propietario ?: "").collection("Gato").document(nombre ?: "").set(
            data
        )
    }
}