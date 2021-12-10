package com.example.monsterburgerapp.model

data class Producto (
    var categoria: String,

    var id:String,
    var nombre:String,
    var precio:Int,
    var descripcion:String,
    var imageUrl:String,
    var inventario:Int,
    var cod_barras:String


){

    constructor():this(
        "","","",0,"","",0,""
    ){}
}

