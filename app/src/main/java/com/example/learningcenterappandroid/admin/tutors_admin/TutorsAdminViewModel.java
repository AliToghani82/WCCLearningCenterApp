package com.example.learningcenterappandroid.admin.tutors_admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TutorsAdminViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TutorsAdminViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}