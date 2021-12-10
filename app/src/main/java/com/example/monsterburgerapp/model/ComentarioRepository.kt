package com.example.monsterburgerapp.model

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ComentarioRepository {

    val db = Firebase.firestore
    val basecomentarios =db.collection("Comentarios")
    private var comentarios = listOf<Comentario>()

    /*
    private var comentarios = listOf<Comentario>(
        Comentario("Brad", 5, "La mejor tienda de hamburguesas"),
        Comentario("Angelina", 5, "La mejor tienda de hamburguesas")

    )
*/


    fun getComentarios(mutableLiveData: MutableLiveData<List<Comentario>>){


        basecomentarios.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {

               comentarios = listOf()
                for (document in snapshot.documents) {

                    var comentario = document.toObject(Comentario::class.java)

                    if(comentario != null){
                        comentario.id = document.id
                        comentarios += comentario
                    }

                }
                mutableLiveData.postValue(comentarios)

            } else {
                Log.d(ContentValues.TAG, "Current data: null")
            }
        }


    }


    /*
    fun setComentarios(mutableLiveData: MutableLiveData<List<Comentario>>){


        basecomentarios.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {

                comentarios = listOf()
                for (document in snapshot.documents) {

                    var comentario = document.toObject(Comentario::class.java)

                    if(comentario != null){
                        comentario.id = document.id
                        comentarios += comentario
                    }

                }
                mutableLiveData.postValue(comentarios)

            } else {
                Log.d(ContentValues.TAG, "Current data: null")
            }
        }


    }


     */





    /*
    fun getComentarios( mutableLiveData: MutableLiveData<List<Comentario>> ){
    basecomentarios.whereEqualTo("categoria", "Acompañamientos")
    .get()
    .addOnSuccessListener { result ->
        println(result)
        comentarios = listOf()
        for (document in result) {

            var comentario = document.toObject(Producto::class.java)
            comentario.id = document.id
            comentarios += comentario
        }

        mutableLiveData.postValue(comentarios)


    }
    .addOnFailureListener { exception ->
        Log.w(TAG, "Error getting documents.", exception)
    }
        return

}




     */


}