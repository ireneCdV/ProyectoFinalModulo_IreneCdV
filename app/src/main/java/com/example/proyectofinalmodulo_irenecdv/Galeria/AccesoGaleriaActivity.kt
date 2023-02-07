package com.example.proyectofinalmodulo_irenecdv.Galeria

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.proyectofinalmodulo_irenecdv.ActivityMenu
import com.example.proyectofinalmodulo_irenecdv.databinding.ActivityAccesoGaleriaBinding
import com.example.proyectofinalmodulo_irenecdv.databinding.ActivityMainBinding

class AccesoGaleriaActivity : ActivityMenu() {
    lateinit var imagen: ImageButton
    lateinit var binding: ActivityAccesoGaleriaBinding
    val pickFoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        val image = it.data?.extras?.get("data") as Bitmap
        binding.imageButton.setImageBitmap(image)
    }
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        //Devuelve la uri de la imagen seleccionada:
            uri ->
        if(uri!=null) {
            imagen.setImageURI(uri)
            //Imagen seleccionada
        }else {
            //no se ha seleccionado ninguna imagen
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAccesoGaleriaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imagen=binding.imageButton


        //Evento para la galeria
        binding.Galeria.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        //Evento para la camara
        binding.Camara.setOnClickListener{
            pickFoto.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }


    }
}


