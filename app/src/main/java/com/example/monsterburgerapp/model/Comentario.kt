package com.example.monsterburgerapp.model

data class Comentario(

    var id:String,
    var nombreUsuario:String,
    var puntaje:Int,
    var texto:String

){

    constructor():this(
        "","",0,""
    ){}
}