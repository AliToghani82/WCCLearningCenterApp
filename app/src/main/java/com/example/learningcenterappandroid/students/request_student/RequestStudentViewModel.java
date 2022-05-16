package com.example.learningcenterappandroid.students.request_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RequestStudentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RequestStudentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}