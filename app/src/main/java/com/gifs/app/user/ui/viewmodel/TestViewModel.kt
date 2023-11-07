package com.gifs.app.user.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gifs.app.user.repo.TestRepo
import com.gifs.app.user.util.AppConstant
import com.gifs.app.user.util.BaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val testRepo: TestRepo,
) : ViewModel() {
    private val _itemData = MutableStateFlow<BaseState>(BaseState.Loading)
    val itemData: StateFlow<BaseState> = _itemData
    private val searchInputMutableFlow: MutableStateFlow<String> = MutableStateFlow("cats")
    val searchInputData = searchInputMutableFlow.asStateFlow()

    init {
        getData(searchInputMutableFlow.value)
    }

    fun getData(query: String) {
        viewModelScope.launch {
            try {
                val response = testRepo.getGifs(AppConstant.API_KEY, query)
                _itemData.value = BaseState.Success(response.data)
            } catch (e: Exception) {
                _itemData.value = BaseState.Failure(e.message.toString())
            }
        }
    }
}