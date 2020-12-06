package com.example.appet

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_registro_vacunas.*
import java.util.*

class RegistroVacunasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_vacunas)

        val bundle = intent.extras //RECUPERAR LOS PARAMETROS

        val nombre =  bundle?.getString("nombreMascota")
        println("El patas es: $nombre")

        etDate.setOnClickListener{showDatePickerDialog()}

    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        etDate.setText(day.toString()+"/"+month.toString()+"/"+year.toString())
    }
}