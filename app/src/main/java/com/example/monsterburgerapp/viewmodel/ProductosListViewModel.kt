package com.example.monsterburgerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.monsterburgerapp.model.Producto
import com.example.monsterburgerapp.model.ProductoRepository
import androidx.lifecycle.ViewModel

class ProductosListViewModel: ViewModel() {

    var productoRepository: ProductoRepository
    var productosModel = MutableLiveData<List<Producto>>()


    init {
        productoRepository = ProductoRepository()
    }

    fun getProductos(){
        var currentProductoList =  productoRepository.getProductos(productosModel)

    }

    fun getBebidas(){
        var currentProductoList =  productoRepository.getBebidas(productosModel)

    }

}