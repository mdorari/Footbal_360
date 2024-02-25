package com.example.footbal360.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.footbal360.ui.screens.competition.CompetitionScreen
import com.example.footbal360.ui.screens.leagues.LeaguesScreen
import com.example.footbal360.ui.screens.mainScreen.MainScreenViewModel
import com.example.footbal360.ui.screens.mainScreen.MainScreen
import com.example.footbal360.ui.screens.matches.MatchesScreen
import com.example.footbal360.ui.screens.videos.VideosScreen
import com.example.footbal360.ui.screens.videos.VideosViewModel

@Composable
fun MyNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    mainScreenViewModel: MainScreenViewModel,
    videosViewModel: VideosViewModel
) {
    NavHost(
        navController = navController, startDestination = Screens.MAIN.name
    ) {
        composable(Screens.MAIN.name) {
            MainScreen(
                paddingValues = paddingValues,
                mainScreenViewModel = mainScreenViewModel,
                navController = navController
            )
        }
        composable(Screens.VIDEOS.name) {
            VideosScreen(
                paddingValues = paddingValues,
                videosViewModel = videosViewModel,
                navController = navController
            )
        }
//        composable(
//            Screens.VIDEO_POST.name + "/{data}",
//            arguments = listOf(
//                navArgument("data") { type = NavType.StringType }
//            )
//        ) { backStackEntry ->
//            VideoPostScreen(
//                navController = navController,
//                videoPostScreenViewModel = viewModel(
//                    factory = VideoViewModelFactory(backStackEntry.arguments?.getString("data"))
//                )
//            )
//        }
        composable(Screens.MATCHES.name) {
            MatchesScreen(navController = navController)
        }
        composable(Screens.LEAGUES.name) {
            LeaguesScreen(navController = navController)
        }
        composable(Screens.COMPETITION.name) {
            CompetitionScreen(navController = navController)
        }
    }
}




