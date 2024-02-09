package com.example.footbal360.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footbal360.data.FootballRepository
import com.example.footbal360.data.Result
import com.example.footbal360.data.model.chips.Chips
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


const val TAG = "Football360"
class ChipsViewModel(
    private val footballRepository: FootballRepository
):ViewModel() {
    private val _chips = MutableStateFlow<Chips>(Chips(data = emptyList()))
    val chips = _chips.asStateFlow()

    init {
        getChips()
    }

    fun getChips(){
        viewModelScope.launch {
            footballRepository.getYourChips().collectLatest {result ->
                when(result){
                    is Result.Error -> {
                        Log.d(TAG, "getChips: ${result.message}")
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