package com.example.magicacompleto.model.response

object UserManger {

    private var userId: Int = -1

    fun getUserId(): Int {
        return userId
    }

    fun setUserId(id: Int) {
        userId = id
    }
}
