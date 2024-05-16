package com.example.footbal360.ui.screens.videos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footbal360.data.FootballRepository
import com.example.footbal360.data.Result
import com.example.footbal360.data.model.sections.AllPosts
import com.example.footbal360.ui.screens.Routes
import com.example.footbal360.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val footballRepository: FootballRepository
) : ViewModel() {
    private val _videos =
        MutableStateFlow<AllPosts>(AllPosts(count = 0, data = emptyList(), limit = 5, offset = 0))
    val videos = _videos.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getVideos()
    }

    fun onEvent(event: VideosScreenEvent) {
        when (event) {
            is VideosScreenEvent.OnBottomNavbarItemClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }

            is VideosScreenEvent.OnPostClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.VIDEO_POST + "?postId=${event.post.code}"))
//
            }
        }
    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun getVideos() {
        viewModelScope.launch {
            footballRepository.getAllVideosPosts().collectLatest { result ->
                when (result) {
                    is Result.Error -> {

                        Log.d("Mehrdad", "getVideos: ${result.message}")
                    }

                    is Result.Success -> {
                        result.data?.let { videoPosts ->
                            _videos.update { videoPosts }

                        }
                    }
                }
            }
        }
    }

}