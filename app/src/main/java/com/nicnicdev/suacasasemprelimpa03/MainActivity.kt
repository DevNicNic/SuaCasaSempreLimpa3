package com.nicnicdev.suacasasemprelimpa03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.nicnicdev.suacasasemprelimpa03.ui.screens.MyFirstScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.navigation.SetupNavGraph

import com.nicnicdev.suacasasemprelimpa03.ui.theme.SuaCasaSempreLimpa03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // criando o controlador de navegação
            SuaCasaSempreLimpa03Theme {
                SetupNavGraph(navController= navController) // passando o naControler
            }
        }
    }
}

