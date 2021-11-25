package com.example.monsterburgerapp.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.monsterburgerapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var auth: FirebaseAuth

    lateinit var nombre: EditText
    lateinit var telefono: EditText
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var btnGuardarRegistro: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        // Initialize Firebase Auth
        auth = Firebase.auth
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nombre=view.findViewById(R.id.registroTextNombre)
        telefono=view.findViewById(R.id.registroTextNumero)
        email=view.findViewById(R.id.registroTextEmail)
        pass=view.findViewById(R.id.registroTextPassword)
        btnGuardarRegistro=view.findViewById(R.id.btnRegistrarse)


        btnGuardarRegistro.setOnClickListener{
                view:View ->
            println("BTN REGISTRARSE")

            createAccount(view,nombre.text.toString(),telefono.text.toString(),email.text.toString(), pass.text.toString())


        }
    }



    fun createAccount(view: View,nombre:String,telefono:String,email:String, password:String){

        println(email)
        println(password)
        println(nombre)
        println(telefono)


        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser

                    println("<<<<<<<<<<<CREADO CON EXITO")
                    view.findNavController().navigate(R.id.action_registroFragment_to_homeActivity)

                } else {
                    println("<<<<<<<<<<<<<<<CREACION CON FALLA")
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
         * @return A new instance of fragment RegistroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}