package com.example.monsterburgerapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monsterburgerapp.R
import com.example.monsterburgerapp.model.Comentario
import com.example.monsterburgerapp.model.Producto
import com.example.monsterburgerapp.view.ui.fragments.OrderDetailDialogFragment
import com.squareup.picasso.Picasso


class ProductoAdapter (val productoList:List<Producto>, val fragmentManager: FragmentManager ): RecyclerView.Adapter<ProductoAdapter.ProductoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {

        var layoutInflater= LayoutInflater.from(parent.context)

        return ProductoHolder(layoutInflater.inflate(R.layout.item_products, parent, false), fragmentManager)

    }

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {
        holder.render(productoList[position])
    }

    override fun getItemCount(): Int {
        return productoList.size
    }



    class ProductoHolder(val view: View,val fragmentManager: FragmentManager): RecyclerView.ViewHolder(view){

        fun render(producto: Producto){

            var card_view_productos = view.findViewById<CardView>(R.id.card_view_productos)
            var itemImageProductos = view.findViewById<ImageView>(R.id.itemImageProductos)
            var itemtitulo = view.findViewById<TextView>(R.id.itemtitulo)
            var precioProducto = view.findViewById<TextView>(R.id.precioProducto)
            var tvInventario = view.findViewById<TextView>(R.id.tvInventario)



           // itemImageProductos.setImageResource(R.drawable.ic_baseline_person_24)
            //

            if (producto.imageUrl.isEmpty()) {
                itemImageProductos.setImageResource(R.drawable.ic_baseline_fastfood_24);
            } else{
                Picasso.get().load(producto.imageUrl).into(itemImageProductos);
            }


            itemtitulo.text = producto.nombre
            precioProducto.text = producto.precio.toString()
            tvInventario.text = producto.inventario.toString() + " Unid"



            card_view_productos.setOnClickListener{
                    view:View->
                var dialogFragment =  OrderDetailDialogFragment().newInstance(

                    producto.id,
                    producto.nombre,
                    producto.precio,
                    producto.descripcion,
                    producto.imageUrl,
                    producto.inventario
                )
                dialogFragment.show(fragmentManager, "prueba")

            }

        }


    }

}

