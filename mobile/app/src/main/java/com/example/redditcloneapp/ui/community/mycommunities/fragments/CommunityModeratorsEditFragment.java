package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.community.CommunityModeratorsAdapter;
import com.example.redditcloneapp.ui.community.CommunityModeratorsFragment;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;

public class CommunityModeratorsEditFragment extends ListFragment {

    public static CommunityModeratorsEditFragment newInstance(){return new CommunityModeratorsEditFragment();}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CommunityModeratorsAdapter adapter = new CommunityModeratorsAdapter(getActivity(),((MyCommunityActivity)getActivity()).getCommunity(),((MyCommunityActivity)getActivity()).getUser());
        setListAdapter(adapter);
    }
}