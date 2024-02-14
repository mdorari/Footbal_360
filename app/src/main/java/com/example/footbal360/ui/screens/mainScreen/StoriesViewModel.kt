package com.example.footbal360.ui.screens.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footbal360.data.FootballRepository
import com.example.footbal360.data.Result
import com.example.footbal360.data.model.story.Stories
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StoriesViewModel(
    private val footballRepository: FootballRepository
):ViewModel() {
    private val _stories = MutableStateFlow<Stories>(Stories(count = 0, results = emptyList()))
    val stories = _stories.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        getStories()
    }

    fun getStories(){
        viewModelScope.launch {
            footballRepository.getStories().collectLatest {result->
                when(result){
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
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

}












