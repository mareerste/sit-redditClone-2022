package com.example.redditcloneapp.ui.community;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.example.redditcloneapp.post.PostCommentFragment;
import com.example.redditcloneapp.service.PostApiService;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityBasicInfoFragment;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityActivity extends AppCompatActivity {

    TextView commName;
    TextView commDate;
    TextView commDesc;

    private User user;
    private Community community;
    static Retrofit retrofit = null;
    private Post post;
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_community);

            user = (User) getIntent().getSerializableExtra("user");
//            community = (Community) getIntent().getSerializableExtra("community");
            post = (Post) getIntent().getSerializableExtra("post");
            getPostCommunity(post);
//            STRELICA KA NAZAD
//            ActionBar actionBar = getSupportActionBar();
//            if(actionBar != null){
//                actionBar.setDisplayHomeAsUpEnabled(true);
//            }

//            FragmentTransition.to(CommunityRulesFragment.newInstance(), this, false, R.id.comm_single_rules);
//            FragmentTransition.to(CommunityModeratorsFragment.newInstance(), this, false, R.id.comm_single_moderators);
//            FragmentTransition.to(CommunityFlairsFragment.newInstance(), this, false, R.id.comm_single_flairs);
//            FragmentTransition.to(CommunityPostsFragment.newInstance(),this,false,R.id.comm_single_posts);

            commName = findViewById(R.id.comm_single_name);
            commDate = findViewById(R.id.comm_single_date);
            commDesc = findViewById(R.id.comm_single_desc);
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
                        intent.putExtra("user", user);
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
//            TextView commName = findViewById(R.id.comm_single_name);
//            commName.setText(community.getName());

//            TextView commDate = findViewById(R.id.comm_single_date);
//            commDate.setText(community.getCreationDate().format(DateTimeFormatter
//                    .ofLocalizedDate(FormatStyle.LONG)));

//            TextView commDesc = findViewById(R.id.comm_single_desc);
//            commDesc.setText(community.getDescription());
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
            if(user == null){
                newPostLayBtn.setVisibility(View.GONE);
            }
            Button suspendBtn = findViewById(R.id.comm_suspend);
            if (user instanceof Administrator){
                suspendBtn.setVisibility(View.VISIBLE);
                suspendBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog dialog = new Dialog(CommunityActivity.this);
                        dialog.setContentView(R.layout.dialog_ban_or_view_user);
                        TextView text = dialog.findViewById(R.id.dialog_ban_or_view_text);
                        text.setText("Suspend community " + community.getName() );
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
                                }
                            }
                        });
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

    public void getPostCommunity(Post post){

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        PostApiService postApiService = retrofit.create(PostApiService.class);
        Call<Community> call = postApiService.getCommunityForPost(post.getId());
        call.enqueue(new Callback<Community>() {

            @Override
            public void onResponse(Call<Community> call, Response<Community> response) {
                commName.setText(response.body().getName());
                commDate.setText(response.body().getCreationDate());
                commDesc.setText(response.body().getDescription());
                community = response.body();

                FragmentTransition.to(CommunityRulesFragment.newInstance(response.body()), CommunityActivity.this, false, R.id.comm_single_rules);
                FragmentTransition.to(CommunityModeratorsFragment.newInstance(response.body()), CommunityActivity.this, false, R.id.comm_single_moderators);
                FragmentTransition.to(CommunityFlairsFragment.newInstance(response.body()), CommunityActivity.this, false, R.id.comm_single_flairs);
                FragmentTransition.to(CommunityPostsFragment.newInstance(response.body()),CommunityActivity.this,false,R.id.comm_single_posts);

                System.out.println("RESPONSE: " + response.body().toString());
                Toast.makeText(getApplicationContext(), response.body().toString(),Toast.LENGTH_LONG).show();
                community = response.body();
            }

            @Override
            public void onFailure(Call<Community> call, Throwable t) {
                System.out.println("RESPONSE: " + t.toString());
                Log.e(MainActivity.TAG, t.toString());
            }
        });

    }


}