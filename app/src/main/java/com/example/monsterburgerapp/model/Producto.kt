package com.example.monsterburgerapp.model

data class Producto (
    var categoria: String,

    var id:String,
    var nombre:String,
    var precio:Int,
    var descripcion:String,
    var imageUrl:String,
    var inventario:Int

){

    constructor():this(
        "","","",0,"","",0
    ){}
}

