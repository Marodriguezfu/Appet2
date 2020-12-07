package com.example.appet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_registro_vacunas.*
import java.text.SimpleDateFormat
import java.util.*

class RegistroVacunasActivity : AppCompatActivity() {

    private var diaObtenido = 0
    private var mesObtenido = 0
    private var añoObtenido = 0
    private var fechaDeNacimiento = ""

    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
    val sdf = SimpleDateFormat("yyyy/MM/dd")
    var formatedDate = sdf.format(date)
    private var tipoMascotaP = ""
    private var nombreMascota = ""
    private var sexoMascota = ""
    private var razaMascota = ""
    private var colorMascota = ""
    private var pesoMascota = ""

    //Perro
    private var moquillo = ""
    private var moquilloFecha = ""
    private var hepatitis = ""
    private var hepatitisFecha = ""
    private var parvovirosis = ""
    private var parvovirosisFecha = ""
    private var leptospirosis = ""
    private var leptospirosisFecha = ""


    //Compartidos
    private var rabia = ""
    private var rabiaFecha = ""

    //Gato

    private var leucemia = ""
    private var leucemiaFecha = ""
    private var rinotraqueitis = ""
    private var rinotraqueitisFecha = ""
    private var panleucopenia = ""
    private var panleucopeniaFecha = ""
    private var calcivirosis = ""
    private var calcivirosisFecha = ""

