package com.example.redditcloneapp.ui.community;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Administrator;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.User;

public class CommunityModeratorsFragment extends ListFragment {

    private Community community;

    public CommunityModeratorsFragment(Community community) {
        this.community = community;
    }

    public static CommunityModeratorsFragment newInstance(Community community){return new CommunityModeratorsFragment(community);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CommunityModeratorsAdapter adapter = new CommunityModeratorsAdapter(getActivity(),community, ((CommunityActivity)getActivity()).getUser());
        setListAdapter(adapter);
    }
}