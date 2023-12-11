package com.example.magicacompleto.model.request

data class RegisterRequest(
    val user: String,
    val email: String,
    val password: String,
)
