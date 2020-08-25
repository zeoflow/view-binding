package com.zeoflow.view.binding.retrofit;

import com.zeoflow.view.binding.ViewBinding;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class RetrofitCallViewBinding extends ViewBinding
{
    private Map<String, Call> mCalls = new HashMap<>();

    @Override
    public void onViewModelDestroyed()
    {
        super.onViewModelDestroyed();
        clearCalls();
    }

    protected <T> void enqueueCall(String callId, Call<T> call, Callback<T> callback)
    {
        Call existingCall = mCalls.get(callId);
        if (existingCall != null)
            new Thread(existingCall::cancel).start();
        mCalls.put(callId, call);
        call.enqueue(callback);
    }

    protected void clearCalls()
    {
        for (String callId : mCalls.keySet())
        {
            new Thread(Objects.requireNonNull(mCalls.get(callId))::cancel).start();
        }
        mCalls.clear();
    }
}
