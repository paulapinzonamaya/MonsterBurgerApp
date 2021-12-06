package com.example.monsterburgerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.monsterburgerapp.model.Comentario
import com.example.monsterburgerapp.model.ComentarioRepository
import androidx.lifecycle.ViewModel

class CommentsListViewModel: ViewModel() {

    var ComentarioRepository: ComentarioRepository
    var ComentarioModel = MutableLiveData<List<Comentario>>()


    init {
        ComentarioRepository = ComentarioRepository()
    }

    fun getComentarios(){
        var currentComentarioList =  ComentarioRepository.getComentarios(ComentarioModel)

    }

}