package com.example.redditcloneapp.ui.community.mycommunities;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityFlairsEditFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityRulesEditFragment;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.redditcloneapp.R;

public class CreateCommunityActivity extends AppCompatActivity {
    private User user;
    private Community community;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);
        user = (User) getIntent().getSerializableExtra("user");
        community = new Community(user);
        Button btnRules = findViewById(R.id.community_create_rules_btn);
        btnRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(CommunityRulesEditFragment.newInstance(community), CreateCommunityActivity.this,false,R.id.community_create_fragment_layout);
            }
        });
        Button btnFlairs = findViewById(R.id.community_create_flairs_btn);
        btnFlairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(CommunityFlairsEditFragment.newInstance(community), CreateCommunityActivity.this,false,R.id.community_create_fragment_layout);
            }
        });

        Button btnSave = findViewById(R.id.community_create_save_btn);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText commName = findViewById(R.id.community_create_name);
                EditText commDesc = findViewById(R.id.community_create_desc);
                if (commName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.community_name_error, Toast.LENGTH_LONG).show();
                } else if (commDesc.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.community_desc_error, Toast.LENGTH_LONG).show();
                } else if (community.getRules().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.community_rules_error, Toast.LENGTH_LONG).show();
                } else if (community.getFlairs().isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.community_flairs_error, Toast.LENGTH_LONG).show();
                }else{
                    community.setName(commName.getText().toString());
                    community.setDescription(commDesc.getText().toString());
                    Toast.makeText(getApplicationContext(),community.toString(),Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

}