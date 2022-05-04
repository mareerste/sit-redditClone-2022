package com.example.redditcloneapp.post;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.adapters.PostAdapter;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;

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
        creationDate.setText(post.getCreationDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)));
        flair.setText(post.getFlair().getName());
    }


}