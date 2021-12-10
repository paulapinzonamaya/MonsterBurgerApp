package com.example.monsterburgerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterburgerapp.model.Comentario
import com.example.monsterburgerapp.model.ComentarioRepository

class ComentariosListViewModel: ViewModel() {


    var comentarioRepository: ComentarioRepository
    var comentariosModel = MutableLiveData<List<Comentario>>()


    init {
        comentarioRepository = ComentarioRepository()
    }

    fun getComentarios(){
        var currentComentarioList =  comentarioRepository.getComentarios(comentariosModel)

    }




}





