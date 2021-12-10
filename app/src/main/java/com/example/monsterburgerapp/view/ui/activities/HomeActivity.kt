package com.example.monsterburgerapp.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toolbar
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
import com.example.monsterburgerapp.view.ui.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem




class HomeActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val orderFragment = OrderFragment()
    private val commentsFragment = ComentsFragment()
    private val adminFragment = AdminFragment()
    private val carritoFragment = CarritoFragment()

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)





        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(
            this,
            R.layout.activity_home
        )

        drawerLayout = binding.drawerLayout

        //////////////////





        ////////////////////77

        replaceFragment(homeFragment)

        var bottonNavigation = findViewById<BottomNavigationView>(R.id.bnvMenu)
        bottonNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navHomeFragment -> replaceFragment(homeFragment)
                R.id.navOrderFragment -> replaceFragment(carritoFragment)
                R.id.navCommentsFragment -> replaceFragment(commentsFragment)
                R.id.navAdminFragment -> replaceFragment(adminFragment)

            }
            true
        }

        //NavigationUI.setupWithNavController(binding.navView, navController)


    }



    ///////////////////////////////////////////



    ///////////////////////////////////////////





    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }


    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragContent, fragment)
            transaction.commit()

        }
    }
}


