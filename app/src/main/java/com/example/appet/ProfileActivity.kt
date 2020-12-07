package com.example.appet

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setSupportActionBar(findViewById(R.id.top_appbar))

        //listener del botón
        findViewById<FloatingActionButton>(R.id.update_btn).setOnClickListener { view ->
            Snackbar.make(view, "Your information has been updated", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    /**
     * Configuración menu de la barra de app
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.topbar_menu, menu)
        return true
    }

}

