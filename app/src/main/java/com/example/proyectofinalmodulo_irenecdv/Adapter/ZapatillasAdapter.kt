package com.example.proyectofinalmodulo_irenecdv.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalmodulo_irenecdv.R
import com.example.proyectofinalmodulo_irenecdv.modelo.Zapatillas

class ZapatillasAdapter(private var ZapatillasList:ArrayList<Zapatillas>):RecyclerView.Adapter<ZapatillasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZapatillasViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_zapatillas, parent, false)
        return ZapatillasViewHolder(itemView)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ZapatillasViewHolder, position: Int) {


      val item = ZapatillasList[position]
        holder.render(item)

    }

    override fun getItemCount(): Int {
        return ZapatillasList.size
    }


}