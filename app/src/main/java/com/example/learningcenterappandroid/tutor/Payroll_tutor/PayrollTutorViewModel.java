package com.example.learningcenterappandroid.tutor.Payroll_tutor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PayrollTutorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PayrollTutorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}