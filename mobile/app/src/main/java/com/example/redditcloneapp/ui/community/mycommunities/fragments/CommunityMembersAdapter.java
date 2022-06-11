package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Banned;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;
import com.example.redditcloneapp.ui.profile.ProfileActivity;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class CommunityMembersAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<User> users;
    private Community community;
    private User moderator;
//TODO fake users
    @RequiresApi(api = Build.VERSION_CODES.O)
    public CommunityMembersAdapter(Activity activity, Community community, User moderator){this.activity = activity; this.users = Mokap.getUsers();this.community = community;this.moderator = moderator;}

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        User user = users.get(i);
        if(view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.community_member_item, null);
        TextView username = vi.findViewById(R.id.my_community_members_username);
        username.setText(user.getUsername());

        Button btnBlock = vi.findViewById(R.id.my_community_members_block_btn);
        Banned banned = Mokap.findBanned(community,user);

        if (banned != null){
            btnBlock.setText(R.string.unblock);
            btnBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(view.getContext(), "Unblocked", Toast.LENGTH_SHORT);
                    toast.show();
                    btnBlock.setText(R.string.block);
                }
            });
        }else {
            btnBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Banned banned = new Banned(Mokap.getBanneds().size()+1, moderator, community, user);
                    Toast toast = Toast.makeText(view.getContext(), banned.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    btnBlock.setText(R.string.unblock);
                }
            });
        }

        Button btnView = vi.findViewById(R.id.my_community_members_view_btn);
        btnView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProfileActivity.class);
                intent.putExtra("user", user);
                activity.startActivity(intent);
            }
        });


        return vi;
    }
}
