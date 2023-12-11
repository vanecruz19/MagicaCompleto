package com.example.magicacompleto.model.response.response

import com.google.gson.annotations.SerializedName
import org.w3c.dom.Comment

data class ListaHResponse(
    @SerializedName("listaComment") var listaComment: ArrayList<Comment>
)
