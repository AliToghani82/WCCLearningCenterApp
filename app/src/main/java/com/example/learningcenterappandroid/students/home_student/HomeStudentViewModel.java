package com.example.learningcenterappandroid.students.home_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeStudentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeStudentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}