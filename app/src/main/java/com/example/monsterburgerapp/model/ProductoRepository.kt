package com.example.monsterburgerapp.model

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class ProductoRepository {


    val db = Firebase.firestore
    val docRef = db.collection("Productos")
    private var productos = listOf<Producto>()


   fun getProductos(mutableLiveData: MutableLiveData<List<Producto>> ){


        docRef.whereEqualTo("categoria", "Entradas").addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(ContentValues.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {

                productos = listOf()
                for (document in snapshot.documents) {

                    var producto = document.toObject(Producto::class.java)

                    if(producto != null){
                        producto.id = document.id
                        productos += producto
                    }

                }
                mutableLiveData.postValue(productos)

            } else {
                Log.d(ContentValues.TAG, "Current data: null")
            }
        }

    }


    fun findByIds(productosIds:List<String>, mutableLiveData: MutableLiveData<List<Producto>>):List<Producto>{

        //TODO: Consultar todos los productos del carrito de Firebase

        println(">>> IDs de los Productos")
        println(productosIds)

        var productosFilter:List<Producto> = mutableListOf<Producto>()


        if(!productosIds.isEmpty()){

            docRef.whereIn(FieldPath.documentId(), productosIds).addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {

                    productosFilter = listOf()
                    for (document in snapshot.documents) {

                        var producto = document.toObject(Producto::class.java)

                        if(producto != null){
                            producto.id = document.id
                            productosFilter += producto
                        }

                    }

                    mutableLiveData.postValue(productosFilter)
                    println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz")
                    println(productosFilter)

                } else {
                    Log.d(ContentValues.TAG, "Current data: null")
                }
            }


        }
        return productosFilter

    }

    fun decrementarInventario(){
        //TODO Decrementar Inventario en Firebase

    }

}