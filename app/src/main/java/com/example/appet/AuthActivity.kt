package com.example.appet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_login.password_login
import kotlinx.android.synthetic.main.activity_login.user_login

class AuthActivity : AppCompatActivity() {

    private val  GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {

        //Error para activar
        //throw RuntimeException("Forzado de error")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //setup
        setup()

        //Se llama la funcion notification, para acceder al ID del dispositivo en el que se accedio
        //notification()

        ir_a_login_button.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)

        }
    }

    //Funcion para obtener el ID de un dispositivo, util para enviar notificaciones personalizadas
    private fun notification() {

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            it.result?.token?.let {
                println("Este es el token del dispositivo: ${it}") //Esta linea es para imprimir el token del dispositivo; se debe enviar a una base de datos para que se pueda acceder a estos a la hora de necesitarse enviar una notificacion a un usuario determinado
            }
        }
    }

    //FUNCION SETUP
    private fun setup() {
        title = "Autenticación"

        //REGISTRO DE USUARIO CON CORREO Y CONTRASEÑA
        sign_up_button.setOnClickListener {//RECOGER EL EVENTO CUANDO SE EJECUTE
            if (user_login.text.isNotEmpty() && password_login.text.isNotEmpty() && confirmPassword.text.isNotEmpty() && nombre_registro.text.isNotEmpty() && telefono_registro.text.isNotEmpty()) { //COMPROBAR QUE NO SON VACÍAS
                if(password_login.text.toString().equals(confirmPassword.text.toString())){ //Se debe comparar si el usuario ingreso la misma contraseña, o si cometio un error al digitar
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        user_login.text.toString(),
                        password_login.text.toString()
                    ).addOnCompleteListener {//REGISTRO DE USUARIO Y CONTRASEÑA EN FIREBASE
                        if (it.isSuccessful) {
                            //showHome(it.result?.user?.email ?: "", ProviderType.BASIC) // PASAR A LA NUEVA PANTALLA,los signos de interrogación son porque el email puede o no existir( Por lo que estas son condiciones por si no existe envíe un string vacío
                            val registro1Intent = Intent(this, Registro1Activity::class.java)
                            startActivity(registro1Intent)
                        } else {
                            showAlert(2) //El usuario ya esta registrado
                        }
                    }
                }else{
                    showAlert(3) //El usuario cometio un error digitando la contraseña
            }}else {
                showAlert(1)  //Si estan vacios los campos
            }
        }


        //REGISTRO DE USUARIO CON GOOGLE
        button_google_auth.setOnClickListener {

            //Configuracion
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(
                getString(
                    R.string.default_web_client_id
                )
            ).requestEmail().build()
            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)

        }
    }

    //FUNCIONES QUE PRODUCEN UNA ALERTA SI ALGO ESTA MAL

    private fun showAlert(caso: Int){

        var mensaje: String;
        when (caso) {
            1 -> mensaje = "Existen campos vacios necesarios para registrar su cuenta en Miauff"
            2 -> mensaje = "Usted ya se encuentra registrado en Miauff o ha cometido un error a la hora de digitar sus datos en el proceso de registro"
            3 -> mensaje = "Las contraseñas no coinciden, intente nuevamente"
            else -> mensaje = "Ocurrio un Error inesperado"
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                if(account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(account.email ?: "", ProviderType.GOOGLE) // PASAR A LA NUEVA PANTALLA,los signos de interrogación son porque el email puede o no existir( Por lo que estas son condiciones por si no existe envíe un string vacío
                        } else {
                            showAlert(2)//El usuario ya esta registrado
                        }
                    }
                }

            } catch (e: ApiException) {
                showAlert(2)//El usuario ya esta registrado
            }
        }
    }
}
