package com.zeoflow.view.binding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

public abstract class BindDialogFragment<T extends ViewDataBinding, S extends ViewBinding> extends DialogFragment implements ViewInterface<T, S>, OnViewModelInitializedCallback<S>
{
    private final ViewModelBindingHelper<S, T> mViewModelBindingHelper = new ViewModelBindingHelper<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        mViewModelBindingHelper.onCreate(this, savedInstanceState, this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mViewModelBindingHelper.onCreate(this, savedInstanceState, this);
        return mViewModelBindingHelper.getBinding().getRoot();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mViewModelBindingHelper.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mViewModelBindingHelper.onPause();
    }

    @Override
    public void onDestroyView()
    {
        mViewModelBindingHelper.onDestroyView(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        mViewModelBindingHelper.onDestroy(this);
        super.onDestroy();
    }

    @Nullable
    @Override
    public Bundle getBundle()
    {
        return getArguments();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        mViewModelBindingHelper.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public Context getContext()
    {
        return getActivity();
    }

    public T getBinding()
    {
        return mViewModelBindingHelper.getBinding();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModelBindingHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mViewModelBindingHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onViewBindingInitialised(S viewModel)
    {
    }

    public S getViewModel()
    {
        return mViewModelBindingHelper.getViewBinding();
    }

    protected void setupViewModel(@LayoutRes int layoutResourceId, Class<S> viewModelClass)
    {
        mViewModelBindingHelper.setup(layoutResourceId, viewModelClass);
    }
}
