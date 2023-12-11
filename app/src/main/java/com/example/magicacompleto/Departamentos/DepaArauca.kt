package com.example.magicacompleto.Departamentos

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.magicacompleto.HistoriasAma.Historia1Ama
import com.example.magicacompleto.HistoriasAma.Historia2Ama
import com.example.magicacompleto.HistoriasAma.Historia3Ama
import com.example.magicacompleto.HistoriasAma.Historia4Ama
import com.example.magicacompleto.R

class DepaArauca : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_depa_arauca)
        val amazonas1Card: CardView = findViewById(R.id.Arauca1card)
        val amazonas2Card: CardView = findViewById(R.id.Arauca2card)
        val amazonas3Card: CardView = findViewById(R.id.Arauca3card)
        val amazonas4Card: CardView = findViewById(R.id.Arauca4card)

        amazonas1Card.setOnClickListener {
            val intent = Intent(this, Historia1Ama::class.java)
            startActivity(intent)
        }

        amazonas2Card.setOnClickListener {
            val intent = Intent(this, Historia2Ama::class.java)
            startActivity(intent)
        }
        amazonas3Card.setOnClickListener {
            val intent = Intent(this, Historia3Ama::class.java)
            startActivity(intent)
        }
        amazonas4Card.setOnClickListener {
            val intent = Intent(this, Historia4Ama::class.java)
            startActivity(intent)
        }


    }
}