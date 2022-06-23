package com.example.redditcloneapp.post;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.adapters.PostAdapter;
import com.example.redditcloneapp.model.Comment;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Flair;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.CommentApiService;
import com.example.redditcloneapp.service.CommunityApiService;
import com.example.redditcloneapp.service.PostApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.community.CommunityFlairsFragment;
import com.example.redditcloneapp.ui.community.CommunityModeratorsFragment;
import com.example.redditcloneapp.ui.community.CommunityPostsFragment;
import com.example.redditcloneapp.ui.community.CommunityRulesFragment;
import com.example.redditcloneapp.ui.profile.ProfileActivity;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {
    private User user;
    private Post post;
    private Community community;

    TextView comName,comDate,comDesc;
    EditText inputCommentText;
    static Retrofit retrofit = null;
    static Retrofit retrofitPost = null;
    static Retrofit retrofitComment = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        user = (User) getIntent().getSerializableExtra("user");
        post = (Post) getIntent().getSerializableExtra("post");
        getPostCommunity(post);
        TextView title = findViewById(R.id.post_single_title);
        TextView text = findViewById(R.id.post_single_text);
        TextView karma = findViewById(R.id.post_single_karma);
        TextView userText = findViewById(R.id.post_single_user);
        TextView creationDate = findViewById(R.id.post_single_date);
        TextView flair = findViewById(R.id.post_single_flair);
        inputCommentText = findViewById(R.id.post_simple_comment_for_send);
        Button saveComment = findViewById(R.id.btn_post_simple_send_comment);
        saveComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save comment
                saveComment();
            }
        });
        title.setText(post.getTitle());
        text.setText(post.getText());
        karma.setText(post.getReactions().toString());
        userText.setText(post.getUser().getUsername());
        userText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this, ProfileActivity.class);
                intent.putExtra("user", post.getUser());
                startActivity(intent);
            }
        });
//        creationDate.setText(post.getCreationDate().format(DateTimeFormatter
//                .ofLocalizedDate(FormatStyle.LONG)));
        creationDate.setText(post.getCreationDate());
        flair.setText(post.getFlair().getName());

        comName = findViewById(R.id.single_post_community_name);
        comDesc = findViewById(R.id.single_post_community_desc);
        comDate = findViewById(R.id.single_post_community_created);

        Button btnComments = findViewById(R.id.post_single_comments_btn);
        btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout commentsLay = findViewById(R.id.post_single_comments_fragment);
                if (commentsLay.getVisibility() == View.GONE)
                    commentsLay.setVisibility(View.VISIBLE);
                else
                    commentsLay.setVisibility(View.GONE);
            }
        });

        Button reportBtn = findViewById(R.id.post_single_report_btn);
        Button joinBtn = findViewById(R.id.post_single_join_community_btn);
        View commentLayout = findViewById(R.id.post_single_layout_comment);
//        TODO: dialog na click

        if(user == null){
            findViewById(R.id.post_vote_up_btn).setVisibility(View.GONE);
            findViewById(R.id.post_vote_down_btn).setVisibility(View.GONE);
            reportBtn.setVisibility(View.GONE);
            joinBtn.setVisibility(View.GONE);
            commentLayout.setVisibility(View.GONE);
            userText.setClickable(false);
        }
        FragmentTransition.to(PostCommentFragment.newInstance(post, this), this, false, R.id.post_single_comments_fragment);

    }
    public Post getPost(){
        return post;
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
                comName.setText(response.body().getName());
                comDate.setText(response.body().getCreationDate());
                comDesc.setText(response.body().getDescription());

                community = response.body();
            }

            @Override
            public void onFailure(Call<Community> call, Throwable t) {
                System.out.println("RESPONSE: " + t.toString());
                Log.e(MainActivity.TAG, t.toString());
            }
        });

    }

    private void saveComment() {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(getSharedPreferences(SignInActivity.mypreference, MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofitComment == null) {
            retrofitComment = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        CommentApiService commentApiService = retrofitComment.create(CommentApiService.class);
        Comment comment = new Comment(inputCommentText.getText().toString());

        Call<Comment> call = commentApiService.saveComment(comment);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(response.isSuccessful()){
                    ArrayList<Comment> comments = post.getComments();
                    comments.add(response.body());
                    post.setComments(comments);
                    updatePost();
                }else{
                    Toast.makeText(PostActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Toast.makeText(PostActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updatePost() {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofitPost == null) {
            retrofitPost = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        PostApiService postApiService = retrofitPost.create(PostApiService.class);

        Call<Post> call = postApiService.updatePost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(PostActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PostActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}