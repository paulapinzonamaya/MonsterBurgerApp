package com.example.monsterburgerapp.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.monsterburgerapp.R
import com.example.monsterburgerapp.databinding.ActivityHomeBinding
import com.example.monsterburgerapp.databinding.ActivityMainBinding
import com.example.monsterburgerapp.view.ui.fragments.AdminFragment
import com.example.monsterburgerapp.view.ui.fragments.ComentsFragment
import com.example.monsterburgerapp.view.ui.fragments.HomeFragment
import com.example.monsterburgerapp.view.ui.fragments.OrderFragment
import com.google.android.material.bottomnavigation.BottomNavigationView



class HomeActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val orderFragment = OrderFragment()
    private val commentsFragment = ComentsFragment()
    private val adminFragment = AdminFragment()

    private lateinit var drawerLayout: DrawerLayout






    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this,
            R.layout.activity_home)

        drawerLayout = binding.drawerLayout


        replaceFragment(homeFragment)

        var bottonNavigation = findViewById<BottomNavigationView>(R.id.bnvMenu)
        bottonNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navHomeFragment -> replaceFragment(homeFragment)
                R.id.navOrderFragment -> replaceFragment(orderFragment)
                R.id.navCommentsFragment -> replaceFragment(commentsFragment)
                R.id.navAdminFragment -> replaceFragment(adminFragment)

            }
            true
        }

        //NavigationUI.setupWithNavController(binding.navView, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }


    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragContent, fragment)
            transaction.commit()

        }
    }



/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
       // configNav()

    }





    fun configNav(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragContent) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bnvMenu).setupWithNavController(navController)
    }

 */
}