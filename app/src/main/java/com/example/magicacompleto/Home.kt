package com.example.magicacompleto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.magicacompleto.Departamentos.DepaAmazonas
import com.example.magicacompleto.Departamentos.DepaAntioquia
import com.example.magicacompleto.Departamentos.DepaArauca
import com.example.magicacompleto.HistoriasAma.Historia1Ama
import com.example.magicacompleto.HistoriasAma.Historia2Ama
import com.example.magicacompleto.HistoriasAma.Historia3Ama
import com.example.magicacompleto.HistoriasAma.Historia4Ama
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textview.MaterialTextView

class Home: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener { private lateinit var drawerLayout: DrawerLayout
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)


        val materialTextView = findViewById<MaterialTextView>(R.id.Depa1)
        val materialTextView2 = findViewById<MaterialTextView>(R.id.Depa2)
        val materialTextView3 = findViewById<MaterialTextView>(R.id.Depa3)
        val materialTextView4 = findViewById<MaterialTextView>(R.id.Depa4)


        materialTextView.setOnClickListener {
            val intent = Intent(this, DepaAmazonas::class.java)
            startActivity(intent)
        }
        materialTextView2.setOnClickListener {
            val intent = Intent(this, DepaAntioquia::class.java)
            startActivity(intent)
        }
        materialTextView3.setOnClickListener {
            val intent = Intent(this, DepaArauca::class.java)
            startActivity(intent)
        }
        materialTextView4.setOnClickListener {
            val intent = Intent(this, DepaArauca::class.java)
            startActivity(intent)
        }

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                return true
            }

            R.id.perfil -> {
                val intent = Intent(this, Perfil::class.java)
                startActivity(intent)
                return true  // <-- Mueve esta línea aquí para que solo retorne si el intent se ha iniciado correctamente
            }

            R.id.Subir -> {
                val intent = Intent(this, SubirHistoria::class.java)
                startActivity(intent)
                return true  // <-- Mueve esta línea aquí para que solo retorne si el intent se ha iniciado correctamente
            }
            R.id.message -> {
                val intent = Intent(this, ReportarError::class.java)
                startActivity(intent)
                return true  // <-- Mueve esta línea aquí para que solo retorne si el intent se ha iniciado correctamente
            }
            R.id.historial -> {
                val intent = Intent(this, MostraHistorias::class.java)
                startActivity(intent)
                return true  // <-- Mueve esta línea aquí para que solo retorne si el intent se ha iniciado correctamente
            }
            R.id.salir -> {
                // Regresar a MainActivity
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()  // Esto finaliza la actividad actual y evita que el usuario vuelva atrás
                return true
            }
            // Otras opciones del menú si las tienes
            else -> return super.onOptionsItemSelected(item)
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}