package com.example.redditcloneapp.ui.community;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Administrator;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Flair;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.post.PostCommentFragment;
import com.example.redditcloneapp.service.BannedApiService;
import com.example.redditcloneapp.service.CommunityApiService;
import com.example.redditcloneapp.service.PostApiService;
import com.example.redditcloneapp.service.UserApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.access.SignUpActivity;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;
import com.example.redditcloneapp.ui.community.mycommunities.fragments.CommunityBasicInfoFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityActivity extends AppCompatActivity {

    TextView commName,commDate,commDesc;
    EditText postTitle, postText;
    Spinner commFlairs;
    Button btnSavePost, newPostLayBtn;
    View newPostView;
    private Flair selectedFlair = null;
    private boolean userBanned = false;

    private User user;
    private Community community;
    static Retrofit retrofit = null;
    static Retrofit retrofitPost = null;
    private String sortType = "Sort";
    private Post post;
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_community);

            user = (User) getIntent().getSerializableExtra("user");
            community = (Community) getIntent().getSerializableExtra("community");
            post = (Post) getIntent().getSerializableExtra("post");

//            STRELICA KA NAZAD
//            ActionBar actionBar = getSupportActionBar();
//            if(actionBar != null){
//                actionBar.setDisplayHomeAsUpEnabled(true);
//            }

            postTitle = findViewById(R.id.comm_new_post_title);
            postText = findViewById(R.id.comm_new_post_text);
            btnSavePost = findViewById(R.id.comm_new_post_btn_save);

            commName = findViewById(R.id.comm_single_name);
            commDate = findViewById(R.id.comm_single_date);
            commDesc = findViewById(R.id.comm_single_desc);
            commFlairs = findViewById(R.id.comm_new_post_spinner);
            if(community == null)
                getPostCommunity(post);
            else{
                loadCommunity(community);
            }
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

            buttonVisibilityUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dropDown.setVisibility(View.GONE);
                    buttonVisibilityDown.setVisibility(View.VISIBLE);
                    buttonVisibilityUp.setVisibility(View.GONE);
                }
            });

            btnSavePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isValid())
                        savePost();
                }
            });

            newPostView = findViewById(R.id.comm_new_post_layout);
            newPostLayBtn = findViewById(R.id.comm_new_post_btn_layout);
            newPostLayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(newPostView.getVisibility() == View.VISIBLE) {
                        closeNewPostLayout();
                    }
                    else {
                        openNewPostLayout();
                    }
                }
            });
            if(user == null){
                newPostLayBtn.setVisibility(View.GONE);
            }

            Spinner sortSpinner = findViewById(R.id.comm_single_sort_btn);
            List<String> list = Arrays.asList("Sort","Top","New","Hot");
            sortSpinner.setAdapter(new ArrayAdapter<String>(CommunityActivity.this, android.R.layout.simple_spinner_dropdown_item,list));
            sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selected = sortSpinner.getSelectedItem().toString();
                    if (!selected.equals("Sort")) {
                        sortType = selected;
                        getPosts();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    getPosts();
                }
            });

            Button suspendBtn = findViewById(R.id.comm_suspend);
            if (getSharedPreferences(SignInActivity.mypreference, MODE_PRIVATE).getString(SignInActivity.Role, "").equals("ROLE_ADMIN")){
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
                                    suspendCommunity(community);
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                });
            }

        }

    private boolean isValid() {
        postTitle.setBackgroundResource(0);
        postText.setBackgroundResource(0);
        boolean value = true;
        String msg = "";
        if(postTitle.getText().toString().equals("")) {
            msg += getResources().getString(R.string.post_title_error);
//            Toast.makeText(this, R.string.post_title_error, Toast.LENGTH_SHORT).show();
            postTitle.setBackgroundResource(R.drawable.rounded_corners_bg);
            value = false;
        }
        if (postText.getText().toString().equals("")){
            if(!msg.equals(""))
                msg += "\n";
            msg += getResources().getString(R.string.post_text_error);
//            Toast.makeText(this, R.string.post_text_error, Toast.LENGTH_SHORT).show();
            postText.setBackgroundResource(R.drawable.rounded_corners_bg);
            value = false;
        }
        if(value == false)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return value;
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
                if(user != null)
                    isBanned();
                ArrayAdapter<Flair> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, response.body().getFlairs());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                commFlairs.setAdapter(adapter);
                commFlairs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        ((TextView) commFlairs.getChildAt(0)).setTextColor(Color.GRAY);
                        ((TextView) commFlairs.getChildAt(0)).setTextSize(20);
                        ((TextView) commFlairs.getChildAt(0)).setBackgroundResource(R.drawable.rounded_corners_bg);

                        selectedFlair = (Flair) commFlairs.getSelectedItem();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        Toast.makeText(getApplicationContext(), "You have to select an Flair first", Toast.LENGTH_LONG).show();
                    }
                });

                FragmentTransition.to(CommunityRulesFragment.newInstance(response.body()), CommunityActivity.this, false, R.id.comm_single_rules);
                FragmentTransition.to(CommunityModeratorsFragment.newInstance(response.body()), CommunityActivity.this, false, R.id.comm_single_moderators);
                FragmentTransition.to(CommunityFlairsFragment.newInstance(response.body()), CommunityActivity.this, false, R.id.comm_single_flairs);
