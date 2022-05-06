package com.example.redditcloneapp.ui.community;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.adapters.PostAdapter;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Mokap;

public class CommunityPostsFragment extends ListFragment {

public static CommunityPostsFragment newInstance(){return new CommunityPostsFragment();}
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
        CommunityPostAdapter adapter = new CommunityPostAdapter(getActivity(),((CommunityActivity)getActivity()).getUser(),((CommunityActivity)getActivity()).getCommunity());
        setListAdapter(adapter);
        System.out.println("TESTAD"+ Mokap.getPostsForCommunity(((CommunityActivity)getActivity()).getCommunity()));
    }
}

