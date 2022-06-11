package com.example.redditcloneapp.ui.community;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;

public class CommunityFlairsFragment extends ListFragment {

    private Community community;

    public CommunityFlairsFragment(Community community) {
        this.community = community;
    }

    public static CommunityFlairsFragment newInstance(Community community){return new CommunityFlairsFragment(community);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CommunityFlairsAdapter adapter = new CommunityFlairsAdapter(getActivity(), community);
        setListAdapter(adapter);
    }
}