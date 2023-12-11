package com.example.magicacompleto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.example.magicacompleto.client.ApiClient
import com.example.magicacompleto.databinding.ActivityRegistroBinding
import com.example.magicacompleto.model.request.RegisterRequest
import com.example.magicacompleto.model.response.RegisterResponse



class Registro : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    private val PASSWORDPATTERN = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=!|°()?¡¿*.:,])" +  // at least 1 special character
                "(?=\\S+$)" +                     // no white spaces
                ".{8,}" +                         // at least 4 characters
                "$"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        register()

        binding.cuenta1.setOnClickListener {
            startActivity(Intent(this@Registro, Login::class.java))
        }


    }
    fun register() {
        binding.singIn2.setOnClickListener {
            getInputs()
        }
    }

    private fun getInputs()
    {
        val usuario         = binding.usuario1.text.toString()
        val email           = binding.email1.text.toString()
        val password        = binding.password2.text.toString()


        if(usuario.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() )
        {
            if(validateEmail()  and validateNameAndDate())
            {
                registerUser(usuario, email, password)
            }
        }
        else
        {
            Toast.makeText(this@Registro, "Completa los campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun registerUser(user: String, email: String, password: String, ) {
        val registerRequest = RegisterRequest(user, email, password)
        val apiCall = ApiClient.getApiService().registerUser(registerRequest)
        apiCall.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if(response.isSuccessful)

                {
                    move()
                    finish()
                }

                else
                {
                    Toast.makeText(this@Registro, "Sucedio un error inesperado o corrige tus credenciales", Toast.LENGTH_LONG
                    ).show()
                }

            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e("Registro", "Fallo en la llamada al servidor")
                Toast.makeText(this@Registro, "Ha ocurrido un error inesperado ", Toast.LENGTH_LONG).show()
            }

        })


    }

    private fun move() {
        startActivity(Intent(this@Registro, Login::class.java))
        Toast.makeText(this@Registro, "Registro exitoso!!!", Toast.LENGTH_LONG).show()
    }

    private fun validateEmail():Boolean {
        val email = binding.email1.text.toString()
        return if(email.isEmpty()){
            binding.email1.error = "El campo del correo no puede estar vacio"
            false
        }else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email1.error = "Por favor ingresa un correo valido"
            false
        } else {
            binding.email1.error = null
            true
        }
    }

    private fun validateNameAndDate(): Boolean {
        val name = binding.usuario1.text.toString()
        return if(name.isEmpty()) {
            binding.usuario1.error = "El campo no puede estar vacio"
            false
        } else {
            binding.usuario1.error = null
            true
        }
    }


}