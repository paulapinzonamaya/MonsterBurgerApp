package com.example.monsterburgerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterburgerapp.model.Producto
import com.example.monsterburgerapp.model.ProductoRepository

class CarritoListViewModel:ViewModel() {

    var productoRepository: ProductoRepository
    var productosModel = MutableLiveData<List<Producto>>()


    init {
        productoRepository = ProductoRepository()
    }

    fun getProductosByIds(productosIds:List<Int>){
        var currentProductoList =  productoRepository.findByIds(productosIds)
        productosModel.postValue(currentProductoList)
    }





}