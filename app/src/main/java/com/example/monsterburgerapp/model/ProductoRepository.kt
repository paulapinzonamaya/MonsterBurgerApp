package com.example.monsterburgerapp.model

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class ProductoRepository {


    val db = Firebase.firestore
    val docRef = db.collection("Productos")
    private var productos = listOf<Producto>()
    val docRefb = db.collection("Bebidas")
    private var bebidas = listOf<Producto>()








    fun getProductosss(mutableLiveData: MutableLiveData<List<Producto>> ){


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


    fun getBebidas(mutableLiveData: MutableLiveData<List<Producto>> ){



        docRef.whereEqualTo("categoria", "Bebidas").addSnapshotListener { snapshot, e ->
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


    fun getAcompañamientos(mutableLiveData: MutableLiveData<List<Producto>> ){



        docRef.whereEqualTo("categoria", "Acompañamientos").addSnapshotListener { snapshot, e ->
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

    fun getHamburguesas(mutableLiveData: MutableLiveData<List<Producto>> ){
        docRef.whereEqualTo("categoria", "Hamburguesas").addSnapshotListener { snapshot, e ->
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





/*
    fun getAcompañamientos( mutableLiveData: MutableLiveData<List<Producto>> ){


                docRef.whereEqualTo("categoria", "Acompañamientos")
                        .get()
                        .addOnSuccessListener { result ->
                                println(result)
                                productos = listOf()
                                for (document in result) {

                                        var producto = document.toObject(Producto::class.java)
                                        producto.id = document.id
                                        productos += producto
                                }

                                mutableLiveData.postValue(productos)


                        }
                        .addOnFailureListener { exception ->
                                Log.w(TAG, "Error getting documents.", exception)
                        }

    }


 */







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




//sirve sin concexion a Firebase
    /*
    fun findByIds(productosIds:List<String>):List<Producto>{
        //TODO: Consultar todos los productos del carrito de Firebase

        println(">>> IDs de los Productos")
        println(productosIds)

        var productosFilter:List<Producto> = mutableListOf<Producto>()

        productosIds.forEach {

                id:String->
            productos.forEach {
                    p:Producto ->
                if(id == p.id){
                    productosFilter += p
                }
            }
        }

        println(">>> Productos Filtrados")
        println(productosFilter)

        //return productos.filter { p -> productosIds.contains(p.id) }

       return productosFilter
    }




     */


    fun getProductoByBarCode(mutableLiveData: MutableLiveData<Producto>, codBar:String){
        var producto:Producto = Producto()


        docRef.whereEqualTo("cod_barras", codBar).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener

            }

            if (snapshot != null && !snapshot.isEmpty) {


                for (document in snapshot.documents) {

                    if(document != null){

                        producto = document.toObject(Producto::class.java)!!

                    }

                }
                mutableLiveData.postValue(producto)

            } else {
                Log.d(TAG, "Current data: null")
            }
        }



    }


}