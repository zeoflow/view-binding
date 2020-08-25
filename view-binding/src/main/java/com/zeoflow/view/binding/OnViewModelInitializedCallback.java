package com.zeoflow.view.binding;

interface OnViewModelInitializedCallback<T extends ViewBinding>
{
    void onViewBindingInitialised(T viewModel);
}
