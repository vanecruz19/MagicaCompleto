package com.example.magicacompleto.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.magicacompleto.MostraHistorias
import com.example.magicacompleto.client.RetrofiCliente
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {


    private var _listaComment = MutableLiveData<List<MostraHistorias>>()
    val listaComment: LiveData<List<MostraHistorias>> get() = _listaComment

    fun obtenerHistoria() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofiCliente.apiService.obtenerHistoria(nombre = String())
            withContext(Dispatchers.Main) {
                Log.d("API", response.body().toString())
                _listaComment.value = response.body()
            }
        }
    }
    fun obtenerComment(personaje: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofiCliente.apiService.obtenerHistoria(nombre = String())
            withContext(Dispatchers.Main) {
                Log.d("API", response.body().toString())
                _listaComment.value = response.body()
            }
        }
    }


}




