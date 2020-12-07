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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_vacunas)



        val bundle = intent.extras //RECUPERAR LOS PARAMETROS

        val nombre =  bundle?.getString("nombreMascota")
        println("El patas es: $nombre")

        dateCumpleaños.setOnClickListener{
            showDatePickerDialog(dateCumpleaños)
           // etDate.setText(diaObtenido.toString()+"/"+mesObtenido.toString()+"/"+añoObtenido.toString())
        }
        dateVacuna1.setOnClickListener{
            showDatePickerDialog(dateVacuna1)
           // pickDateBtn.setText (diaObtenido.toString()+"/"+mesObtenido.toString()+"/"+añoObtenido.toString())
        }
        dateVacuna2.setOnClickListener{
            showDatePickerDialog(dateVacuna2)
            // pickDateBtn.setText (diaObtenido.toString()+"/"+mesObtenido.toString()+"/"+añoObtenido.toString())
        }
        dateVacuna3.setOnClickListener{
            showDatePickerDialog(dateVacuna3)
            // pickDateBtn.setText (diaObtenido.toString()+"/"+mesObtenido.toString()+"/"+añoObtenido.toString())
        }
        dateVacuna4.setOnClickListener{
            showDatePickerDialog(dateVacuna4)
            // pickDateBtn.setText (diaObtenido.toString()+"/"+mesObtenido.toString()+"/"+añoObtenido.toString())
        }
        dateVacuna5.setOnClickListener{
            showDatePickerDialog(dateVacuna5)
            // pickDateBtn.setText (diaObtenido.toString()+"/"+mesObtenido.toString()+"/"+añoObtenido.toString())
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

        parent.setText(diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString())

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



}