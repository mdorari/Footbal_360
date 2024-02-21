package com.example.footbal360.ui.screens

enum class Screens {
    MAIN,
    VIDEOS,
    MATCHES,
    LEAGUES,
    COMPETITION,
    VIDEO_POST,
    NORMAL_POST,
    STORY
}


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