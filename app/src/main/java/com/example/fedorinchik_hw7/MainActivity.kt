package com.example.fedorinchik_hw7

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        botNavView()
    }

    private fun botNavView() {
        bottomNav = bottom_navigation_view
        val navigationController = findNavController(R.id.fragment)
        bottomNav.setupWithNavController(navigationController)
    }
}
