package com.example.footbal360.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footbal360.data.FootballRepository
import com.example.footbal360.data.Result
import com.example.footbal360.data.model.sections.AllPosts
import com.example.footbal360.data.model.sections.Data
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FootballViewModel(
    private val footballRepository: FootballRepository
):ViewModel() {

    private val _allPosts = MutableStateFlow<AllPosts>(AllPosts(count = 0, data = emptyList(), limit = 5, offset = 0))
    val allPosts = _allPosts.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        getExplorerPosts()
//        getAllPosts()
    }


    fun getExplorerPosts(){
        viewModelScope.launch {
            footballRepository.getExplorerPosts().collectLatest {result->
                when(result){
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let { explorerPosts->
                            _allPosts.update { explorerPosts }
                        }
                    }
                }
            }
        }
    }

    fun getAllPosts() {
        viewModelScope.launch {
            footballRepository.getAllPosts().collectLatest { result ->
                when(result){
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let { allPosts ->
                            _allPosts.update { allPosts }
                        }
                    }
                }
            }
        }
    }

}












