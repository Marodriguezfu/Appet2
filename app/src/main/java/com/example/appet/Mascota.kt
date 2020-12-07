package com.example.appet

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


abstract class Mascota(propietario: String, nombre: String, sexo: String, raza: String, colorPelo: String, peso : String, birthDate:String) {
    var propietario : String = propietario
    var nombre : String = nombre
    var sexo : String = sexo
    var raza : String = raza
    var colorPelo : String = colorPelo
    var peso: String = peso
    var birthDate : String = birthDate

    /**
     * Guarda la información principal de una mascota en Firebase.
     *
     * @param tipoPet [String] Define el tipo de mascota para almacenar
     * la información en la base de datos.
     */
    fun savePrincipalInfo( tipoPet:String){
        val db = FirebaseFirestore.getInstance()
        val tipo = tipoPet

        val data = hashMapOf("nombreMascota" to nombre, "genero" to sexo, "raza" to raza,"colorPelo" to colorPelo,"peso" to peso+" kg", "Fecha de Nacimiento" to birthDate )
        db.collection("mascota").document(propietario ?: "").collection(tipo).document(nombre?: "").set(
            data , SetOptions.merge()
        )
    }

    abstract fun saveInformation()

}