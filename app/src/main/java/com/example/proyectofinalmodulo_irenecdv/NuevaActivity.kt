package com.example.proyectofinalmodulo_irenecdv

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalmodulo_irenecdv.Adapter.ZapatillasAdapter
import com.example.proyectofinalmodulo_irenecdv.databinding.ActivityNuevaBinding
import com.example.proyectofinalmodulo_irenecdv.modelo.Zapatillas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NuevaActivity : ActivityMenu() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityNuevaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db= FirebaseFirestore.getInstance()
        //Boton cerrar sesion, nos cierra la sesion de firebase y nos manda a la mainActivity
        binding.BCerrarSesion.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }

        //Editar alguna zapatilla atravez de su id
        binding.editarzapa.setOnClickListener {
            db.collection("Zapatillas")
                .whereEqualTo("Id", binding.Id.text.toString())
                .get().addOnSuccessListener {
                    it.forEach {
                        binding.Id.setText(it.get("id") as String?)
                        binding.marca.setText(it.get("Marca") as String?)
                        binding.modelo.setText(it.get("Modelo") as String?)
                        binding.color.setText(it.get("Color") as String?)

                    }
                    //Toast.makeText(this, "Zapatilla editada correctamente", Toast.LENGTH_SHORT).show()

                }

        }


        binding.eliminarzapa.setOnClickListener{
            db.collection("Zapatillas")
                .document(binding.Id.text.toString())
                .delete()
            //Toast.makeText(this, "Zapatilla eliminada correctamente", Toast.LENGTH_SHORT).show()
            actualizarDatos()
        }

        binding.guardarzapa.setOnClickListener{
            if(!binding.Id.text.toString().isNullOrEmpty()&&!binding.marca.text.toString().isNullOrEmpty()&& !binding.modelo.text.toString().isNullOrEmpty()&&!binding.color.text.toString().isNullOrEmpty()) {
                db.collection("Zapatillas")
                    .document(binding.Id.text.toString())
                    .set(
                        mapOf(
                            "Id" to binding.Id.text.toString(),
                            "Marca" to binding.marca.text.toString(),
                            "Modelo" to binding.modelo.text.toString(),
                            "Color" to binding.color.text.toString()
                        )

                    )
                Toast.makeText(this, "Zapatilla guardada correctamente", Toast.LENGTH_SHORT).show()

                actualizarDatos()


            }else {
                Toast.makeText(this, "Algun campo esta vacio", Toast.LENGTH_LONG).show()}
        }


    }

    fun actualizarDatos() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("Zapatillas").get()
            .addOnSuccessListener { result ->
                val datos = result.toObjects(Zapatillas::class.java)
                recyclerView.adapter = ZapatillasAdapter(datos as ArrayList<Zapatillas>)
            }
    }
}