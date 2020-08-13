package com.example.appet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                        showAlert() //SI NO SE REGISTRA CREA UNA ALERTA
                    }
                }
            }else {
                showAlert() //Si estan vacios los campos
            }
            }
        }



    //FUNCION QUE PRODUCE UNA ALERTA SI ALGO ESTA MAL
    private fun showAlert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
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
