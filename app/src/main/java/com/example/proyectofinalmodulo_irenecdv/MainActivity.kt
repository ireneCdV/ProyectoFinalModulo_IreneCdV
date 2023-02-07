package com.example.proyectofinalmodulo_irenecdv


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinalmodulo_irenecdv.Registros.RegistroActivity
import com.example.proyectofinalmodulo_irenecdv.databinding.ActivityMainBinding
import com.example.proyectofinalmodulo_irenecdv.modelo.Zapatillas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.BIniciarSesion.setOnClickListener {
            //Al pulsar sobre el boton INICIAR SESION, comprobamos que las credenciales sean correctas pasandole a Firebase el correo y la contrasema introducida
            login()
        }
        //Cuando pulsemos sobre el boton registrarse accederemos a la activity correspondiente para el registro.
        binding.Registrarse.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }


    }

    private fun login() {
        //Si el correo y la password no son campos vacios:
        if (binding.EmailAddress.text.isNotEmpty() && binding.TextPassword.text.isNotEmpty()) {
            //Iniciamos sesion con el metodo singIn y enviamos a Firebase el correo y la contraseña
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.EmailAddress.text.toString(),
                binding.TextPassword.text.toString()

            )
                .addOnCompleteListener {
                    //Si las credenciades tuvo exito:
                    if (it.isSuccessful) {
                        //Accedemos a la pantalla ListadoActivivity, y podra ver la lista de zapatillas que hay añadidas y moverse por la app atravez del menu.
                        val intent = Intent(this, ListadoActivity::class.java)
                        startActivity(intent)
                    } else {
                        //Si no avisamos al usuario que a ocurrido un problema
                        Toast.makeText(this, "Correo o password incorrecto", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        } else {
            Toast.makeText(this, "Algun campo esta vacio", Toast.LENGTH_SHORT).show()

        }


    }


}


