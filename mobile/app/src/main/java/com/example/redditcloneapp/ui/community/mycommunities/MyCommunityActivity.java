package com.example.redditcloneapp.ui.community.mycommunities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.CommunityModeratorsFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityBasicInfoFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityFlairsEditFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityMembersFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityModeratorsEditFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityRulesEditFragment;

public class MyCommunityActivity extends AppCompatActivity {

    Community community;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_community);
        community = (Community) getIntent().getSerializableExtra("community");
        user = (User) getIntent().getSerializableExtra("user");
        FragmentTransition.to(CommunityBasicInfoFragment.newInstance(), this,false,R.id.my_community_fragment);
        Button btnBasic = findViewById(R.id.my_community_basic_btn);
        btnBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(CommunityBasicInfoFragment.newInstance(), MyCommunityActivity.this,false,R.id.my_community_fragment);
            }
        });
        Button btnMembers = findViewById(R.id.my_community_members_btn);
        btnMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(CommunityMembersFragment.newInstance(), MyCommunityActivity.this,false,R.id.my_community_fragment);
            }
        });
        Button btnModerators = findViewById(R.id.my_community_moderators_btn);
        btnModerators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(CommunityModeratorsEditFragment.newInstance(), MyCommunityActivity.this,false,R.id.my_community_fragment);
            }
        });

        Button btnRules = findViewById(R.id.my_community_rules_btn);
        btnRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(CommunityRulesEditFragment.newInstance(), MyCommunityActivity.this,false,R.id.my_community_fragment);
            }
        });
        Button btnFlairs = findViewById(R.id.my_community_flairs_btn);
        btnFlairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(CommunityFlairsEditFragment.newInstance(), MyCommunityActivity.this,false,R.id.my_community_fragment);
            }
        });

        Button btnSuspend = findViewById(R.id.my_community_suspend_comm_btn);
        btnSuspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MyCommunityActivity.this);
                dialog.setContentView(R.layout.dialog_ban_or_view_user);
                TextView text = dialog.findViewById(R.id.dialog_ban_or_view_text);
                text.setText(community.getName());
                Button btnLeft = dialog.findViewById(R.id.dialog_ban_or_view_btn_left);
                btnLeft.setText("Suspend");
                Button btnRight = dialog.findViewById(R.id.dialog_ban_or_view_btn_right);
                btnRight.setText(R.string.cancel);
                EditText susReason = dialog.findViewById(R.id.dialog_ban_or_view_edit_text);
                susReason.setVisibility(View.VISIBLE);
                susReason.setHint(R.string.sus_reason);
                dialog.show();
                btnRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!susReason.getText().toString().equals("")){
                            community.setSuspended(true);
                            community.setSuspendedReason(susReason.getText().toString());
                            dialog.dismiss();
                            FragmentTransition.to(CommunityBasicInfoFragment.newInstance(), MyCommunityActivity.this,false,R.id.my_community_fragment);
                        }
                    }
                });
            }
        });
    }

    public Community getCommunity(){return community;}

    public User getUser() {
        return user;
    }
}