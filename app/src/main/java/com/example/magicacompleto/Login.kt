package com.example.magicacompleto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.example.magicacompleto.client.ApiClient
import com.example.magicacompleto.databinding.ActivityLoginBinding
import com.example.magicacompleto.model.request.LoginRequest
import com.example.magicacompleto.model.response.LoginResponse
import com.example.magicacompleto.model.response.UserManger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickListener()

        binding.Crear.setOnClickListener {
            startActivity(Intent(this@Login, Registro::class.java))
        }

        binding.Recuperar.setOnClickListener {
            startActivity(Intent(this@Login, Recuperar::class.java))
        }



    }

    private fun clickListener() {
        binding.login2.setOnClickListener{
            validate()
            getInputs()
        }
    }

    private fun getInputs() {
        val email = binding.email2.text.toString()
        val password = binding.password.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty())
        {
            loginUser(email, password)
        }
        else
        {
            Toast.makeText(this@Login, "Campos incompletos", Toast.LENGTH_LONG).show()
        }

    }

    private fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        val apiCall = ApiClient.getApiService().loginUser(loginRequest)
        apiCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResponse?.let {

                        val userId = it.user.id

                        UserManger.setUserId(userId)

                        move()

                    }
                } else {
                    Toast.makeText(this@Login, "Corrige tus credenciales", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@Login, "Ha ocurrido un error inesperado", Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun move() {
        startActivity(Intent(this, Home::class.java))
        finish()
    }

    /**
     *  Validate email and password
     */
    private fun validate(){
        val result = arrayOf(validateEmail(), validatePassword())
        if(false in result)
        {
            return
        }
    }

    private fun validateEmail():Boolean {
        val email = binding.email2.text.toString()
        return if(email.isEmpty()){
            binding.email2.error = "El campo del correo no puede estar vacio"
            false
        }else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email2.error = "Por favor ingresa un correo valido"
            false
        } else {
            binding.email2.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.password.text.toString()
        return if(password.isEmpty())
        {
            binding.password.error = "El campo contraseña no debe estar vacio"
            false
        } else {
            binding.password.error = null
            true
        }
    }

}