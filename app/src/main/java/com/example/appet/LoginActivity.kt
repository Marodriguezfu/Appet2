package com.example.appet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //Hacemos que una vez, la aplicación ha cargado, empiece a ejecutarse la app
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sign_in_button.setOnClickListener {
            val registerIntent = Intent(this, AuthActivity::class.java)
            startActivity(registerIntent)

        }

        //Setup
        setup()
        session()
    }

    override fun onStart() {
        super.onStart()
        LoginLayout.visibility = View.VISIBLE
    }
    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            LoginLayout.visibility = View.INVISIBLE
            showHome(email, ProviderType.valueOf(provider))
        }


    }
    //FUNCION SETUP
    private fun setup() {
        title = "Inicio de sesión"

        //ENTRAR CON UN USUARIO REGISTRADO
        log_in_button.setOnClickListener {
            if (user_login.text.isNotEmpty() && password_login.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(user_login.text.toString(),password_login.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC) //los signos de interrogación son porque el email puede o no existir( Por lo que estas son condiciones por si no existe envíe un string vacío
                    }else {
                        showAlert2() //SI NO SE REGISTRA CREA UNA ALERTA
                    }
                }
            }else {
                showAlert1() //Si estan vacios los campos
            }
            }
        }



    //FUNCIONES QUE PRODUCEN UNA ALERTA SI ALGO ESTA MAL

    //La funcion showAlert1 mostrara un mensaje que le diga al usuario que no introdujo parte de la informacion (usuario, contraseña o ambos)
    private fun showAlert1(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Los campos requeridos para el inicio de sesión se encuentran vacios")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //La funcion showAlert2 mostrara un mensaje que le indica al usuario que no se encuentra registrado en la aplicacion, o ha cometido un error al digitar sus credenciales
    private fun showAlert2(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Usted no se encuentra registrado en Appet o ha cometido un error a la hora de iniciar sesión")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        // IR A HOME
        val homeIntent = Intent(this, HomeActivity::class.java).apply {    //CREAR UN INTENT A LA NUEVA PANTALLA Y NAVEGAR A LA NUEVA PANTALLA
            //PARAMETROS A PASAR
            putExtra("email", email) //PASARLE EL EMAIL A LA NUEVA PANTALLA
            putExtra("provider", provider.name) //PASARLE EL PROVEEDOR A LA NUEVA PANTALLA
        }
        startActivity(homeIntent) //LA NAVEGACION A LA NUEVA PANTALLA
        finish()
    }


}
