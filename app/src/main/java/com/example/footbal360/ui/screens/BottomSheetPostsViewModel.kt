package com.example.footbal360.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footbal360.data.FootballRepository
import com.example.footbal360.data.Result
import com.example.footbal360.data.model.sections.AllPosts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BottomSheetPostsViewModel(
    private val footballRepository: FootballRepository
) : ViewModel() {

    private val _bottomPosts =
        MutableStateFlow<AllPosts>(AllPosts(count = 0, data = emptyList(), limit = 5, offset = 0))
    val bottomPosts = _bottomPosts.asStateFlow()

    init {
        getBottomPosts()
    }

    fun getBottomPosts(){
        viewModelScope.launch {
            footballRepository.getBottomSheetPosts().collectLatest {result ->
                when(result){
                    is Result.Error -> {
                        Log.d("TAG", "getChips: ${result.message}")
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
}