package com.example.appet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.password_login
import kotlinx.android.synthetic.main.activity_login.user_login

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //setup
        setup()

        ir_a_login_button.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)

        }
    }

    //FUNCION SETUP
    private fun setup() {
        title = "Autenticación"

        //CREAR UN USUARIO
        sign_up_button.setOnClickListener {//RECOGER EL EVENTO CUANDO SE EJECUTE
            if (user_login.text.isNotEmpty() && password_login.text.isNotEmpty() && nombre_registro.text.isNotEmpty() && telefono_registro.text.isNotEmpty()) { //COMPROBAR QUE NO SON VACÍAS
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(user_login.text.toString(), password_login.text.toString()).addOnCompleteListener {//REGISTRO DE USUARIO Y CONTRASEÑA EN FIREBASE
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC) // PASAR A LA NUEVA PANTALLA,los signos de interrogación son porque el email puede o no existir( Por lo que estas son condiciones por si no existe envíe un string vacío
                    } else {
                        showAlert()//El usuario ya esta registrado
                    }
                }
            }else {
                showAlert() //Si estan vacios los campos
            }
        }
    }
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
