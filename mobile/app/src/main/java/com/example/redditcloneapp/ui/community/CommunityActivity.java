package com.example.redditcloneapp.ui.community;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.post.PostCommentFragment;
import com.example.redditcloneapp.tools.FragmentTransition;

public class CommunityActivity extends AppCompatActivity {

    private User user;
    private Community community;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_community);

            user = (User) getIntent().getSerializableExtra("user");
            community = (Community) getIntent().getSerializableExtra("community");

            FragmentTransition.to(CommunityRulesFragment.newInstance(), this, false, R.id.comm_single_rules);
            FragmentTransition.to(CommunityModeratorsFragment.newInstance(), this, false, R.id.comm_single_moderators);
            FragmentTransition.to(CommunityFlairsFragment.newInstance(), this, false, R.id.comm_single_flairs);

            View dropDown = findViewById(R.id.comm_drop_down_lay);
            Button buttonVisibilityDown = findViewById(R.id.comm_drop_down_lay_btn_down);
            Button buttonVisibilityUp = findViewById(R.id.comm_drop_down_lay_btn_up);

            buttonVisibilityDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dropDown.setVisibility(View.VISIBLE);
                    buttonVisibilityDown.setVisibility(View.GONE);
                    buttonVisibilityUp.setVisibility(View.VISIBLE);
                }
            });

            buttonVisibilityUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dropDown.setVisibility(View.GONE);
                    buttonVisibilityDown.setVisibility(View.VISIBLE);
                    buttonVisibilityUp.setVisibility(View.GONE);
                }
            });
        }

    public Community getCommunity() {
        return community;
    }
}