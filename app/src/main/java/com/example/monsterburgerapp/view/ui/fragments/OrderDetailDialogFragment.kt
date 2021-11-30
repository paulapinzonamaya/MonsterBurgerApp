package com.example.monsterburgerapp.view.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.monsterburgerapp.R
import com.example.monsterburgerapp.databinding.FragmentOrderDetailDialogBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderDetailDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderDetailDialogFragment : DialogFragment() {

    private var id:Int?=null
    private var nombre:String?=null
    private var precio:Int?=null
    private var descripcion:String?=null
    private var imageUrl:String?=null


    fun newInstance(
        id:Int,
        nombre:String,
        precio:Int,
        descripcion:String,
        imageUrl:String
    ):OrderDetailDialogFragment{
        var f = OrderDetailDialogFragment()

        val args = Bundle()
        args.putInt("id",id)
        args.putString("nombre",nombre)
        args.putInt("precio",precio)
        args.putString("descripcion", descripcion)
        args.putString("imageUrl",imageUrl)


        f.arguments=args

        return f

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
            id = it.getInt("id")
            nombre = it.getString("nombre")
            precio = it.getInt("precio")
            descripcion=it.getString("descripcion")
            imageUrl=it.getString("imageUrl")
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var binding = FragmentOrderDetailDialogBinding.bind(view)
        binding.itemTitulo.text = nombre
        binding.fodDetalleProducto.text = descripcion
        binding.precioProducto.text = precio.toString()

        binding.fodBtBuyProduct.setOnClickListener{
                view:View ->
            println(">>>>SOY EL BOTON DE AGREGAR AL CARRITO")

            var sharedPref:SharedPreferences=requireActivity().getPreferences(Context.MODE_PRIVATE)
            val editor= sharedPref.edit()

            if(!sharedPref.contains("carrito_ids")){
                //Agregar el id de producto por primera vez
                var first_carrito_ids =id.toString()

                editor.putString("carrito_ids", first_carrito_ids)
                editor.apply()


            }else{
                //Agregue el id de producto actual

                var carrito_ids = sharedPref.getString("carrito_ids","Default")

                if(carrito_ids!= "Default"){
                    carrito_ids += ","+id.toString()
                    editor.putString("carrito_ids", carrito_ids)
                    editor.apply()


                    print("CARRITO IDS ${carrito_ids}")
                }
            }

            //dismiss()
            Toast.makeText(requireContext(),"Producto agregado al carrito", Toast.LENGTH_LONG)

        }
    }






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_detail_dialog, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderDetailDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}