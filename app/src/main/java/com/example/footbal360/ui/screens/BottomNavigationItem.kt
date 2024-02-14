package com.example.footbal360.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: String,
    val unselectedIcon: String,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val route: String
)

fun getBottomNavigationItems(): List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(
            title = "مسابقه",
            selectedIcon = "\uE96A",
            unselectedIcon = "\uE96B",
            hasNews = false,
            route = Screens.COMPETITION.name),
        BottomNavigationItem(
            title = "لیگ ها",
            selectedIcon = "\uE97F",
            unselectedIcon = "\uE8E4",
            hasNews = false,
            route = Screens.LEAGUES.name),
        BottomNavigationItem(
            title = "نتایج زنده",
            selectedIcon = "\uE980",
            unselectedIcon = "\uE981",
            hasNews = false,
            route = Screens.MATCHES.name),
        BottomNavigationItem(
            title = "ویدیوها",
            selectedIcon = "\uE9AC",
            unselectedIcon = "\uE9AD",
            hasNews = false,
            route = Screens.VIDEOS.name),
        BottomNavigationItem(
            title = "خانه",
            selectedIcon = "\uE974",
            unselectedIcon = "\uE975",
            hasNews = false,
            route = Screens.MAIN.name)
    )
}