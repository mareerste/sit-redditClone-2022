package com.example.redditcloneapp.ui.community;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Administrator;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.post.PostCommentFragment;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class CommunityActivity extends AppCompatActivity {

    private User user;
    private Community community;
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_community);

            user = (User) getIntent().getSerializableExtra("user");
            community = (Community) getIntent().getSerializableExtra("community");
//            STRELICA KA NAZAD
//            ActionBar actionBar = getSupportActionBar();
//            if(actionBar != null){
//                actionBar.setDisplayHomeAsUpEnabled(true);
//            }

            FragmentTransition.to(CommunityRulesFragment.newInstance(), this, false, R.id.comm_single_rules);
            FragmentTransition.to(CommunityModeratorsFragment.newInstance(), this, false, R.id.comm_single_moderators);
            FragmentTransition.to(CommunityFlairsFragment.newInstance(), this, false, R.id.comm_single_flairs);
            FragmentTransition.to(CommunityPostsFragment.newInstance(),this,false,R.id.comm_single_posts);

            View dropDown = findViewById(R.id.comm_drop_down_lay);
            Button buttonVisibilityDown = findViewById(R.id.comm_drop_down_lay_btn_down);
            Button buttonVisibilityUp = findViewById(R.id.comm_drop_down_lay_btn_up);
            View adminLayoutBtn = findViewById(R.id.comm_single_admin_view);
            if(user instanceof Administrator){
                adminLayoutBtn.setVisibility(View.VISIBLE);
                Button viewCommunityBtn = findViewById(R.id.comm_single_admin_view_btn);
                viewCommunityBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CommunityActivity.this, MyCommunityActivity.class);
                        intent.putExtra("community", community);
                        startActivity(intent);
                    }
                });
            }

            buttonVisibilityDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dropDown.setVisibility(View.VISIBLE);
                    buttonVisibilityDown.setVisibility(View.GONE);
                    buttonVisibilityUp.setVisibility(View.VISIBLE);
                }
            });
            TextView commName = findViewById(R.id.comm_single_name);
            commName.setText(community.getName());

            TextView commDate = findViewById(R.id.comm_single_date);
            commDate.setText(community.getCreationDate().format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.LONG)));

            TextView commDesc = findViewById(R.id.comm_single_desc);
            commDesc.setText(community.getDescription());
            buttonVisibilityUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dropDown.setVisibility(View.GONE);
                    buttonVisibilityDown.setVisibility(View.VISIBLE);
                    buttonVisibilityUp.setVisibility(View.GONE);
                }
            });

            View newPostView = findViewById(R.id.comm_new_post_layout);
            Button newPostLayBtn = findViewById(R.id.comm_new_post_btn_layout);
            newPostLayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(newPostView.getVisibility() == View.VISIBLE) {
                        newPostView.setVisibility(View.GONE);
                        newPostLayBtn.setText(R.string.create_a_new_post);
                    }
                    else {
                        newPostView.setVisibility(View.VISIBLE);
                        newPostLayBtn.setText(R.string.cancel);
                    }
                }
            });

            Button suspendBtn = findViewById(R.id.comm_suspend);
            if (user instanceof Administrator){
                suspendBtn.setVisibility(View.VISIBLE);
                suspendBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog dialog = new Dialog(CommunityActivity.this);
                        dialog.setContentView(R.layout.dialog_suspend_community);
                        TextView textReason = dialog.findViewById(R.id.dialog_suspend_comm_reason);
                        Button btnLeft = dialog.findViewById(R.id.dialog_suspend_comm_sus_btn);
                        btnLeft.setText("Suspend");
                        Button btnRight = dialog.findViewById(R.id.dialog_suspend_comm_cancel_btn);
                        TextView commName = dialog.findViewById(R.id.dialog_suspend_comm_name);
                        commName.setText("Suspend community " + community.getName() );
                        dialog.show();
                    }
                });

            }

        }

    public Community getCommunity() {
        return community;
    }

    public User getUser() {
        return user;
    }


}