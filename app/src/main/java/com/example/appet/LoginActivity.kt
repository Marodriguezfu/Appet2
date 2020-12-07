package com.example.appet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.button_google_auth
import kotlinx.android.synthetic.main.activity_login.password_login
import kotlinx.android.synthetic.main.activity_login.user_login

class LoginActivity : AppCompatActivity() {

    private val  GOOGLE_SIGN_IN = 100

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

    /**
     * Setea la funcionalidad de los botones de la actividad
     */
    private fun setup() {
        title = "Inicio de sesión"

        //ENTRAR CON UN USUARIO REGISTRADO
        log_in_button.setOnClickListener {
            if (user_login.text.isNotEmpty() && password_login.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(user_login.text.toString(),password_login.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        //showHome(it.result?.user?.email ?: "", ProviderType.BASIC) //los signos de interrogación son porque el email puede o no existir( Por lo que estas son condiciones por si no existe envíe un string vacío
                        showFoto(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }else {
                        showAlert(2) //SI NO SE REGISTRA CREA UNA ALERTA
                    }
                }
            }else {
                showAlert(1) //Si estan vacios los campos
            }
        }

        button_google_auth.setOnClickListener {

            //Configuracion
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN )

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
            1 -> mensaje = "Los campos requeridos para el inicio de sesión se encuentran vacios"
            2 -> mensaje = "Usted no se encuentra registrado en Miauff o ha cometido un error a la hora de iniciar sesión"
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
     * Inicia la actividad principal
     */
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

    private fun showFoto(email: String, provider: ProviderType) {
        // IR A HOME
        val fotoIntent = Intent(this, RegistroFotoActivity::class.java).apply {    //CREAR UN INTENT A LA NUEVA PANTALLA Y NAVEGAR A LA NUEVA PANTALLA
            //PARAMETROS A PASAR
            putExtra("email", email) //PASARLE EL EMAIL A LA NUEVA PANTALLA
            putExtra("provider", provider.name) //PASARLE EL PROVEEDOR A LA NUEVA PANTALLA
        }
        startActivity(fotoIntent) //LA NAVEGACION A LA NUEVA PANTALLA
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
                            showAlert(2)//El usuario no esta registrado
                        }
                    }
                }

            } catch (e: ApiException) {
                showAlert(2)
            }


        }
    }
}
