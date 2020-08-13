package com.example.appet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType {
    BASIC //TIPO DE AUTENTICACION BASICA(CORREO Y CONTRASEÑA)
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup
        val bundle = intent.extras //RECUPERAR LOS PARAMETROS
        val email =  bundle?.getString("email")//Se accede al map en donde se almacenan los datos
        val provider = bundle?.getString("provider")//Se accede al map en donde se almacenan los datos

        //Se llama la funcion setup, con los parametros recien recuperados
        setup(email ?: "", provider ?: "")
    }

    private fun setup(email:String, provider: String) { //COMO PARAMETROS LO QUE DEBE LLEGAR A ESTA PANTALLA

        title = "Inicio"
        emailtextView.text = email
        providertextview.text = provider

        //LLAMAR A FIREBASE PARA CIERRE DE SESION
        log_out_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()//para volver a la pantalla anterior(EL INICIO DE SESION)
        }
    }
}