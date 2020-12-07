package com.example.appet

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registro_foto.*

class RegistroFotoActivity : AppCompatActivity() {

    private val selectActivity = 50
    private var imageUri : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_foto)

        nextPicButton.setOnClickListener{
            println("hola")
        }

        backPicButton.setOnClickListener{
            println("adios")
        }

        profilePic.setOnClickListener{
            selectPhotoFromGallery(this, selectActivity)
            println("holi")
        }

    }

    private fun selectPhotoFromGallery(activity: Activity, code: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity.startActivityForResult(intent, code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when{
            requestCode == selectActivity && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data
                profilePic.setImageURI(imageUri)
            }
        }
    }


}