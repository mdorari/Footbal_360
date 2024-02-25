package com.example.footbal360.ui.screens.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footbal360.data.FootballRepository
import com.example.footbal360.data.Result
import com.example.footbal360.data.model.chips.Chips
import com.example.footbal360.data.model.sections.AllPosts
import com.example.footbal360.data.model.story.Stories
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
class MainScreenViewModel @Inject constructor(
    private val footballRepository: FootballRepository
) : ViewModel() {

    private val _sliderPosts =
        MutableStateFlow<AllPosts>(AllPosts(count = 0, data = emptyList(), limit = 5, offset = 0))
    val sliderPosts = _sliderPosts.asStateFlow()

    private val _stories = MutableStateFlow<Stories>(Stories(count = 0, results = emptyList()))
    val stories = _stories.asStateFlow()

    private val _bottomPosts =
        MutableStateFlow<AllPosts>(AllPosts(count = 0, data = emptyList(), limit = 5, offset = 0))
    val bottomPosts = _bottomPosts.asStateFlow()

    private val _chips = MutableStateFlow<Chips>(Chips(data = emptyList()))
    val chips = _chips.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: MainScreenEvent){
        when(event){
            is MainScreenEvent.OnPostClick -> {
                TODO()
            }
            MainScreenEvent.RefreshPage -> {
                TODO()
            }
        }
    }

    init {
        getSliderPosts()
        getStories()
        getChips()
        getBottomPosts()
    }


    fun getSliderPosts() {
        viewModelScope.launch {
            footballRepository.getSliderPosts().collectLatest { result ->
                when (result) {
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }

                    is Result.Success -> {
                        result.data?.let { explorerPosts ->
                            _sliderPosts.update { explorerPosts }
                        }
                    }
                }
            }
        }
    }

    fun getStories(){
        viewModelScope.launch {
            footballRepository.getStories().collectLatest {result->
                when(result){
                    is Result.Error -> {
                        Log.d("TAG", "getStories: ${result.message}")
                    }
                    is Result.Success -> {
                        result.data?.let { stories ->
                            _stories.update { stories }
                        }
                    }
                }
            }
        }
    }

    fun getBottomPosts(){
        viewModelScope.launch {
            footballRepository.getBottomSheetPosts().collectLatest {result ->
                when(result){
                    is Result.Error -> {
                        Log.d("TAG", "getBottomPosts: ${result.message}")
                    }
                    is Result.Success -> {
                        result.data?.let {allPosts ->
                            _bottomPosts.update { allPosts }
                        }
                    }
                }
            }
        }
    }

    fun getChips(){
        viewModelScope.launch {
            footballRepository.getYourChips().collectLatest {result ->
                when(result){
                    is Result.Error -> {
                        Log.d("TAG", "getChips: ${result.message}")
                    }
                    is Result.Success -> {
                        result.data?.let { yourChips->
                            _chips.update { yourChips }
                        }

                    }
                }
            }
        }
    }
}












