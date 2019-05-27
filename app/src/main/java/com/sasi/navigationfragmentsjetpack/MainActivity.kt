package com.sasi.navigationfragmentsjetpack

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Getting the Navigation Controller
        navController = Navigation.findNavController(this, R.id.fragment)

        //Setting the navigation controller to Bottom Nav
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(bottomNav, navController!!)

        //Setting up the action bar
        //NavigationUI.setupActionBarWithNavController(this, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }

            Toast.makeText(this@MainActivity, "Navigated to $dest",
                    Toast.LENGTH_SHORT).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}
