package com.example.learningcenterappandroid.students.settings_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsStudentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsStudentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}