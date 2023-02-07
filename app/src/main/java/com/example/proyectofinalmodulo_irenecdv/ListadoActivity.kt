package com.example.proyectofinalmodulo_irenecdv


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalmodulo_irenecdv.Adapter.ZapatillasAdapter
import com.example.proyectofinalmodulo_irenecdv.databinding.ActivityListadoBinding
import com.example.proyectofinalmodulo_irenecdv.modelo.Zapatillas
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListadoActivity : ActivityMenu() {
    var db = Firebase.firestore
    lateinit var listazapatos: ArrayList<Zapatillas>
    lateinit var  zapatillasRecycler: RecyclerView
    lateinit var  binding: ActivityListadoBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        zapatillasRecycler = RecyclerView(applicationContext)
        zapatillasRecycler.layoutManager = LinearLayoutManager(this)


        listazapatos = arrayListOf()



            cargarDatos()

    }

    fun cargarDatos() {

        val db = FirebaseFirestore.getInstance()
        // Obtengo los datos de la base de datos
        db.collection("Zapatillas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Añadiendo zapatillas", "${document.id} => ${document.data}")
                    val zapas = document.toObject(Zapatillas::class.java)
                    listazapatos.add(zapas)
                    // Muestro el recyclerView
                    binding.recycler.layoutManager = LinearLayoutManager(this)
                    binding.recycler.adapter = ZapatillasAdapter(listazapatos)
                    // Vuelvo a actualizar el adapter para el borrado
                    var adapter = ZapatillasAdapter(listazapatos)
                    zapatillasRecycler.adapter = adapter
                    val position = listazapatos.indexOf(Zapatillas())
                    adapter.notifyItemRemoved(position)
                    adapter.notifyDataSetChanged()


                }


            }
            .addOnFailureListener { exception ->
                Log.w("Añadiendo Zapatillas", "Error al obtener los datos de las zapatillas.", exception)
            }


    }


}

