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

//PARA OPCION 2
    fun getProductosByIds(productosIds:List<String>){
        var currentProductoList =  productoRepository.findByIds(productosIds, productosModel)

    }





    /*
    //Para opcion 1
fun getProductosByIds(productosIds:List<String>){
    var currentProductoList =  productoRepository.findByIds(productosIds)
    productosModel.postValue(currentProductoList)
}

     */


}