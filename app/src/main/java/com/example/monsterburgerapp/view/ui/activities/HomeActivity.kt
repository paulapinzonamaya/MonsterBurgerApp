package com.example.monsterburgerapp.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.monsterburgerapp.R
import com.example.monsterburgerapp.databinding.ActivityHomeBinding
import com.example.monsterburgerapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {



    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configNav()
    }



    fun configNav(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragContent) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bnvMenu).setupWithNavController(navController)
    }
}