package com.gifs.app.user.util

import com.gifs.app.user.model.Data

sealed class BaseState {
    class Success(val data: List<Data>) : BaseState()
    class Failure(val errorMsg: String) : BaseState()
    object Loading : BaseState()
}