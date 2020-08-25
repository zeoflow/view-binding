package com.zeoflow.view.binding;

import android.view.View;

import androidx.databinding.BindingAdapter;

import com.zeoflow.material.elements.textfield.TextInputEditText;
import com.zeoflow.material.elements.textfield.TextInputLayout;

public class BindingAdapters
{
    @BindingAdapter("show")
    public static void setShow(View view, boolean visible)
    {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("hide")
    public static void setHide(View view, boolean visible)
    {
        setShow(view, !visible);
    }

    @BindingAdapter("invisible")
    public static void setInvisible(View view, boolean invisible)
    {
        view.setVisibility(invisible ? View.INVISIBLE : View.VISIBLE);
    }

    @BindingAdapter("app:setTextColor")
    public static void setTextColor(TextInputEditText mTextInputEditText, int color)
    {
        mTextInputEditText.setTextColor(color);
    }

    @BindingAdapter("app:setBoxStrokeColor")
    public static void setBoxStrokeColor(TextInputLayout mTextInputLayout, int color)
    {
        mTextInputLayout.setBoxStrokeColor(color);
    }
}
