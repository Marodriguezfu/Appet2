package com.example.appet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegistroVacunasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_vacunas)

        val bundle = intent.extras //RECUPERAR LOS PARAMETROS

        val nombre =  bundle?.getString("nombreMascota")
        println("El patas es: $nombre")



    }
}