package com.example.appet

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_registro_foto.*

class RegistroFotoActivity : AppCompatActivity() {

    private val selectActivity = 50
    private var imageUri : Uri? = null
    var myStrPhoto : StorageReference? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_foto)

        val bundle = intent.extras  //Recuperar los parametros
        val email =  bundle?.getString("email")//Se accede al map en donde se almacenan los datos
        val provider = bundle?.getString("provider")//Se accede al map en donde se almacenan los datos

        myStrPhoto = FirebaseStorage.getInstance().getReference()

        nextPicButton.setOnClickListener{
            if (email != null) {
                saveProfileImage(email)
            }

            showHome(email ?: "",provider ?: "")
        }

        backPicButton.setOnClickListener{
            println("adios")
        }

        profilePic.setOnClickListener{
            selectPhotoFromGallery(this, selectActivity)
            println("holi")
        }

    }

    /**
     * Abre la galeria del télefono del usuario.
     *
     * @param activity Actividad en la cual se ejecuta la función.
     */
    private fun selectPhotoFromGallery(activity: Activity, code: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity.startActivityForResult(intent, code)
    }

    /**
     * Guarda la imagen de perfil en el Storage de Firebase.
     *
     * @param emailUsuario Cadena que almacena el correo del usuario
     * para organizar las imágenes en Firebase.
     */
    private fun saveProfileImage(emailUsuario: String){
        var filePath: StorageReference =
            myStrPhoto?.child(emailUsuario)!!.child("Foto de perfil")!!.child ("profile_picture")
        filePath.putFile(imageUri!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == selectActivity && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data
                profilePic.setImageURI(imageUri)
            }
        }
    }

    /**
     * Inicia la actividad principal.
     *
     * @param email Cadena con el email del Usuario.
     * @param provider Tipo de inicio de sesión empleado.
     */
    private fun showHome(email: String, provider: String) {
        // IR A HOME
        val homeIntent = Intent(this, HomeActivity::class.java).apply {    //CREAR UN INTENT A LA NUEVA PANTALLA Y NAVEGAR A LA NUEVA PANTALLA
            //PARAMETROS A PASAR
            putExtra("email", email) //PASARLE EL EMAIL A LA NUEVA PANTALLA
            putExtra("provider", provider) //PASARLE EL PROVEEDOR A LA NUEVA PANTALLA
        }
        startActivity(homeIntent) //LA NAVEGACION A LA NUEVA PANTALLA
        finish()
    }

}