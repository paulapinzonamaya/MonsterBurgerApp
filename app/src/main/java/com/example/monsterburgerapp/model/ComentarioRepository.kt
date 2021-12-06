package com.example.monsterburgerapp.model


import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ComentarioRepository {

    val db = Firebase.firestore

    private var comentarios = listOf<Comentario>(
        Comentario("Brad", 5, "La mejor tienda de hamburguesas"),
        Comentario("Angelina", 5, "La mejor tienda de hamburguesas")

        )


    fun getComentarios() {

        db.collection("comentarios")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

    }


}


   /* fun findByIds(comentariosIds:List<Int>):List<Comentario>{

        return comentarios.filter{p-> comentariosIds.contains(p.id)}

    }
}*/




