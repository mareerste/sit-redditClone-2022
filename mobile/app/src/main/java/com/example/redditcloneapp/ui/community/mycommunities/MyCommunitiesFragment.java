package com.example.redditcloneapp.ui.community.mycommunities;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Moderator;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.ui.access.SignUpActivity;
import com.example.redditcloneapp.ui.community.CommunityActivity;

public class MyCommunitiesFragment extends ListFragment {

    public static MyCommunitiesFragment newInstance(){return new MyCommunitiesFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Community community = Mokap.getUsersCommunities((Moderator) ((MainActivity)getActivity()).getUser()).get(position);
        Toast toast = Toast.makeText(getContext(),community.toString(), Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent((MainActivity)getActivity(),MyCommunityActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyCommunitiesAdapter adapter = new MyCommunitiesAdapter(getActivity(), Mokap.getUsersCommunities((Moderator) ((MainActivity)getActivity()).getUser()));
        setListAdapter(adapter);
    }
}