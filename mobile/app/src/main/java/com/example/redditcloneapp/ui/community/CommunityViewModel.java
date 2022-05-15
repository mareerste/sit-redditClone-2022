package com.example.redditcloneapp.ui.community;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.redditcloneapp.R;

public class CommunityViewModel extends ViewModel {

    private final MutableLiveData<Integer> mText;

    public CommunityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(R.string.my_communities);
    }

    public LiveData<Integer> getText() {
        return mText;
    }
}