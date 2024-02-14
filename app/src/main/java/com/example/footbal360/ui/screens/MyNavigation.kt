package com.example.footbal360.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.footbal360.ui.screens.competition.CompetitionScreen
import com.example.footbal360.ui.screens.leagues.LeaguesScreen
import com.example.footbal360.ui.screens.mainScreen.BottomSheetPostsViewModel
import com.example.footbal360.ui.screens.mainScreen.ChipsViewModel
import com.example.footbal360.ui.screens.mainScreen.FootballViewModel
import com.example.footbal360.ui.screens.mainScreen.MainScreen
import com.example.footbal360.ui.screens.mainScreen.StoriesViewModel
import com.example.footbal360.ui.screens.matches.MatchesScreen
import com.example.footbal360.ui.screens.videos.VideosScreen
import com.example.footbal360.ui.screens.videos.VideosViewModel

@Composable
fun MyNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    footballViewModel: FootballViewModel,
    storiesViewModel: StoriesViewModel,
    bottomSheetPostsViewModel: BottomSheetPostsViewModel,
    chipsViewModel: ChipsViewModel,
    videosViewModel: VideosViewModel
) {
    NavHost(
        navController = navController, startDestination = Screens.MAIN.name
    ) {
        composable(Screens.MAIN.name) {
            MainScreen(
                paddingValues = paddingValues,
                footballViewModel = footballViewModel,
                storiesViewModel = storiesViewModel,
                bottomSheetPostsViewModel = bottomSheetPostsViewModel,
                chipsViewModel = chipsViewModel,
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