//                FragmentTransition.to(CommunityPostsFragment.newInstance(response.body()),CommunityActivity.this,false,R.id.comm_single_posts);
                community = response.body();
                getPosts();

            }

            @Override
            public void onFailure(Call<Community> call, Throwable t) {
                System.out.println("RESPONSE: " + t.toString());
                Log.e(MainActivity.TAG, t.toString());
            }
        });

    }

    private void savePost() {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(getSharedPreferences(SignInActivity.mypreference, MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


            retrofitPost = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        CommunityApiService communityApiService = retrofitPost.create(CommunityApiService.class);
        System.out.println("COMM ID"+community.getId());
        Post post = new Post(postTitle.getText().toString(), postText.getText().toString(), selectedFlair);

        Call<Post> call = communityApiService.savePost(community.getId(),post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){

//                    FragmentTransition.to(CommunityPostsFragment.newInstance(community),CommunityActivity.this,false,R.id.comm_single_posts);
                    getPosts();
                    closeNewPostLayout();
                    clearForm();
                }else{
                    Toast.makeText(CommunityActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(CommunityActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearForm() {
            postTitle.setText("");
            postText.setText("");
    }

    private void openNewPostLayout() {
        newPostView.setVisibility(View.VISIBLE);
        newPostLayBtn.setText(R.string.cancel);
    }

    private void closeNewPostLayout() {
        newPostView.setVisibility(View.GONE);
        newPostLayBtn.setText(R.string.create_a_new_post);
    }

    private void loadCommunity(Community community){
        if(user != null)
            isBanned();
        commName.setText(community.getName());
        commDate.setText(community.getCreationDate());
        commDesc.setText(community.getDescription());

        ArrayAdapter<Flair> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, community.getFlairs());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        commFlairs.setAdapter(adapter);
        commFlairs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) commFlairs.getChildAt(0)).setTextColor(Color.GRAY);
                ((TextView) commFlairs.getChildAt(0)).setTextSize(20);
                ((TextView) commFlairs.getChildAt(0)).setBackgroundResource(R.drawable.rounded_corners_bg);

                selectedFlair = (Flair) commFlairs.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "You have to select an Flair first", Toast.LENGTH_LONG).show();
            }
        });

        FragmentTransition.to(CommunityRulesFragment.newInstance(community), CommunityActivity.this, false, R.id.comm_single_rules);
        FragmentTransition.to(CommunityModeratorsFragment.newInstance(community), CommunityActivity.this, false, R.id.comm_single_moderators);
        FragmentTransition.to(CommunityFlairsFragment.newInstance(community), CommunityActivity.this, false, R.id.comm_single_flairs);
//        FragmentTransition.to(CommunityPostsFragment.newInstance(community),CommunityActivity.this,false,R.id.comm_single_posts);

        getPosts();

    }
    public void suspendCommunity(Community community) {

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
                    Intent intent = new Intent(CommunityActivity.this, MainActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }else{
                    Toast.makeText(CommunityActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Community> call, Throwable t) {
                Toast.makeText(CommunityActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPosts(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        CommunityApiService communityApiService = retrofit.create(CommunityApiService.class);
        Call<List<Post>> call = communityApiService.getCommunityPosts(community.getId());
        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, retrofit2.Response<List<Post>> response) {
//                CommunityPostAdapter adapter = new CommunityPostAdapter(CommunityActivity.this,user,response.body(), community);
//                setListAdapter(adapter);
                List<Post> posts = response.body();

                if(sortType.equals("New")){
                    Collections.sort(posts, new Comparator<Post>() {
                        @Override
                        public int compare(Post p1, Post p2) {
                            Date date1 = null,date2 = null;
                            try {
                                date1=new SimpleDateFormat("MM/dd/yyyy").parse(p1.getCreationDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                date2=new SimpleDateFormat("MM/dd/yyyy").parse(p2.getCreationDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if(date1.after(date2))
                                return -1;
                            else if (date2.after(date1))
                                return 1;
                            else
                                return 1;
                        }
                    });
                } else if(sortType.equals("Top")){
                    Collections.sort(posts, new Comparator<Post>() {
                        @Override
                        public int compare(Post p1, Post p2) {
                            return p2.getReactions() - p1.getReactions();
                        }
                    });
                } else if(sortType.equals("Hot")) {
                    Collections.sort(posts, new Comparator<Post>() {
                        @Override
                        public int compare(Post p1, Post p2) {
                            Date date1 = null, date2 = null;
                            try {
                                date1 = new SimpleDateFormat("MM/dd/yyyy").parse(p1.getCreationDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                date2 = new SimpleDateFormat("MM/dd/yyyy").parse(p2.getCreationDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (date1.after(date2))
                                return -1;
                            else if (date2.after(date1))
                                return 1;
                            else {
                                if (p1.getReactions() < p2.getReactions())
                                    return 1;
                                else if (p1.getReactions() > p2.getReactions())
                                    return -1;
                                else
                                    return 0;
                            }
                        }
                    });
                }
                FragmentTransition.to(CommunityPostsFragment.newInstance(posts, userBanned),CommunityActivity.this,false,R.id.comm_single_posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

        private void isBanned(){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BannedApiService bannedApiService = retrofit.create(BannedApiService.class);
        Call<Boolean> call = bannedApiService.isReported(community.getId(),user.getUsername());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                userBanned = response.body();

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(CommunityActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}