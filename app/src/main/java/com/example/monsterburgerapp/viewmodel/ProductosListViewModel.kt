package com.example.monsterburgerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.monsterburgerapp.model.Producto
import com.example.monsterburgerapp.model.ProductoRepository
import androidx.lifecycle.ViewModel

class ProductosListViewModel: ViewModel() {

    var productoRepository: ProductoRepository
    var productosModel = MutableLiveData<List<Producto>>()
    var productoModel = MutableLiveData<Producto>()



    init {
        productoRepository = ProductoRepository()
    }

    fun getProductosss(){
        var currentProductoList =  productoRepository.getProductosss(productosModel)

    }

    fun getBebidas(){
        var currentProductoList =  productoRepository.getBebidas(productosModel)

    }

    fun getAcompañamientos(){
        var currentProductoList =  productoRepository.getAcompañamientos(productosModel)

    }

    fun getHamburguesas(){
        var currentProductoList =  productoRepository.getHamburguesas(productosModel)

    }

    fun getProductoByBarCode(codeBar:String){

        productoRepository.getProductoByBarCode(productoModel, codeBar)

    }


}