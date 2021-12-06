package com.example.monsterburgerapp.model

class ProductoRepository {


    private var productos = listOf<Producto>(
        Producto(1,"Hamburguesa1",16000,"Deliciosa hamburguesa 1","https://drive.google.com/file/d/1Be7M5wlm7yFX62YN1O0rMaKvQqK3kjwE/view"),
        Producto(2,"Hamburguesa2",23000,"Deliciosa hamburguesa 2","https://drive.google.com/file/d/1Be7M5wlm7yFX62YN1O0rMaKvQqK3kjwE/view"),
        Producto(3,"Hamburguesa3",19000,"Deliciosa hamburguesa 3","https://drive.google.com/file/d/1Be7M5wlm7yFX62YN1O0rMaKvQqK3kjwE/view"),
        Producto(4,"Hamburguesa4",11000,"Deliciosa hamburguesa 4","https://drive.google.com/file/d/1Be7M5wlm7yFX62YN1O0rMaKvQqK3kjwE/view"),
        Producto(5,"Hamburguesa5",10000,"Deliciosa hamburguesa 5","https://drive.google.com/file/d/1Be7M5wlm7yFX62YN1O0rMaKvQqK3kjwE/view"),
        Producto(6,"Hamburguesa6",12000,"Deliciosa hamburguesa 6","https://drive.google.com/file/d/1Be7M5wlm7yFX62YN1O0rMaKvQqK3kjwE/view"),
        Producto(7,"Hamburguesa7",12300,"Deliciosa hamburguesa 7","https://drive.google.com/file/d/1Be7M5wlm7yFX62YN1O0rMaKvQqK3kjwE/view")
    )

    fun getProductos():List<Producto>{


        return productos
    }

    fun findByIds(productosIds:List<Int>):List<Producto>{

        return productos.filter{p-> productosIds.contains(p.id)}

    }
}