package com.example.redditcloneapp.ui.community.mycommunities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Administrator;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.post.PostActivity;
import com.example.redditcloneapp.service.CommunityApiService;
import com.example.redditcloneapp.service.PostApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.community.CommunityModeratorsFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityBasicInfoFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityFlairsEditFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityMembersFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityModeratorsEditFragment;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityRulesEditFragment;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCommunityActivity extends AppCompatActivity {

    Community community;
    User user;

    TextView commName;
    Button viewCommunity;

    static Retrofit retrofit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_community);
        community = (Community) getIntent().getSerializableExtra("community");
        user = (User) getIntent().getSerializableExtra("user");
        FragmentTransition.to(CommunityBasicInfoFragment.newInstance(), this,false,R.id.my_community_fragment);
        commName = findViewById(R.id.my_community_name);
        viewCommunity = findViewById(R.id.my_community_view_btn);
        viewCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyCommunityActivity.this, CommunityActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("community", community);
                startActivity(intent);
            }
        });
        commName.setText(community.getName());
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
                FragmentTransition.to(CommunityRulesEditFragment.newInstance(community), MyCommunityActivity.this,false,R.id.my_community_fragment);
            }
        });
        Button btnFlairs = findViewById(R.id.my_community_flairs_btn);
        btnFlairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(CommunityFlairsEditFragment.newInstance(community), MyCommunityActivity.this,false,R.id.my_community_fragment);
            }
        });

        Button btnSuspend = findViewById(R.id.my_community_suspend_comm_btn);
        if(user instanceof Administrator){
            View susLayout = findViewById(R.id.my_community_suspend_comm_btn_layout);
            susLayout.setVisibility(View.VISIBLE);
        }
        btnSuspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MyCommunityActivity.this);
                dialog.setContentView(R.layout.dialog_ban_or_view_user);
                TextView text = dialog.findViewById(R.id.dialog_ban_or_view_text);
                text.setText("Suspend community " + community.getName());
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

        Button btnSave = findViewById(R.id.my_community_save_btn);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (community.getRules().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.community_rules_error, Toast.LENGTH_LONG).show();
                } else if (community.getFlairs().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.community_flairs_error, Toast.LENGTH_LONG).show();
                } else{
                    updateCommunity(community);
//                    Toast.makeText(getApplicationContext(), community.toString(), Toast.LENGTH_LONG).show();
//                    finish();
                }
            }
        });
    }

    public void updateCommunity(Community community) {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommunityApiService communityApiService = retrofit.create(CommunityApiService.class);

        Call<Community> call = communityApiService.updateCommunity(community);
        call.enqueue(new Callback<Community>() {
            @Override
            public void onResponse(Call<Community> call, Response<Community> response) {
                if(response.isSuccessful()){
                    finish();
                }else{
                    Toast.makeText(MyCommunityActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Community> call, Throwable t) {
                Toast.makeText(MyCommunityActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Community getCommunity(){return community;}

    public User getUser() {
        return user;
    }

}