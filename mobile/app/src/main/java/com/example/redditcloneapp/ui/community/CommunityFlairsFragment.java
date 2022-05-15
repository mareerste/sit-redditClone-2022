package com.example.redditcloneapp.ui.community;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditcloneapp.R;

public class CommunityFlairsFragment extends ListFragment {

    public static CommunityFlairsFragment newInstance(){return new CommunityFlairsFragment();}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CommunityFlairsAdapter adapter = new CommunityFlairsAdapter(getActivity(), ((CommunityActivity)getActivity()).getCommunity());
        setListAdapter(adapter);
    }
}