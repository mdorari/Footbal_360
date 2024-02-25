package com.example.footbal360.ui.screens.mainScreen

import com.example.footbal360.data.model.sections.Post


sealed class MainScreenEvent {
    object RefreshPage: MainScreenEvent()
    data class OnPostClick(val post: Post):MainScreenEvent()
}