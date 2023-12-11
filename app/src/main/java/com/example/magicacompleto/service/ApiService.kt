package com.example.magicacompleto.service

import com.example.magicacompleto.MostraHistorias
import com.example.magicacompleto.model.request.LoginRequest
import com.example.magicacompleto.model.request.RegisterRequest
import com.example.magicacompleto.model.response.LoginResponse
import com.example.magicacompleto.model.response.RegisterResponse
import com.example.magicacompleto.model.response.request.CrearhRequest
import com.example.magicacompleto.model.response.response.CrearhResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/api/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/api/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>


    @POST("/api/chistorias")  // Cambia la URL según la estructura de tu API
    fun SubirHC(@Body CrearhRequest: CrearhRequest): Call<CrearhResponse>

    @GET ("/api/mostrarhistorias")
     suspend fun obtenerHistoria(
        @Query("character") nombre: String
    ): Response<List<MostraHistorias>>


}