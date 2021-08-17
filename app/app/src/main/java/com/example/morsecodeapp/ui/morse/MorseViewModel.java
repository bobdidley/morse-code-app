package com.example.morsecodeapp.ui.morse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MorseViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MorseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is morse translator fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}