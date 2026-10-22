package nl.frankkie.catdex.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import nl.frankkie.catdex.ui.NavDestination
import nl.frankkie.catdex.ui.viewmodel.AppViewModel

@Composable
fun MainScreen(appViewModel: AppViewModel){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavHost(
        navController = navController,
        startDestination = NavDestination.ListScreen.route,
        modifier = Modifier
    ) {
        composable(NavDestination.ListScreen.route) {
            ListScreen(appViewModel, navController)
        }
        composable(NavDestination.DetailScreen.route) {
            DetailScreen(appViewModel, navController)
        }
    }
}