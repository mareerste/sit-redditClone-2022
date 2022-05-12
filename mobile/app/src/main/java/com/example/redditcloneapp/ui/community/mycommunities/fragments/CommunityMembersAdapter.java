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

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.ui.profile.ProfileActivity;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
        Button btnBlock = vi.findViewById(R.id.my_community_members_block_btn);
        Button btnView = vi.findViewById(R.id.my_community_members_view_btn);
        btnView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProfileActivity.class);
                intent.putExtra("user", user);
                activity.startActivity(intent);
//                Dialog dialog = new Dialog(activity);
//                dialog.setContentView(R.layout.fragment_profile);
//                TextView textUsername = dialog.findViewById(R.id.profile_username);
//                textUsername.setText(user.getUsername());
//                TextView textPassword = dialog.findViewById(R.id.profile_password);
//                textPassword.setText(user.getPassword());
//                TextView textMail = dialog.findViewById(R.id.profile_email);
//                textMail.setText(user.getEmail());
//                TextView textDate = dialog.findViewById(R.id.profile_reg_date);
//                textDate.setText(user.getRegistrationDate().format(DateTimeFormatter
//                        .ofLocalizedDate(FormatStyle.LONG)));
//                TextView textDescr = dialog.findViewById(R.id.profile_description);
//                textDescr.setText(user.getDescription());
//                dialog.show();
            }
        });


        return vi;
    }
}
