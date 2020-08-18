package com.example.appet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro1.*
import kotlinx.android.synthetic.main.activity_registro2.*
import android.widget.ArrayAdapter as ArrayAdapter1
import kotlinx.android.synthetic.main.activity_registro1.buttonNext as buttonNext1

class Registro2Activity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var spinner: Spinner? = null //creamos una variable para el Spinner
    private var arrayAdapter: ArrayAdapter1<String>? = null //Creamos un ArrayAdapter, se utiliza a la hora de pasar el arreglo al Spinner
    private var result:TextView ? = null //Esta variable debe ser eliminada, solo se utiliza para mostrar que si se esta seleccionando el departamento
    private var depto: TextView? = null //Esta variable se utiliza para definir el texto que ira en en la zona de departamento, su valor depende de la actividad anterior
    private lateinit var ciudad: String //Esta variable se utiliza para almacenar el valor de la ciudad seleccionada por el usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro2)

        //Setup
        val bundle = intent.extras //RECUPERAR LOS PARAMETROS
        val departamento =  bundle?.getString("departamento")//Se recupera el valor del departamento, proveniente de la actividad Registro1


        depto = findViewById(R.id.deptoSeleccion) //Se accede al id del textView en donde se almacenara el departamento del usuario
        depto?.text = departamento //Se le asigna el valor del departamento, al textView del xml

        val itemList = arrayCity(departamento) //Creamos el array de las ciudades del departamento, el argumento viene del activity anterior

        spinner = findViewById(R.id.spinnerCiudad) //Accedemos al Spinner que hicimos en el archivo xml con la ayuda de su id
        result = findViewById(R.id.resultText) //Esta linea debe ser eliminada, solo se utiliza para mostrar que si se esta seleccionanda la ciudad
        arrayAdapter = ArrayAdapter1(applicationContext , android.R.layout.simple_spinner_item , itemList) //se llama el array que ya creamos y se adapta
        spinner?.adapter = arrayAdapter //Se envia el array que ya hemos adatado al spinner
        spinner?.onItemSelectedListener = this //Se define el spinner

        buttonBack.setOnClickListener {

            //Llamamos la actividad Registro 1

            val registro1Intent = Intent(this, Registro1Activity::class.java)
            startActivity(registro1Intent)

        }

        buttonNext.setOnClickListener {

            //Llamamos la actividad Registro 1

            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)

        }
    }


    //Creamos una funcion para crear el array de las ciudades de cada departamentos de Colombia
    private fun arrayCity(departamento:String?): ArrayList<String> {
        val ciudades = arrayListOf<String>() //Creamos el array de ciudades del departamento de Colombia

        if(departamento.equals("Amazonas")){
            ciudades.addAll(listOf("Leticia","Puerto Nariño"))
        }
        /*departamentos.addAll(listOf("Amazonas","Antioquia",
            "Arauca","Atlántico","Bogotá","Bolívar","Boyacá",
            "Caldas","Caquetá","Casanare","Cauca","Cesar","Chocó",
            "Córdoba","Cundinamarca","Guainía","Guaviare","Huila",
            "La Guajira","Magdalena","Meta","Nariño","Norte de Santander",
            "Putumayo","Quindío","Risaralda","San Andrés y Providencia",
            "Santander","Sucre","Tolima","Valle del Cauca","Vaupés","Vichada"))*/
        return ciudades
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(applicationContext,"Nothing Selected", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        ciudad = parent?.getItemAtPosition(position) as String
        result?.text = ciudad//jhj
        //Toast.makeText(applicationContext , "$items", Toast.LENGTH_LONG).show()
    }
}