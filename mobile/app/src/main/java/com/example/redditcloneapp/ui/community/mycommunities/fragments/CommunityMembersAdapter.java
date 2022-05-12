package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.User;

import java.util.ArrayList;

public class CommunityMembersAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<User> users;

    public CommunityMembersAdapter(Activity activity, Community community){this.activity = activity; this.users = community.getMembers();}

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        User user = users.get(i);
        if(view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.community_member_item, null);
        TextView username = vi.findViewById(R.id.my_community_members_username);
        username.setText(user.getUsername());


        return vi;
    }
}
