package com.example.proyectofinalmodulo_irenecdv


import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinalmodulo_irenecdv.Galeria.AccesoGaleriaActivity
import com.google.firebase.auth.FirebaseAuth

open class ActivityMenu: AppCompatActivity() {
    companion object{
        var actividadActual=0;
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal,menu)
        if (menu != null) {
            for (i in 0 until menu.size()) {
                if (i == actividadActual) menu.getItem(i).isEnabled = false
                else menu.getItem(i).isEnabled = true
            }
        }
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            //item zapatillas que nos dirige a la activity de listadoActivity donde podremos ver las zapatilla añadidas
            R.id.Zapatillas -> {
                val intent = Intent(this, ListadoActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                actividadActual = 0
                startActivity(intent)
                true
            }
            //item opcionesZapatillas que nos dirige a la activity de NuevaActivity que podremo introducir nuevos registros
            R.id.OpcionesZapatillas -> {
                val intent = Intent(this, NuevaActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                actividadActual = 1
                startActivity(intent)
                true
            }
            //item galeria que nos dirige a la activity de AccesoGaleriaActivity donde podremos acceder a la galeria y a la camara
            R.id.Galeria -> {
                val intent = Intent(this, AccesoGaleriaActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                actividadActual = 2
                startActivity(intent)
                true
            }
            //item salir que nos dirige a la activity principal cerrando sesion.
            R.id.salir -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }








}