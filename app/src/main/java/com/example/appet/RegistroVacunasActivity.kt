package com.example.appet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_registro_vacunas.*
import java.text.SimpleDateFormat
import java.util.*

class RegistroVacunasActivity : AppCompatActivity() {

    private var diaObtenido = 0
    private var mesObtenido = 0
    private var añoObtenido = 0
    private var fechaDeNacimiento = ""
    private var guardado = false

    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
    val sdf = SimpleDateFormat("yyyy/MM/dd")
    var formatedDate = sdf.format(date)
    var tipoMascotaP = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_vacunas)



        val bundle = intent.extras //RECUPERAR LOS PARAMETROS

        val nombre =  bundle?.getString("nombreMascota")
        val tipoMascota =  bundle?.getString("tipoMascota")
        tipoMascotaP = tipoMascota ?: ""
        if(tipoMascota == "Perro") {
            dateVacuna1.setText("Moquillo")
            dateVacuna2.setText("Hepatitis")
            dateVacuna3.setText("Parvovirosis")
            dateVacuna4.setText("Leptospirosis")
            dateVacuna5.setText("Rabia")
        }
        else{
            dateVacuna1.setText("Leucemia")
            dateVacuna2.setText("Rinotraqueitis")
            dateVacuna3.setText("Panleucopenia")
            dateVacuna4.setText("Calcivirosis")
            dateVacuna5.setText("Rabia")
        }

        println("El patas es: $nombre")

        dateCumpleaños.setOnClickListener{
            showDatePickerDialog(dateCumpleaños)
        }
        dateVacuna1.setOnClickListener{
            it.setBackgroundColor(0xFF4CAF50.toInt())
            showDatePickerDialog(dateVacuna1)
        }
        dateVacuna2.setOnClickListener{
            it.setBackgroundColor(0xFF4CAF50.toInt())
            showDatePickerDialog(dateVacuna2)
        }
        dateVacuna3.setOnClickListener{
            it.setBackgroundColor(0xFF4CAF50.toInt())
            showDatePickerDialog(dateVacuna3)
        }
        dateVacuna4.setOnClickListener{
            it.setBackgroundColor(0xFF4CAF50.toInt())
            showDatePickerDialog(dateVacuna4)
        }
        dateVacuna5.setOnClickListener{
            it.setBackgroundColor(0xFF4CAF50.toInt())
            showDatePickerDialog(dateVacuna5)
        }
    }

    private fun showDatePickerDialog(parent: EditText) {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year, parent) }
        datePicker.show(supportFragmentManager, "datePicker")
    }
    private fun onDateSelected(day: Int, month: Int, year: Int, parent: EditText) {
        diaObtenido = day
        mesObtenido = month
        añoObtenido = year

             if(parent == dateVacuna1 &&  tipoMascotaP == "Perro") {dateVacuna1.setText("Moquillo")}
        else if(parent == dateVacuna2 &&  tipoMascotaP == "Perro") {dateVacuna2.setText("Hepatitis")}
        else if(parent == dateVacuna3 &&  tipoMascotaP == "Perro") {dateVacuna3.setText("Parvovirosis")}
        else if(parent == dateVacuna4 &&  tipoMascotaP == "Perro") {dateVacuna4.setText("Leptospirosis")}
        else if(parent == dateVacuna5 &&  tipoMascotaP == "Perro") {dateVacuna5.setText("Rabia")}
        else if(parent == dateVacuna1 &&  tipoMascotaP == "Gato" ) {dateVacuna1.setText("Leucemia")}
        else if(parent == dateVacuna2 &&  tipoMascotaP == "Gato" ) {dateVacuna2.setText("Rinotraqueitis")}
        else if(parent == dateVacuna3 &&  tipoMascotaP == "Gato" ) {dateVacuna3.setText("Panleucopenia")}
        else if(parent == dateVacuna4 &&  tipoMascotaP == "Gato" ) {dateVacuna4.setText("Calcivirosis")}
        else if(parent == dateVacuna5 &&  tipoMascotaP == "Gato" ) {dateVacuna5.setText("Rabia")}
        else if(parent == dateCumpleaños){dateCumpleaños.setText("")}

        parent.setText(parent.text.toString() + "\n" + diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString())

        if(parent==dateCumpleaños){
            var sdf = formatedDate
            fechaDeNacimiento = sdf;
            //Año//MES//DIA
            println("Tu perro" + sdf)
            /*
            var strs = sdf.split("/")
            println("Tomado fecha año " + strs[0])
            println("Tomado fecha mes " + strs[1])
            println("Tomado fecha dia " + strs[2])

            println("Tu perro tiene " + (strs[0].toInt()-añoObtenido) + "años")
            println("Tu perro tiene " + (strs[1].toInt()-(mesObtenido+1)) + "meses")
            println("Tu perro tiene " + (strs[2].toInt()-diaObtenido) + "dias")*/
        }

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}