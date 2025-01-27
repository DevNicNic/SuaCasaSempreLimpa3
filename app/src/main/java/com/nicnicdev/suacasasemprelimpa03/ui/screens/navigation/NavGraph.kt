package com.nicnicdev.suacasasemprelimpa03.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nicnicdev.suacasasemprelimpa03.ui.screens.BathroomTipsScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.BedroomTipsScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.KitchenTipsScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.LaundyTipsScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.MyFirstScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.OptionsScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.RegisterScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.RoomTipsScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.ScheduleTasksScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.ScheduleWeeklyTasksScreen
import com.nicnicdev.suacasasemprelimpa03.ui.screens.TipsHomeScreen

sealed class Screen (val route: String){
    data object Main : Screen ("main_screen")
    data object Register : Screen ("register_screen")
    data object Options : Screen ("options_screen")
    data object TipsHome : Screen ( "tips_home_screen")
    data object Shedule : Screen ("shedule_taks_screen")
    data object ScheduleWeeklyTask : Screen ("schedule_weekly_task_screen")


    sealed class Category(route: String): Screen(route) {
        data object Kitchen : Category ("kitchen_tips_screen")
        data object Bathroom : Category ( "bathoom_tips_screen")
        data object Bedroom : Category ( "bedroom_tips_screen")
        data object Laundry : Category ( "laundry_tips_screen")
        data object Room: Category ( "room_tips_screen")
    }
}

@Composable
fun SetupNavGraph (navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route //primeira tela
    ) {
        composable(route = Screen.Main.route) {
            MyFirstScreen(navController = navController) // tela principal com navegação
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController) // tela cadastro
        }
        composable(route = Screen.Options.route) {
            OptionsScreen(navController = navController) //terceira tela
        }
        composable(route = Screen.TipsHome.route) {
            TipsHomeScreen(
                userName = "Nicole",
                onCategoryClick = { category ->

                    when (category) {
                        "Cozinha" -> navController.navigate(Screen.Category.Kitchen.route)
                        "Banheiro" -> navController.navigate(Screen.Category.Bathroom.route)
                        "Quarto" -> navController.navigate(Screen.Category.Bedroom.route)
                        "Lavanderia" -> navController.navigate(Screen.Category.Laundry.route)
                        "Sala" -> navController.navigate(Screen.Category.Room.route)

                    }
                }
            )
        }
        composable(route = Screen.Shedule.route) {
            ScheduleTasksScreen(userName = "Nicole",  onBackClick = {}) // tela de agendamento
        }
        composable(route = Screen.Category.Kitchen.route) {
            KitchenTipsScreen (onBackClik = {navController.popBackStack()})
        }
        composable(route = Screen.Category.Bathroom.route) {
            BathroomTipsScreen(onBackClik = {navController.popBackStack()} )
        }
        composable(route = Screen.Category.Bedroom.route) {
            BedroomTipsScreen (onBackClik = {navController.popBackStack()})
        }
        composable (route = Screen.Category.Laundry.route) {
            LaundyTipsScreen ( onBackClik = {navController.popBackStack()})
        }
        composable(route = Screen.Category.Room.route) {
            RoomTipsScreen ( onBackClik = {navController.popBackStack()})
        }
        composable (route = Screen.ScheduleWeeklyTask.route) {
            ScheduleWeeklyTasksScreen(userName = "Nicole",  onBackClick = {})

            }
        }
    }







