package com.example.redditcloneapp.ui.community.mycommunities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityBasicInfoFragment;

public class MyCommunityActivity extends AppCompatActivity {

    Community community;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_community);
        community = (Community) getIntent().getSerializableExtra("community");
        FragmentTransition.to(CommunityBasicInfoFragment.newInstance(), this,false,R.id.my_community_fragment);
    }

    public Community getCommunity(){return community;}
}