package com.grupo.controlze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.grupo.controlze.ui.navigation.AppNavigation
import com.grupo.controlze.ui.theme.ControlzeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ControlzeTheme {
                AppNavigation()
            }
        }
    }
}
