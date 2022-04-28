package com.example.redditcloneapp.ui.report;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReportViewModel  extends ViewModel {
    private final MutableLiveData<String> mText;

    public ReportViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Report section");
    }
    public LiveData<String> getText(){return mText;}
}
