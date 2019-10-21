package com.vmadalin.core.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState: BaseViewState>(initialState: ViewState): ViewModel() {

    protected var _state: LiveData<ViewState> = MutableLiveData(initialState)
    val state: LiveData<ViewState>
        get() = _state

}
