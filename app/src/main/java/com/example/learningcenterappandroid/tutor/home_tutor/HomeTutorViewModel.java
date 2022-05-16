package com.example.learningcenterappandroid.tutor.home_tutor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeTutorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeTutorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}