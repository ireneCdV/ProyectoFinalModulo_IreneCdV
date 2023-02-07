package com.example.proyectofinalmodulo_irenecdv.Adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectofinalmodulo_irenecdv.databinding.ItemZapatillasBinding
import com.example.proyectofinalmodulo_irenecdv.modelo.Zapatillas


class ZapatillasViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemZapatillasBinding.bind(view)

    //Creo la funcion render que uso en la activity del adapter.
    fun render(zapatillasModel:Zapatillas ){
        binding.id.text = "Id: "+zapatillasModel.Id
        binding.marca.text ="Marca: "+ zapatillasModel.Marca
        binding.modelo.text ="Modelo: "+ zapatillasModel.Modelo
        binding.color.text ="Color: "+ zapatillasModel.Color
        Glide.with(binding.foto.context).load(zapatillasModel.Imagen).into(binding.foto)


    }
}



