package com.example.appet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_content.*

enum class ProviderType {
    BASIC, //TIPO DE AUTENTICACION BASICA(CORREO Y CONTRASEÑA)
    GOOGLE
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(top_appbar)


        //Setup
        val bundle = intent.extras //RECUPERAR LOS PARAMETROS
        val email =  bundle?.getString("email")//Se accede al map en donde se almacenan los datos
        val provider = bundle?.getString("provider")//Se accede al map en donde se almacenan los datos

        //Se llama la funcion setup, con los parametros recien recuperados
        setup(email ?: "", provider ?: "")

        //GUARDADO DE DATOS
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE). edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun setup(email:String, provider: String) { //COMO PARAMETROS LO QUE DEBE LLEGAR A ESTA PANTALLA

        title = "Inicio"
        emailtextView.text = email
        providertextview.text = provider

        //LLAMAR A FIREBASE PARA CIERRE DE SESION
        log_out_button.setOnClickListener {

            //Borrado de datos
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE). edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            //onBackPressed()//para volver a la pantalla anterior(EL INICIO DE SESION)
            finish()
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    /**
     * Configuración menu de la barra de app
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.topbar_menu, menu)
        return true
    }
    //Liseteners para cada ietm en el menu de la barra de la app
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mascotas_opt ->{
                //Agregar accion que desee que haga este botón
                true
            }
            R.id.galleria_opt ->{
                //Agregar acción que desee que haga este botón
                true
            }
            R.id.settings_btn ->{
                //Agregar Acción que desee que haga este botón.
              true
            }
            R.id.log_out_opt ->{
                //Acciones para realizar un LogOut exitoso
                //Borrado de datos
                val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE). edit()
                prefs.clear()
                prefs.apply()

                FirebaseAuth.getInstance().signOut()
                //onBackPressed()//para volver a la pantalla anterior(EL INICIO DE SESION)
                finish()
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


}