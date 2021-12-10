package com.example.monsterburgerapp.view.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.monsterburgerapp.R
import com.example.monsterburgerapp.model.Producto
import com.example.monsterburgerapp.viewmodel.ProductosListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions








// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() , OnMapReadyCallback {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private val viewModel: ProductosListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){

            R.id.nav_escaner->{
                Toast.makeText(requireContext(), "TODO: SCAN", Toast.LENGTH_LONG).show()
                barcodeLauncher.launch(ScanOptions())
            }
            R.id.nav_confi-> Toast.makeText(requireContext(),"TODO: search", Toast.LENGTH_LONG).show()
            R.id.nav_escaner-> Toast.makeText(requireContext(),"TODO: search", Toast.LENGTH_LONG).show()
            R.id.nav_fin-> Toast.makeText(requireContext(),"TODO: search", Toast.LENGTH_LONG).show()


        }


        return true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(p0: GoogleMap) {
        var name = "Monster Burger"
        var address = "Cra 8 b N 92a 67, Bogotá - Colombia"
        var latitude = 4.713075278323504
        var longitude = -74.09580839325278
        var zoom = 16f

        var centerMap = LatLng(latitude, longitude)
        p0?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))


        val marker = p0.addMarker(
            MarkerOptions()
                .title(name)
                .position(centerMap)
        )


    }

    /**override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val ButtonInstagram = view.findViewById<ImageButton>(R.id.ButtonInstagram)
        ButtonInstagram?.setOnClickListener() {


            var url = "https://www.instagram.com/?hl=es-la";
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            })

        }

        val ButtonTwitter = view.findViewById<ImageButton>(R.id.ButtonTwitter)
        ButtonTwitter?.setOnClickListener() {


            var url = "https://twitter.com/";
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            })

        }

        var button_entradas = view.findViewById<Button>(R.id.button_entradas)
        button_entradas.setOnClickListener {
                view:View->
            replaceFragment(OrderFragment())
        }



        var button_bebidas = view.findViewById<Button>(R.id.button_bebidas)
        button_bebidas.setOnClickListener {
                view:View->
            replaceFragment(BebidasFragment())
        }


        var button_acompanamiento = view.findViewById<Button>(R.id.button_acompanamiento)
        button_acompanamiento.setOnClickListener {
                view:View->
            replaceFragment(AcompanamientosFragment())
        }


        var button_hamburguesa = view.findViewById<Button>(R.id.button_hamburguesa)
        button_hamburguesa.setOnClickListener {
                view:View->
            replaceFragment(HamburguesasFragment())
        }





        super.onViewCreated(view, savedInstanceState)
    }



     */
    private fun replaceFragment(fragment: Fragment){

        if(fragment != null){
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragContent,fragment)
            transaction.commit()
        }
    }
///////////////////////////////////////////////////////////////////////////////




    // Register the launcher and result handler
    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {

            Toast.makeText(requireActivity(), "Cancelled", Toast.LENGTH_LONG).show()

        } else {

            Toast.makeText(
                requireActivity(),
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()


            var producto: Producto

            viewModel.getProductoByBarCode(result.contents)
            viewModel.productoModel.observe(viewLifecycleOwner){
                    producto->
                println(">>>> PRODUCTO")
                println(producto)

                var barCodeFragment = BarCodeDetailDialogFragment().newInstance(

                    producto.id,
                    producto.nombre,
                    producto.precio,
                    producto.descripcion,
                    producto.imageUrl,
                    producto.inventario,
                    producto.cod_barras

                )

                barCodeFragment.show(childFragmentManager, "BarCodeDetailDialogFragment")



            }



        }
    }




    ////////////////////////////////////////////////////////////////////////////////////////



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }





}