package com.example.dersapp.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dersapp.levels.Level1
import com.example.dersapp.levels.Level10
import com.example.dersapp.levels.Level11
import com.example.dersapp.levels.Level12
import com.example.dersapp.levels.Level13
import com.example.dersapp.levels.Level14
import com.example.dersapp.levels.Level15
import com.example.dersapp.levels.Level16
import com.example.dersapp.levels.Level17
import com.example.dersapp.levels.Level18
import com.example.dersapp.levels.Level19
import com.example.dersapp.levels.Level2
import com.example.dersapp.levels.Level20
import com.example.dersapp.levels.Level3
import com.example.dersapp.levels.Level4
import com.example.dersapp.levels.Level5
import com.example.dersapp.levels.Level6
import com.example.dersapp.levels.Level7
import com.example.dersapp.levels.Level8
import com.example.dersapp.levels.Level9
import com.example.dersapp.mainMenu.LevelScreen

sealed class Screen(val route: String) {
    object Home: Screen("levelScreen")

    object lvl1: Screen("level1/{language}"){
        fun createRoute(language: String) = "level1/$language"
    }
    object lvl2: Screen("level2/{language}"){
        fun createRoute(language: String) = "level2/$language"
    }
    object lvl3: Screen("level3/{language}"){
        fun createRoute(language: String) = "level3/$language"
    }
    object lvl4: Screen("level4/{language}"){
        fun createRoute(language: String) = "level4/$language"
    }
    object lvl5: Screen("level5/{language}"){
        fun createRoute(language: String) = "level5/$language"
    }
    object lvl6: Screen("level6/{language}"){
        fun createRoute(language: String) = "level6/$language"
    }
    object lvl7: Screen("level7/{language}"){
        fun createRoute(language: String) = "level7/$language"
    }
    object lvl8: Screen("level8/{language}"){
        fun createRoute(language: String) = "level8/$language"
    }
    object lvl9: Screen("level9/{language}"){
        fun createRoute(language: String) = "level9/$language"
    }
    object lvl10: Screen("level10/{language}"){
        fun createRoute(language: String) = "level10/$language"
    }
    object lvl11: Screen("level11/{language}"){
        fun createRoute(language: String) = "level11/$language"
    }
    object lvl12: Screen("level12/{language}"){
        fun createRoute(language: String) = "level12/$language"
    }
    object lvl13: Screen("level13/{language}"){
        fun createRoute(language: String) = "level13/$language"
    }
    object lvl14: Screen("level14/{language}"){
        fun createRoute(language: String) = "level14/$language"
    }
    object lvl15: Screen("level15/{language}"){
        fun createRoute(language: String) = "level15/$language"
    }
    object lvl16: Screen("level16/{language}"){
        fun createRoute(language: String) = "level16/$language"
    }
    object lvl17: Screen("level17/{language}"){
        fun createRoute(language: String) = "level17/$language"
    }
    object lvl18: Screen("level18/{language}"){
        fun createRoute(language: String) = "level18/$language"
    }
    object lvl19: Screen("level19/{language}"){
        fun createRoute(language: String) = "level19/$language"
    }
    object lvl20: Screen("level20/{language}"){
        fun createRoute(language: String) = "level20/$language"
    }
}

fun navigateToLevel(navController: NavController, level: Int, dil: String) {
    when (level) {
        1 -> navController.navigate(Screen.lvl1.createRoute(dil))
        2 -> navController.navigate(Screen.lvl2.createRoute(dil))
        3 -> navController.navigate(Screen.lvl3.createRoute(dil))
        4 -> navController.navigate(Screen.lvl4.createRoute(dil))
        5 -> navController.navigate(Screen.lvl5.createRoute(dil))
        6 -> navController.navigate(Screen.lvl6.createRoute(dil))
        7 -> navController.navigate(Screen.lvl7.createRoute(dil))
        8 -> navController.navigate(Screen.lvl8.createRoute(dil))
        9 -> navController.navigate(Screen.lvl9.createRoute(dil))
        10 -> navController.navigate(Screen.lvl10.createRoute(dil))
        11 -> navController.navigate(Screen.lvl11.createRoute(dil))
        12 -> navController.navigate(Screen.lvl12.createRoute(dil))
        13 -> navController.navigate(Screen.lvl13.createRoute(dil))
        14 -> navController.navigate(Screen.lvl14.createRoute(dil))
        15 -> navController.navigate(Screen.lvl15.createRoute(dil))
        16 -> navController.navigate(Screen.lvl16.createRoute(dil))
        17 -> navController.navigate(Screen.lvl17.createRoute(dil))
        18 -> navController.navigate(Screen.lvl18.createRoute(dil))
        19 -> navController.navigate(Screen.lvl19.createRoute(dil))
        20 -> navController.navigate(Screen.lvl20.createRoute(dil))
        // Add more levels as needed
        else -> {}
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "levelScreen") {
        composable(Screen.Home.route) { LevelScreen(navController, totalLevels = 20, context = LocalContext.current) }
        composable(
            route = Screen.lvl1.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level1(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl2.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level2(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl3.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level3(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl4.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level4(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl5.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level5(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl6.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level6(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl7.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level7(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl8.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level8(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl9.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level9(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl10.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level10(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl11.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level11(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl12.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level12(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl13.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level13(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl14.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level14(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl15.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level15(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl16.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level16(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl17.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level17(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl18.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level18(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl19.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level19(navController,dil ?: "0")
        }
        composable(
            route = Screen.lvl20.route,
            arguments = listOf(navArgument("language"){type= NavType.StringType})
        ) { backStackEntry ->
            val dil = backStackEntry.arguments?.getString("language")
            Level20(navController,dil ?: "0")
        }
    }
}