package com.example.magicacompleto

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.magicacompleto.client.ApiClient
import com.example.magicacompleto.model.response.request.CrearhRequest
import com.example.magicacompleto.model.response.response.CrearhResponse
import com.example.magicacompleto.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubirHistoria : AppCompatActivity() {

    private val apiService = ApiClient.getRetrofit().create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subir_historia)

    }

        fun onSubirClick(view: View) {
            val editTextTitulo = findViewById<EditText>(R.id.titulo)
            val editTextTexto = findViewById<EditText>(R.id.editText)

            val titulo = editTextTitulo.text.toString()
            val texto = editTextTexto.text.toString()

            // Crear el objeto SubirHCRequest
            val subirHCRequest = CrearhRequest(titulo, texto)

            // Llamar a la API
            val call: Call<CrearhResponse> = apiService.SubirHC(subirHCRequest)

            call.enqueue(object : Callback<CrearhResponse> {
                override fun onResponse(call: Call<CrearhResponse>, response: Response<CrearhResponse>) {
                    if (response.isSuccessful) {
                        val crearhResponse: CrearhResponse? = response.body()
                        // Manejar la respuesta exitosa (por ejemplo, mostrar un mensaje)
                        println("Respuesta de la API: $crearhResponse")
                    } else {
                        // Manejar la respuesta no exitosa (por ejemplo, mostrar un mensaje de error)
                        println("Error en la respuesta de la API")
                    }
                }

                override fun onFailure(call: Call<CrearhResponse>, t: Throwable) {
                    // Manejar errores de conexión u otros problemas
                    println("Error en la llamada a la API: ${t.message}")
                }
            })
        }
    }


