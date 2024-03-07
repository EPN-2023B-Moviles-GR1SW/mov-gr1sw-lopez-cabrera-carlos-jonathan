package com.example.flip.screens.bussiness

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.flip.R

class FlipAction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flip_action)

        onTap()
    }

    private fun onTap(){
        val ivCoin = findViewById<ImageView>(R.id.IVcoin)
        ivCoin.setOnClickListener {
            val result = (1..2).random()
            if (result == 1){
                flipAnimation(R.drawable.head,"Cara: La casa gana")
                //TODO: LOGICA SI PIERDE

            }else{
                flipAnimation(R.drawable.tail,"Cruz: Tu ganas")
                //TODO: LOGICA SI GANA
            }
        }
    }

    private fun flipAnimation(resultImage: Int, resultText: String) {

        val imageView = findViewById<ImageView>(R.id.IVcoin)
        imageView.animate().apply {
            duration = 1000
            rotationYBy(1800f)
            imageView.isClickable = false
        }.withEndAction(){
            imageView.setImageResource(resultImage)
            Toast.makeText(this, resultText, Toast.LENGTH_SHORT).show()
            imageView.isClickable = true
        }
    }
}