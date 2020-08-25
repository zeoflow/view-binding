package com.zeoflow.view.binding

internal interface OnViewModelInitializedCallback<T : ViewBinding?> {
    fun onViewBindingInitialised(viewModel: T)
}