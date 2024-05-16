package com.example.footbal360.ui.screens.videos

import com.example.footbal360.data.model.sections.Post


sealed class VideosScreenEvent {
    data class OnBottomNavbarItemClick(val route: String) : VideosScreenEvent()
    data class OnPostClick(val post: Post) :VideosScreenEvent()
}