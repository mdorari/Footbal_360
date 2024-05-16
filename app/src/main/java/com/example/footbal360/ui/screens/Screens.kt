package com.example.footbal360.ui.screens

data class Screens (
    val route:String,
    val hasBottomNavBar:Boolean
)

/*
other approach:
sealed class Screen(val rout: String){
    object Main: Screen("main")
    object Videos: Screen("videos")
    object Matches: Screen("matches")
    ...
}

and then just use these as Screen.Home.rout
 */