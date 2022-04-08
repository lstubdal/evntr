package com.codex.evntr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.codex.evntr.Event.EventFragmentDirections

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var eventsButton: ImageButton
        lateinit var goingButton: ImageButton
        lateinit var profileButton: ImageButton
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        goingButton = findViewById(R.id.fav_button2) as ImageButton

        profileButton = findViewById(R.id.profile_button2) as ImageButton
        eventsButton = findViewById(R.id.events_button2) as ImageButton
        goingButton.setOnClickListener{
            navController.navigate(EventFragmentDirections.actionEventFragmentToFavoriteFragment())
        }
        profileButton.setOnClickListener{
            navController.navigate(EventFragmentDirections.actionEventFragmentToProfileFragment())
        }

        eventsButton.setOnClickListener{
            navController.navigate(EventFragmentDirections.actionEventFragmentSelf())
        }

    }
}

