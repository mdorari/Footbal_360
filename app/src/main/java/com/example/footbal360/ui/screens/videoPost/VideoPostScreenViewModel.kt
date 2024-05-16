package com.example.footbal360.ui.screens.videoPost

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footbal360.data.FootballRepository
import com.example.footbal360.data.Result
import com.example.footbal360.data.model.videoPost.VideoPost
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
class VideoPostScreenViewModel @Inject constructor(
    private val footballRepository: FootballRepository,
    savedStateHandle: SavedStateHandle
//    private val postCode: String
):ViewModel(){
    private val _videoPost =
        MutableStateFlow<VideoPost>(VideoPost(count = 0, data = emptyList(), limit = 5, offset = 0))
    val videoPost = _videoPost.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val postId = savedStateHandle.get<Int>("postId") ?: 1

        Log.d("Mehrdad VideoPostVM", "postId: $postId")
        if(postId != -1){
            getPost(postId = postId)
        }
    }



    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun getPost(postId:Int){
        viewModelScope.launch {
            footballRepository.getPostByPostCode(postCode = postId).collectLatest { result->
                when(result){
                    is Result.Error -> {
                        Log.d("TAG", "getStories: ${result.message}")
                    }
                    is Result.Success -> {
                        result.data?.let { post->
                            _videoPost.update { post }
                        }
                    }
                }
            }
        }
    }
}

//class VideoViewModelFactory(private val postCode: String?):
//        ViewModelProvider.NewInstanceFactory(){
//    override fun <T : ViewModel> create(modelClass: Class<T>): T =
//        VideoPostScreenViewModel(postCode = "$postCode", footballRepository = FootballRepositoryImpl(RetrofitInstance.api)) as T
//        }