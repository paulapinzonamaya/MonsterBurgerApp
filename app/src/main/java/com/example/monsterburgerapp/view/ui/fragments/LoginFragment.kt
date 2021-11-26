package com.example.monsterburgerapp.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
 * Use the [loginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class loginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var btnInicio:Button
    private lateinit var btnRegistro:Button


    private lateinit var auth: FirebaseAuth





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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email=view.findViewById(R.id.loginTextEmail)
        pass= view.findViewById(R.id.loginTextPassword)
        btnInicio= view.findViewById(R.id.btnInicioSesion)
        btnRegistro= view.findViewById(R.id.btnRegistrate)


        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser !=null){
            println("YA HAY UN USUARIO LOGUEADO")
            view.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)

        }else{
            println("NO HAY UN USUARIO LOGUEADO")
        }




        btnInicio.setOnClickListener{
            view:View ->
            println("BTN INICIO")

            signIn(view,email.text.toString(),pass.text.toString())




        }
        btnRegistro.setOnClickListener{
                    view:View ->
                println("BTN REGISTRO")

                view.findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
            }


    }

    override fun onStart() {
        super.onStart()

    }






    fun signIn (view:View,email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser

                    view.findNavController().navigate(R.id.action_loginFragment_to_homeActivity)



                    println("<<<<<<<<<<<LOGIN CON EXITO")


                } else {
                    // If sign in fails, display a message to the user.
                    println("<<<<<<LOGIN CON FALLA")
                    Toast.makeText(requireContext().applicationContext, "Usario no Valido", Toast.LENGTH_LONG).show()

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
         * @return A new instance of fragment loginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            loginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}