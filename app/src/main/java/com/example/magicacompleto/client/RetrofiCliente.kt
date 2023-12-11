package com.example.magicacompleto.client

import com.example.magicacompleto.service.ApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofiCliente {
    val apiService: ApiService by lazy {
        Retrofit
            .Builder()
            .baseUrl(ApiClient.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }
}