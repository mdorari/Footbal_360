package com.example.footbal360.ui.screens

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
            route = Routes.COMPETITION),
        BottomNavigationItem(
            title = "لیگ ها",
            selectedIcon = "\uE97F",
            unselectedIcon = "\uE8E4",
            hasNews = false,
            route = Routes.LEAGUES),
        BottomNavigationItem(
            title = "نتایج زنده",
            selectedIcon = "\uE980",
            unselectedIcon = "\uE981",
            hasNews = false,
            route = Routes.MATCHES),
        BottomNavigationItem(
            title = "ویدیوها",
            selectedIcon = "\uE9AC",
            unselectedIcon = "\uE9AD",
            hasNews = false,
            route = Routes.VIDEOS),
        BottomNavigationItem(
            title = "خانه",
            selectedIcon = "\uE974",
            unselectedIcon = "\uE975",
            hasNews = false,
            route = Routes.MAIN)
    )
}