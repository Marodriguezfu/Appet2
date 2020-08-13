package com.example.appet

import android.content.ContentProvider
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //Hacemos que una vez, la aplicación ha cargado, empiece a ejecutarse la app
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Setup
        setup()
    }
    //FUNCION SETUP
    private fun setup() {
        title = "Autenticación"

        //CREAR UN USUARIO
        sign_in_button.setOnClickListener {//RECOGER EL EVENTO CUANDO SE EJECUTE
            if (user_login.text.isNotEmpty() && password_login.text.isNotEmpty()) { //COMPROBAR QUE NO SON VACÍAS
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(user_login.text.toString(), password_login.text.toString()).addOnCompleteListener {//REGISTRO DE USUARIO Y CONTRASEÑA EN FIREBASE
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC) // PASAR A LA NUEVA PANTALLA,los signos de interrogación son porque el email puede o no existir( Por lo que estas son condiciones por si no existe envíe un string vacío
                    } else {
                        showAlert()
                    }
                }
            }
        }

        //ENTRAR CON UN USUARIO REGISTRADO
        log_in_button.setOnClickListener {
            if (user_login.text.isNotEmpty() && password_login.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(sign_in_button.text.toString(),password_login.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC) //los signos de interrogación son porque el email puede o no existir( Por lo que estas son condiciones por si no existe envíe un string vacío
                    } else {
                        showAlert() //SI NO SE REGISTRA CREA UNA ALERTA
                    }
                }
            }
        }


    }
    //FUNCION QUE PRODUCE UNA ALERTA SI ALGO ESTA MAL
    private fun showAlert() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {


        val homeIntent = Intent(this, HomeActivity::class.java).apply {    //CREAR UN INTENT A LA NUEVA PANTALLA Y NAVEGAR A LA NUEVA PANTALLA
            //PARAMETROS A PASAR
            putExtra("email", email) //PASARLE EL EMAIL A LA NUEVA PANTALLA
            putExtra("provider", provider.name) //PASARLE EL PROVEEDOR A LA NUEVA PANTALLA
        }
        startActivity(homeIntent) //LA NAVEGACION A LA NUEVA PANTALLA
    }
}

