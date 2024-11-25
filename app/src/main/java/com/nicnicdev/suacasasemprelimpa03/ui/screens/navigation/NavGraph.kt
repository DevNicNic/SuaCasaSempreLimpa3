package com.nicnicdev.suacasasemprelimpa03.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nicnicdev.suacasasemprelimpa03.ui.screens.MyFirstScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.OptionsScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.RegisterScreen

sealed class Screen (val route: String){
    data object Main : Screen ("main_screen")
    data object Register : Screen ("register_screen")
    data object Options : Screen ("options_screen")
}

@Composable
fun SetupNavGraph (navController: NavHostController) {
    NavHost(
        navController = navController ,
        startDestination = Screen.Main.route //primeira tela
    ) {
        composable ( route = Screen.Main.route  ) {
            MyFirstScreen(navController = navController) // tela principal com navegação
        }
        composable (route = Screen.Register.route){
            RegisterScreen(navController = navController) // tela cadastro
        }
        composable (route= Screen.Options.route) {
            OptionsScreen() //terceira tela
        }
    }

}