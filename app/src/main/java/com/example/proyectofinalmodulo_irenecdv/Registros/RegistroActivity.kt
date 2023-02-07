package com.example.proyectofinalmodulo_irenecdv.Registros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinalmodulo_irenecdv.ListadoActivity
import com.example.proyectofinalmodulo_irenecdv.NuevaActivity
import com.example.proyectofinalmodulo_irenecdv.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Registate.setOnClickListener {
            if (binding.nombre.text.isNotEmpty() && binding.Apellidos.text.isNotEmpty() && binding.Email.text.isNotEmpty()
                && binding.Password.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.Email.text.toString(),
                    binding.Password.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this, ListadoActivity::class.java).apply {
                            putExtra("nombreusuario", binding.nombre.text.toString())
                        }
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "No ha sido posible crear el usuario", Toast.LENGTH_LONG).show()
                    }
                }

            } else {
                Toast.makeText(this, "Algun campo esta vacio", Toast.LENGTH_LONG).show()
            }

        }
    }
}