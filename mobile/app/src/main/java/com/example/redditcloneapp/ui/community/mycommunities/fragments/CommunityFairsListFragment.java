package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.ui.community.CommunityFlairsAdapter;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;

public class CommunityFairsListFragment extends ListFragment {
    private Community community;

    public CommunityFairsListFragment(Community community) {
        this.community = community;
    }

    public static CommunityFairsListFragment newInstance(Community community){return new CommunityFairsListFragment(community);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CommunityFlairsEditAdapter adapter = new CommunityFlairsEditAdapter(getActivity(),community);
        setListAdapter(adapter);
    }
}