package com.example.gscompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.gscompose.ui.theme.GSComposeTheme

/**
 * Main Activity, ideally here we
 * must init services and
 * methods that will be used to
 * get global configs for the App.
 * **/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GSComposeTheme {
                /**
                 * Navigation component in compose.
                 * See GSNavController class to see the
                 * navigation specifications.
                 * **/
                val navController = rememberNavController()
                GSNavController(navController = navController)
            }
        }
    }
}
