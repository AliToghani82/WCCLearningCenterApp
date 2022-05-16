package com.example.learningcenterappandroid.admin.active_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActiveAdminViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ActiveAdminViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}