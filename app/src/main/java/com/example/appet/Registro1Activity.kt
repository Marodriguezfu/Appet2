package com.example.appet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_registro1.*

class Registro1Activity : AppCompatActivity() , AdapterView.OnItemSelectedListener{

    private var spinner: Spinner? = null //creamos una variable para el Spinner
    private var arrayAdapter:ArrayAdapter<String> ? = null //Creamos un ArrayAdapter, se utiliza a la hora de pasar el arreglo al Spinner
    private var result:TextView ? = null //Esta variable debe ser eliminada, solo se utiliza para mostrar que si se esta seleccionando el departamento
    private lateinit var departamento: String //En esta variable se almacenara mas adelante, el departamento seleccionado por el usuario

    private val itemList = arrayDep() //Creamos el Array de departamentos de Colombia y lo guardamos en la constante itemList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro1)

        spinner = findViewById(R.id.spinnerDepto) //Accedemos al Spinner que hicimos en el archivo xml con la ayuda de su id
        result = findViewById(R.id.resultText)//Esta linea debe ser eliminada, solo se utiliza para mostrar que si se esta seleccionando el departamento
        arrayAdapter = ArrayAdapter(applicationContext , android.R.layout.simple_spinner_item , itemList) //se llama el array que ya creamos y se adapta
        spinner?.adapter = arrayAdapter //Se envia el array que ya hemos adatado al spinner
        spinner?.onItemSelectedListener = this //Se define el spinner

        buttonNext.setOnClickListener {

            //Llamamos la actividad Registro 2
            showRegistro2(departamento)

        }


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(applicationContext,"Nothing Selected", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        departamento = parent?.getItemAtPosition(position) as String
        result?.text = departamento//jhj
        //Toast.makeText(applicationContext , "$items", Toast.LENGTH_LONG).show()
    }

    //Creamos una funcion para crear el array de departamentos de Colombia
    private fun arrayDep(): ArrayList<String> {
        val departamentos = arrayListOf<String>() //Creamos el array de departamentos de Colombia
        departamentos.addAll(listOf("Amazonas","Antioquia",
            "Arauca","Atlántico","Bogotá","Bolívar","Boyacá",
            "Caldas","Caquetá","Casanare","Cauca","Cesar","Chocó",
            "Córdoba","Cundinamarca","Guainía","Guaviare","Huila",
            "La Guajira","Magdalena","Meta","Nariño","Norte de Santander",
            "Putumayo","Quindío","Risaralda","San Andrés y Providencia",
            "Santander","Sucre","Tolima","Valle del Cauca","Vaupés","Vichada"))
        return departamentos
    }



    private fun showRegistro2(depto: String) {
        // IR A HOME
        val registro2Intent = Intent(this, Registro2Activity::class.java).apply {    //CREAR UN INTENT A LA NUEVA PANTALLA Y NAVEGAR A LA NUEVA PANTALLA
            //PARAMETROS A PASAR
            putExtra("departamento", depto) //Pasamos el departamento a la siguiente aplicacion
        }
        startActivity(registro2Intent) //LA NAVEGACION A LA NUEVA PANTALLA
    }

}