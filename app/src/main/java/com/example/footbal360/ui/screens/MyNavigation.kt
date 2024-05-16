package com.example.footbal360.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.footbal360.ui.screens.competition.CompetitionScreen
import com.example.footbal360.ui.screens.leagues.LeaguesScreen
import com.example.footbal360.ui.screens.mainScreen.MainScreen
import com.example.footbal360.ui.screens.matches.MatchesScreen
import com.example.footbal360.ui.screens.videoPost.VideoPostScreen
import com.example.footbal360.ui.screens.videos.VideosScreen

@Composable
fun MyNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
//    mainScreenViewModel: MainScreenViewModel,
//    videosViewModel: VideosViewModel,
//    videoPostScreenViewModel: VideoPostScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MAIN
    ) {
        composable(Routes.MAIN) {
            MainScreen(
                paddingValues = paddingValues,
//                viewModel = mainScreenViewModel,
                navController = navController,
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(route = Routes.VIDEOS) {
            VideosScreen(
                paddingValues = paddingValues,
//                videosViewModel = videosViewModel,
                navController = navController,
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(
            route = Routes.VIDEO_POST + "?postId={postId}",
            arguments = listOf(
                navArgument(name = "postId") {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
        ) {
            VideoPostScreen(
                paddingValues = paddingValues,
//                navController = navController,
//                videoPostScreenViewModel = videoPostScreenViewModel,
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(Routes.MATCHES) {
            MatchesScreen(navController = navController)
        }
        composable(Routes.LEAGUES) {
            LeaguesScreen(navController = navController)
        }
        composable(Routes.COMPETITION) {
            CompetitionScreen(navController = navController)
        }
    }
}




