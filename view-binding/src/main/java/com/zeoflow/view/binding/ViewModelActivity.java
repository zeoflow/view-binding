package com.zeoflow.view.binding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

public abstract class ViewModelActivity<T extends ViewDataBinding, S extends ViewModel> extends AppCompatActivity implements ViewInterface<T, S>, OnViewModelInitializedCallback<S>
{
    private final ViewModelBindingHelper<S, T> mViewModelBindingHelper = new ViewModelBindingHelper<>();

    @Override
    public void onDestroy()
    {
        mViewModelBindingHelper.onDestroy(this);
        super.onDestroy();
    }

    @Override
    public Context getContext()
    {
        return getActivity();
    }

    @Override
    public Activity getActivity()
    {
        return this;
    }

    @Override
    public T getBinding()
    {
        return mViewModelBindingHelper.getBinding();
    }

    @Nullable
    @Override
    public Bundle getBundle()
    {
        if (getIntent() == null) return null;
        return getIntent().getExtras();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        mViewModelBindingHelper.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mViewModelBindingHelper.onCreate(this, savedInstanceState, this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mViewModelBindingHelper.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mViewModelBindingHelper.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
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

    public S getViewBinding()
    {
        return mViewModelBindingHelper.getViewBinding();
    }

    protected void setupViewBinding(@LayoutRes int layoutResourceId, Class<S> viewBindingClass)
    {
        mViewModelBindingHelper.setup(layoutResourceId, viewBindingClass);
    }
}
