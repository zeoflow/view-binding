package com.zeoflow.view.binding;

interface OnViewModelInitializedCallback<T extends ViewModel>
{
    void onViewModelInitialized(T viewModel);
}
