package com.example.monsterburgerapp.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.monsterburgerapp.R
import com.example.monsterburgerapp.databinding.FragmentBarCodeDetailDialogBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BarCodeDetailDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BarCodeDetailDialogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var id:String? = null
    private var nombre:String? = null
    private var precio:Int? = null
    private var descripcion:String? = null
    private var imageUrl:String? = null
    private var inventario:Int? = null
    private var barCode:String? = null


    fun newInstance(
        id:String,
        nombre:String,
        precio:Int,
        descripcion:String,
        imageUrl:String,
        inventario:Int,
        barCode:String
    ): OrderDetailDialogFragment{
        val f = OrderDetailDialogFragment()

        val args = Bundle()
        args.putString("id", id)
        args.putString("nombre", nombre)
        args.putInt("precio", precio)
        args.putString("descripcion", descripcion)
        args.putString("imageUrl", imageUrl)
        args.putInt("inventario", inventario)
        args.putString("barCode", barCode)

        f.arguments = args

        return f

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")
            nombre = it.getString("nombre")
            precio = it.getInt("precio")
            descripcion = it.getString("descripcion")
            imageUrl = it.getString("imageUrl")
            inventario = it.getInt("inventario")
            barCode = it.getString("barCode")


        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bar_code_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println(">>> Estoy en BarCpdeDialog")


        var binding = FragmentBarCodeDetailDialogBinding.bind(view)
        binding.itemTitulo.text = nombre
        binding.precioProducto.text = precio.toString()
        binding.fodDetalleProducto.text = descripcion

        binding.tvBarCode.text = barCode



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BarCodeDetailDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BarCodeDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
