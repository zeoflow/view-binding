package com.zeoflow.view.binding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

/**
 * An interface representing View in MVVM architecture
 * <p>
 * This interface should be implemented by Activity/Fragment representing a screen
 *
 * @param <T> Generated Data Binding layout class
 */
public interface ViewInterface<T extends ViewDataBinding, S extends ViewBinding>
{
    Context getContext();

    T getBinding();

    Activity getActivity();

    Bundle getBundle();

    void startActivityForResult(Intent intent, int requestCode);

    void startActivityForResult(Intent intent, int requestCode, Bundle bundle);
}
