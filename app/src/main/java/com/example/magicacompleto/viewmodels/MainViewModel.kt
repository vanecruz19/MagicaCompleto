package com.example.magicacompleto.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magicacompleto.client.RetrofiCliente
import com.example.magicacompleto.model.response.Histori

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {



    private var _listaComment = MutableLiveData<List<Histori>>()
    val listaComment: LiveData<List<Histori>> get() = _listaComment

    fun obtenerHistoria() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofiCliente.apiService.obtenerHistoria()
            withContext(Dispatchers.Main) {
                Log.d("API", response.body().toString())
                _listaComment.value = response.body()
            }
        }
    }

    fun obtenerHistoria(personaje: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofiCliente.apiService.obtenerHistoria(personaje)
            withContext(Dispatchers.Main) {
                Log.d("API", response.body().toString())
                _listaComment.value = response.body()
            }
        }
    }



}