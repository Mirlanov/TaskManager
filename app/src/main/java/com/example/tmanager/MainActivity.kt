package com.example.tmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tmanager.data.local.Pref
import com.example.tmanager.databinding.ActivityMainBinding
import com.example.tmanager.utils.showToast
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val pref: Pref by lazy {
        Pref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNav: BottomNavigationView = binding.navView
        showToast("Hello")
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (!pref.isShowed())
            navController.navigate(R.id.onBoardingFragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.taskFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNav.setupWithNavController(navController)

        val fragmentWithoutBottomNav = setOf(
            R.id.onBoardingFragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (fragmentWithoutBottomNav.contains(destination.id)) {
                bottomNav.isVisible = false
                supportActionBar?.hide()
            } else {
                bottomNav.isVisible = true
                supportActionBar?.show()
            }

        }
    }
    //Worked
}