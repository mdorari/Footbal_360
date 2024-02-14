package com.example.footbal360.ui.screens.mainScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footbal360.data.FootballRepository
import com.example.footbal360.data.Result
import com.example.footbal360.data.model.sections.AllPosts
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FootballViewModel(
    private val footballRepository: FootballRepository
) : ViewModel() {

    private val _sliderPosts =
        MutableStateFlow<AllPosts>(AllPosts(count = 0, data = emptyList(), limit = 5, offset = 0))
    val sliderPosts = _sliderPosts.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()


    init {
        getSliderPosts()
//        getAllPosts()
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

//    fun getAllPosts() {
//        viewModelScope.launch {
//            footballRepository.getAllPosts().collectLatest { result ->
//                when (result) {
//                    is Result.Error -> {
//                        _showErrorToastChannel.send(true)
//                    }
//
//                    is Result.Success -> {
//                        result.data?.let { allPosts ->
//                            _allPosts.update { allPosts }
//                        }
//                    }
//                }
//            }
//        }
//    }
}












