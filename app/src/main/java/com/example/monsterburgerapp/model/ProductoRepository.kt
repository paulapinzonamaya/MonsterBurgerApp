package com.example.monsterburgerapp.model

class ProductoRepository {


    private var productos = listOf<Producto>(
        Producto(1,"Albondigas",16000,"Deliciosas albondigas con queso al horno","https://drive.google.com/file/d/1Be7M5wlm7yFX62YN1O0rMaKvQqK3kjwE/view"),
        Producto(2,"Empanadas",23000,"Fiesta de empanadas","https://drive.google.com/file/d/1Be7M5wlm7yFX62YN1O0rMaKvQqK3kjwE/view"),
        Producto(3,"Totopos",17000,"Fiesta de totopos","https://drive.google.com/file/d/1Be7M5wlm7yFX62YN1O0rMaKvQqK3kjwE/view")
    )

    fun getProductos():List<Producto>{


        return productos
    }

    fun findByIds(productosIds:List<Int>):List<Producto>{

        return productos.filter{p-> productosIds.contains(p.id)}

    }
}