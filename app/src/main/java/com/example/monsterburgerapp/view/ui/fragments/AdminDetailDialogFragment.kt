package com.example.monsterburgerapp.view.ui.fragments

import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.monsterburgerapp.R
import com.example.monsterburgerapp.databinding.FragmentAdminDetailDialogBinding
import com.example.monsterburgerapp.model.DBHelper
import com.example.monsterburgerapp.model.Tables
import java.io.File

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
    private lateinit var ivPhotoUser:ImageView
    private lateinit var stringImageBase64:String

    fun newInstance(
        nombre: String,
        direccion: String,
        telefono: String,
        correo: String,
        stringBase64: String,
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

        ivPhotoUser = view.findViewById(R.id.ibPhotoAdmin)

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

        binding.ibPhotoAdmin.setOnClickListener{
            val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            retultLauncher.launch(galleryIntent)
        }
    }

    var retultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        result ->

        if (result.resultCode == Activity.RESULT_OK){

            var data: Intent? = result.data

            var imageUri = data?.data

            ivPhotoUser.setImageURI(imageUri)

            println(">>> IMAGE URI ")
            println(imageUri?.path.toString())

            var fileRealPath:String? = ""

            imageUri?.let {
                fileRealPath = getRealPathFromURI(requireContext(), it)
            }

            println(">>> RUTA REAL")
            println(fileRealPath)

            //TODO: realizar rezise para tener la imagen miniatura
            var fileImage:File = File(imageUri?.path)

            stringImageBase64 = converToBase64(fileImage)
            println(stringImageBase64)

        }
    }

    fun converToBase64(file:File):String{

        return Base64.encodeToString(file.readBytes(), Base64.DEFAULT)
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

    fun getRealPathFromURI(context: Context, uri: Uri): String? {
        when {
            // DocumentProvider
            DocumentsContract.isDocumentUri(context, uri) -> {
                when {
                    // ExternalStorageProvider
                    isExternalStorageDocument(uri) -> {
                        val docId = DocumentsContract.getDocumentId(uri)
                        val split = docId.split(":").toTypedArray()
                        val type = split[0]
                        // This is for checking Main Memory
                        return if ("primary".equals(type, ignoreCase = true)) {
                            if (split.size > 1) {
                                Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                            } else {
                                Environment.getExternalStorageDirectory().toString() + "/"
                            }
                            // This is for checking SD Card
                        } else {
                            "storage" + "/" + docId.replace(":", "/")
                        }
                    }
                    isDownloadsDocument(uri) -> {
                        val fileName = getFilePath(context, uri)
                        if (fileName != null) {
                            return Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName
                        }
                        var id = DocumentsContract.getDocumentId(uri)
                        if (id.startsWith("raw:")) {
                            id = id.replaceFirst("raw:".toRegex(), "")
                            val file = File(id)
                            if (file.exists()) return id
                        }
                        val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id))
                        return getDataColumn(context, contentUri, null, null)
                    }
                    isMediaDocument(uri) -> {
                        val docId = DocumentsContract.getDocumentId(uri)
                        val split = docId.split(":").toTypedArray()
                        val type = split[0]
                        var contentUri: Uri? = null
                        when (type) {
                            "image" -> {
                                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            }
                            "video" -> {
                                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                            }
                            "audio" -> {
                                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                            }
                        }
                        val selection = "_id=?"
                        val selectionArgs = arrayOf(split[1])
                        return getDataColumn(context, contentUri, selection, selectionArgs)
                    }
                }
            }
            "content".equals(uri.scheme, ignoreCase = true) -> {
                // Return the remote address
                return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(context, uri, null, null)
            }
            "file".equals(uri.scheme, ignoreCase = true) -> {
                return uri.path
            }
        }
        return null
    }

    fun getDataColumn(context: Context, uri: Uri?, selection: String?,
                      selectionArgs: Array<String>?): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(
            column
        )
        try {
            if (uri == null) return null
            cursor = context.contentResolver.query(uri, projection, selection, selectionArgs,
                null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }


    fun getFilePath(context: Context, uri: Uri?): String? {
        var cursor: Cursor? = null
        val projection = arrayOf(
            MediaStore.MediaColumns.DISPLAY_NAME
        )
        try {
            if (uri == null) return null
            cursor = context.contentResolver.query(uri, projection, null, null,
                null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }
}