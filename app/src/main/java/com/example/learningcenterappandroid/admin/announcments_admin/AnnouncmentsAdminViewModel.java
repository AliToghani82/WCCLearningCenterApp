package com.example.learningcenterappandroid.admin.announcments_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnnouncmentsAdminViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AnnouncmentsAdminViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}