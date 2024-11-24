package com.nicnicdev.suacasasemprelimpa03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nicnicdev.suacasasemprelimpa03.ui.screens.MyFirstScreen

import com.nicnicdev.suacasasemprelimpa03.ui.theme.SuaCasaSempreLimpa03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuaCasaSempreLimpa03Theme {
                MyFirstScreen()
            }
        }
    }
}