    private var email = ""
    private var provider = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_vacunas)



        val bundle = intent.extras //RECUPERAR LOS PARAMETROS

        val nombre =  bundle?.getString("nombreMascota")
        val tipoMascota =  bundle?.getString("tipoMascota")
        nombreMascota = bundle?.getString("nombreMascota").toString()
        sexoMascota = bundle?.getString("sexoMascota").toString()
        razaMascota = bundle?.getString("razaMascota").toString()
        colorMascota = bundle?.getString("colorMascota").toString()
        pesoMascota = bundle?.getString("pesoMascota").toString()
        tipoMascotaP = tipoMascota ?: ""
        email = bundle?.getString("email").toString()
        provider = bundle?.getString("provider").toString()

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

        morePet.setOnClickListener{
            if(fechaDeNacimiento == ""){
                showAlert(1)
            } else {
                saveDataPet()
                showMascota(email, provider)
            }
        }

        noMorePet.setOnClickListener{
            if(fechaDeNacimiento == ""){
                showAlert(1)
            } else {
                saveDataPet()
                showFoto(email, provider)
            }
        }

    }

    /**
     * Muestra un cuadro de alerta amigable cuando el usuario comete un error.
     *
     * @param caso número que reconoce el error cometido por el usuario.
     */
    private fun showAlert(caso: Int){

        var mensaje: String;
        when (caso) {
            1 -> mensaje = "Debe seleccionar la fecha de nacimiento de su peludo para continuar"
            else -> mensaje = "Ocurrio un Error inesperado"
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /**
     * Guarda la infomación obtenida del usuario en un objeto del tipo necesitado
     * y sube esta información a la base de datos de Firebase.
     */
    private fun saveDataPet(){
        if(tipoMascotaP == "Perro"){
            moquillo = numberVacuna1.text.toString()
            hepatitis = numberVacuna2.text.toString()
            parvovirosis = numberVacuna3.text.toString()
            leptospirosis = numberVacuna4.text.toString()
            rabia = numberVacuna5.text.toString()

            var perrito: Perro = Perro(email ?: "",nombreMascota,sexoMascota,razaMascota,colorMascota,pesoMascota,fechaDeNacimiento)
            perrito.moquillo=moquillo
            perrito.moquilloFecha=moquilloFecha
            perrito.hepatitis = hepatitis
            perrito.hepatitisFecha=hepatitisFecha
            perrito.parvovirosis = parvovirosis
            perrito.parvovirosisFecha=parvovirosisFecha
            perrito.leptospirosis = leptospirosis
            perrito.leptospirosisFecha=leptospirosisFecha
            perrito.rabiaFecha=rabiaFecha

            perrito.saveInformation()

            //

        }
        else{
            leucemia = numberVacuna1.text.toString()
            rinotraqueitis = numberVacuna2.text.toString()
            panleucopenia = numberVacuna3.text.toString()
            calcivirosis = numberVacuna4.text.toString()
            rabia = numberVacuna5.text.toString()
            var gatito: Gato = Gato(email ?: "",nombreMascota,sexoMascota,razaMascota,colorMascota,pesoMascota,fechaDeNacimiento)

            gatito.leucemia = leucemia
            gatito.leucemiaFecha=leucemiaFecha
            gatito.rinotraqueitis = rinotraqueitis
            gatito.rinotraqueitisFecha=rinotraqueitisFecha
            gatito.panleucopenia = panleucopenia
            gatito.panleucopeniaFecha = panleucopeniaFecha
            gatito.calcivirosis = calcivirosis
            gatito.calcivirosisFecha=calcivirosisFecha
            gatito.rabia = rabia
            gatito.rabiaFecha=rabiaFecha

            gatito.saveInformation()

        }



        println("Su mascota es: " +
                tipoMascotaP + " " + "Su nombre es: " +
                nombreMascota + " " + " Su sexo es " +
                sexoMascota + " " + " Su raza es " +
                razaMascota + " " + " El color es " +
                colorMascota + " " + "El peso es " +
                pesoMascota + " " + "La fecha de nacimiento es " +
                fechaDeNacimiento + " Le pertenece a: " +
                email + " registrado por: " + provider
        )
    }

    /**
     * Muestra el cuadro de dialogo con la configuración establecida.
     *
     * @param parent permite al programa conocer quien llamo a la función.
     */
    private fun showDatePickerDialog(parent: EditText) {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year, parent) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    /**
     * Organiza la información recogida sobre las fechas de
     * vacunación de las mascotas.
     */
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
            fechaDeNacimiento = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString();
        }

        else if(parent == dateVacuna1 && tipoMascotaP == "Perro") { // Moquillo
            moquilloFecha = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString()
        }
        else if(parent == dateVacuna2 && tipoMascotaP == "Perro") { // Hepatitis
            hepatitisFecha = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString()
        }
        else if(parent == dateVacuna3 && tipoMascotaP == "Perro") { // Parvovirosis
            parvovirosisFecha = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString()
        }
        else if(parent == dateVacuna4 && tipoMascotaP == "Perro") { // Leptospirosis
            leptospirosisFecha = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString()
        }
        else if(parent == dateVacuna5 && tipoMascotaP == "Perro") { // Rabia
            rabiaFecha = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString()
        }
        else if(parent == dateVacuna1 && tipoMascotaP == "Gato") { // Leucemia
            leucemiaFecha = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString()
        }
        else if(parent == dateVacuna2 && tipoMascotaP == "Gato") { // Rinotraqueitis
            rinotraqueitisFecha = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString()
        }
        else if(parent == dateVacuna3 && tipoMascotaP == "Gato") { // Panleucopenia
            panleucopeniaFecha = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString()
        }
        else if(parent == dateVacuna4 && tipoMascotaP == "Gato") { // Calcivirosis
            calcivirosisFecha = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString()
        }
        else if(parent == dateVacuna5 && tipoMascotaP == "Gato") { // Rabia
            rabiaFecha = diaObtenido.toString()+"/"+(mesObtenido+1).toString()+"/"+añoObtenido.toString()
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    /**
     * Inicia la actividad RegistroMascota
     */
    private fun showMascota(email: String, provider: String) {
        // IR A HOME
        val registroMascotaIntent = Intent(this, RegistroMascotaActivity::class.java).apply {    //CREAR UN INTENT A LA NUEVA PANTALLA Y NAVEGAR A LA NUEVA PANTALLA
            //PARAMETROS A PASAR
            putExtra("email", email) //PASARLE EL EMAIL A LA NUEVA PANTALLA
            putExtra("provider", provider) //PASARLE EL PROVEEDOR A LA NUEVA PANTALLA
        }
        startActivity(registroMascotaIntent) //LA NAVEGACION A LA NUEVA PANTALLA
        finish()
    }

    /**
     * Inicia la actividad RegistroFoto
     */
    private fun showFoto(email: String, provider: String) {
        // IR A HOME
        val fotoIntent = Intent(this, RegistroFotoActivity::class.java).apply {    //CREAR UN INTENT A LA NUEVA PANTALLA Y NAVEGAR A LA NUEVA PANTALLA
            //PARAMETROS A PASAR
            putExtra("email", email) //PASARLE EL EMAIL A LA NUEVA PANTALLA
            putExtra("provider", provider) //PASARLE EL PROVEEDOR A LA NUEVA PANTALLA
        }
        startActivity(fotoIntent) //LA NAVEGACION A LA NUEVA PANTALLA
        finish()
    }

}