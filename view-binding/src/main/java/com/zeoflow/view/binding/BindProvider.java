package com.zeoflow.view.binding;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.HashMap;

/**
 * Static Singleton class providing ViewModel instances based on their unique identifier.
 * <p>
 * The instance is either created using default constructor or if it exists - retrieved from a static in-memory
 * map storing previously created instances.
 */
public class BindProvider
{
    private static BindProvider sInstance;
    /**
     * HashMap storing ViewModel instances
     */
    private final HashMap<String, ViewBinding> mViewModels;

    private BindProvider()
    {
        mViewModels = new HashMap<>();
    }

    /**
     * Static instance getter
     *
     * @return static {@link BindProvider} instance
     */
    public static BindProvider getInstance()
    {
        if (sInstance == null)
            sInstance = new BindProvider();
        return sInstance;
    }

    /**
     * Remove a specific ViewModel from static HashMap.
     * Call this as soon as you are sure the ViewModel won't be used anymore
     *
     * @param viewModelId Unique ViewModel ID used to store the ViewModel instance
     */
    public synchronized void removeViewModel(String viewModelId)
    {
        mViewModels.remove(viewModelId);
    }

    /**
     * Get an instance of specified ViewModel based on its unique ID. The instance will be either restored from an
     * in-memory map or created using the default constructor and put inside the map
     *
     * @param viewModelId    ViewModel ID
     * @param viewModelClass ViewModel class
     * @return ViewModel inside a wrapper containing a flag indicating if the instance was created or restored
     */
//	@SuppressWarnings("unchecked")
    @NonNull
    public synchronized <T extends ViewBinding> ViewModelWrapper<T> getViewModel(Context context, String viewModelId, @NonNull Class<T> viewModelClass)
    {
        // try to get the instance from in-memory map
        T instance = (T) mViewModels.get(viewModelId);
        if (instance != null)
            return new ViewModelWrapper<T>(instance, false);
        // if it doesn't exist, use the default constructor to create new instance
        try
        {
            instance = viewModelClass.newInstance();
            instance.setViewModelId(viewModelId);
            instance.setApplicationContext(context.getApplicationContext());
            mViewModels.put(viewModelId, instance);
            return new ViewModelWrapper<T>(instance, true);
        } catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Wrapper around ViewModel instance bearing additional information
     * such as a flag indicating if the instance was created or restored
     */
    public static class ViewModelWrapper<V extends ViewBinding>
    {
        @NonNull
        private final V mViewModel;
        private final boolean mWasCreated;

        private ViewModelWrapper(@NonNull V viewModel, boolean wasCreated)
        {
            this.mViewModel = viewModel;
            this.mWasCreated = wasCreated;
        }

        /**
         * Provides the actual wrapped ViewModel instance
         *
         * @return ViewModel instance
         */
        @NonNull
        public V getViewModel()
        {
            return mViewModel;
        }

        /**
         * Returns true if the ViewModel was instantiated and not found in the static map
         *
         * @return true if the ViewModel was instantiated and not found in the static map
         */
        public boolean wasCreated()
        {
            return mWasCreated;
        }
    }
}
