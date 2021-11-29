package com.example.monsterburgerapp.view.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monsterburgerapp.R
import com.example.monsterburgerapp.view.adapter.ProductoAdapter
import com.example.monsterburgerapp.viewmodel.CarritoListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarritoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarritoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: CarritoListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    var sharedPref:SharedPreferences=requireActivity().getPreferences(Context.MODE_PRIVATE)
    var carrito_ids:String? = sharedPref.getString("carrito_ids","Default")
    var carritoIdsArr:List<Int> = listOf()

    if(carrito_ids != "Default" ){
        carritoIdsArr = carrito_ids?.split(",")?.map{it.toInt()}!!

    }
        println(">>>>>CarritoIdsArr ${carritoIdsArr}")


        viewModel.getProductosByIds(carritoIdsArr)

}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    var rvProductosCarrito = view.findViewById<RecyclerView>(R.id.rvProductosCarrito)

        rvProductosCarrito.layoutManager = LinearLayoutManager(requireActivity())


        viewModel.productosModel.observe(viewLifecycleOwner){

            productos ->
            val adapter = ProductoAdapter(productos,childFragmentManager)
            rvProductosCarrito.adapter = adapter
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarritoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarritoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}