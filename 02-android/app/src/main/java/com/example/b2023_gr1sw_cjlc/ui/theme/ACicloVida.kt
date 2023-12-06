package com.example.b2023_gr1sw_cjlc.ui.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.b2023_gr1sw_cjlc.R
import com.google.android.material.snackbar.Snackbar
import androidx.compose.material3.Snackbar as Snackbar1


var textoGlobal = ""


class ACicloVida : AppCompatActivity() {



    fun  mostratSnackBar(texto:String){
        textoGlobal = textoGlobal + " " + texto
        Snackbar
            .make(
                findViewById(R.id.cl_ciclo_vida),
                        textoGlobal,
                        Snackbar.LENGTH_INDEFINITE
            )
            .show()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        mostratSnackBar("onCreate")
    }

    //Eventos del ciclo de vida

    override fun onStart() {
        super.onStart()
        mostratSnackBar("onStart")
    }

    override fun onResume() {
        super.onResume()
        mostratSnackBar("onResuem")
    }

    override fun onRestart() {
        super.onRestart()
        mostratSnackBar("onRestart")
    }

    override fun onPause() {
        super.onPause()
        mostratSnackBar("onPause")
    }

    override fun onStop() {
        super.onStop()
        mostratSnackBar("onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        mostratSnackBar("onDestroy")
    }
}