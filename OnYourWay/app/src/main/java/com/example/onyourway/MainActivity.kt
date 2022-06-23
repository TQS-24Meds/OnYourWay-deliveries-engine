package com.example.onyourway

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.onyourway.databinding.ActivityMainBinding
import com.example.onyourway.room.DeliveryApplication
import com.example.onyourway.room.DeliveryViewModel
import com.example.onyourway.room.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var perms: ActivityRecognitionPermission
    private val deliveryViewModel: DeliveryViewModel by viewModels {
        ViewModelFactory ((application as DeliveryApplication).repository)
    }
    private val sharedPrefFile = "kotlinsharedpreference"
    var prof : profile = profile()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView =findViewById<BottomNavigationView>(R.id.navView)
        bottomNavigationView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(binding.navView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.signUp ||destination.id == R.id.login_frag ) {

                bottomNavigationView.visibility = View.GONE
            } else {

                bottomNavigationView.visibility = View.VISIBLE
            }
        }
        perms = ActivityRecognitionPermission(this)
        perms.askPermission()
        perms.askCoarsePermission()
        perms.askFinePermission()
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}