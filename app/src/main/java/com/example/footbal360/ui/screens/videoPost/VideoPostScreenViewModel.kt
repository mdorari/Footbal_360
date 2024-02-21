package com.example.footbal360.ui.screens.videoPost

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.footbal360.data.FootballApi
import com.example.footbal360.data.FootballRepository
import com.example.footbal360.data.FootballRepositoryImpl
import com.example.footbal360.data.Result
import com.example.footbal360.data.RetrofitInstance
import com.example.footbal360.data.model.sections.AllPosts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VideoPostScreenViewModel (
    private val footballRepository: FootballRepository,
    private val postCode: String
):ViewModel(){
    private val _videoPost =
        MutableStateFlow<AllPosts>(AllPosts(count = 0, data = emptyList(), limit = 5, offset = 0))
    val videoPost = _videoPost.asStateFlow()

    init {
        getPost()
    }

    fun getPost(){
        viewModelScope.launch {
            footballRepository.getPostByPostCode(postCode = postCode).collectLatest { result->
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

class VideoViewModelFactory(private val postCode: String?):
        ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        VideoPostScreenViewModel(postCode = "$postCode", footballRepository = FootballRepositoryImpl(RetrofitInstance.api)) as T
        }