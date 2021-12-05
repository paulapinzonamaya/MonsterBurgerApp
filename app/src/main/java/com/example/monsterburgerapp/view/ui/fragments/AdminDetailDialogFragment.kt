package com.example.monsterburgerapp.view.ui.fragments

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.monsterburgerapp.R
import com.example.monsterburgerapp.databinding.FragmentAdminDetailDialogBinding
import com.example.monsterburgerapp.model.DBHelper
import com.example.monsterburgerapp.model.Tables

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdminDetailDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminDetailDialogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var nombre:String
    private lateinit var direccion:String
    private lateinit var telefono:String
    private lateinit var correo:String

    private lateinit var informacionDBHelper: DBHelper

    fun newInstance(
        nombre:String,
        direccion:String,
        telefono:String,
        correo:String,
    ):AdminDetailDialogFragment {

        val f = AdminDetailDialogFragment()

        val args = Bundle()
        args.putString("nombre", nombre)
        args.putString("direccion", direccion)
        args.putString("telefono", telefono)
        args.putString("correo", correo)

        f.arguments = args

        return f
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            nombre = it.getString("nombre").toString()
            direccion = it.getString("direccion").toString()
            telefono = it.getString("telefono").toString()
            correo = it.getString("correo").toString()

        }

        informacionDBHelper = DBHelper(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var binding = FragmentAdminDetailDialogBinding.bind(view)

        binding.ibPhotoAdmin.setImageResource(R.drawable.ic_baseline_person_24)
        binding.etNameAdmin.setText(nombre)
        binding.etAddressAdmin.setText(direccion)
        binding.etPhoneAdmin.setText(telefono)
        binding.etEmailAdmin.setText(correo)

        //Paso 10. Configurando el almacenamiento de valores
        binding.btSaveAdmin.setOnClickListener {

            if (binding.etNameAdmin.text.isNotEmpty() &&
                binding.etAddressAdmin.text.isNotEmpty() &&
                binding.etPhoneAdmin.text.isNotEmpty() &&
                binding.etEmailAdmin.text.isNotEmpty()) {


                informacionDBHelper.edit(1,
                    binding.etNameAdmin.text.toString(),
                    binding.etAddressAdmin.text.toString(),
                    binding.etPhoneAdmin.text.toString(),
                    binding.etEmailAdmin.text.toString())

                //Paso 11. Limpiando los campos editables
                Toast.makeText(requireContext(), "se guardaron los datos", Toast.LENGTH_LONG).show()
                binding.etNameAdmin.text.clear()
                binding.etAddressAdmin.text.clear()
                binding.etPhoneAdmin.text.clear()
                binding.etEmailAdmin.text.clear()

                val db: SQLiteDatabase = informacionDBHelper.readableDatabase
                val cursor = db.rawQuery("SELECT * FROM" + Tables.information["TABLE_NAME"], null )

                if (cursor.moveToFirst()) {
                    do {
                        binding.etNameAdmin.setText(cursor.getString(1).toString())
                        binding.etAddressAdmin.setText(cursor.getString(2).toString())
                        binding.etPhoneAdmin.setText(cursor.getString(3).toString())
                        binding.etEmailAdmin.setText(cursor.getString(4).toString())
                    }while (cursor.moveToNext())
                }
            }else {
                Toast.makeText(requireContext(), "Error al guardar, complete todos los campos", Toast.LENGTH_LONG).show()
            }
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AdminDetailDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AdminDetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}