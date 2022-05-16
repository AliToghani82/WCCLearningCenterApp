package com.example.learningcenterapp.tutor.settings_tutor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsTutorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsTutorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}