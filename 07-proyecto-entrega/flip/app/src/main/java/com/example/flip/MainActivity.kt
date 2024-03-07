package com.example.flip

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.flip.screens.bussiness.FlipAction
import com.example.flip.screens.bussiness.FlipWalletActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnFlipAction = findViewById<Button>(R.id.btnFlipAction)
        val btnFlipWallet = findViewById<Button>(R.id.btnFlipWalletView)
        btnFlipAction.
            setOnClickListener {
                irActividad(FlipAction::class.java)
            }

        btnFlipWallet.setOnClickListener {
            irActividad(FlipWalletActivity::class.java)
        }


    }


    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}

