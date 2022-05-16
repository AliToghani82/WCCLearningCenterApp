package com.example.learningcenterapp.tutor.requests_tutor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RequestTutorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RequestTutorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}