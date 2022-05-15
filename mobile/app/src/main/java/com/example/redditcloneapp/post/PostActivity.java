package com.example.redditcloneapp.post;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.adapters.PostAdapter;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.profile.ProfileActivity;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class PostActivity extends AppCompatActivity {
    private User user;
    private Post post;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        user = (User) getIntent().getSerializableExtra("user");
        post = (Post) getIntent().getSerializableExtra("post");

        TextView title = findViewById(R.id.post_single_title);
        TextView text = findViewById(R.id.post_single_text);
        TextView karma = findViewById(R.id.post_single_karma);
        TextView userText = findViewById(R.id.post_single_user);
        TextView creationDate = findViewById(R.id.post_single_date);
        TextView flair = findViewById(R.id.post_single_flair);
        title.setText(post.getTitle());
        text.setText(post.getText());
        karma.setText(post.getPostReaction());
        userText.setText(post.getUser().getUsername());
        userText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this, ProfileActivity.class);
                intent.putExtra("user", post.getUser());
                startActivity(intent);
            }
        });
        creationDate.setText(post.getCreationDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)));
        flair.setText(post.getFlair().getName());

        TextView comName = findViewById(R.id.single_post_community_name);
        TextView comDesc = findViewById(R.id.single_post_community_desc);
        TextView comDate = findViewById(R.id.single_post_community_created);

        comName.setText(post.getCommunity().getName());
        comDesc.setText(post.getCommunity().getDescription());
        comDate.setText(post.getCommunity().getCreationDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)));
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
            findViewById(R.id.post_single_vote_layout).setVisibility(View.GONE);
            reportBtn.setVisibility(View.GONE);
            joinBtn.setVisibility(View.GONE);
            commentLayout.setVisibility(View.GONE);
            userText.setClickable(false);
        }
        FragmentTransition.to(PostCommentFragment.newInstance(), this, false, R.id.post_single_comments_fragment);

    }
    public Post getPost(){
        return post;
    }

    public User getUser() {
        return user;
    }
}