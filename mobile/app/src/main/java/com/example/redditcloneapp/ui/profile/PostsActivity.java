package com.example.redditcloneapp.ui.profile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Build;
import android.os.Bundle;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.User;

public class PostsActivity extends ListActivity {
    private User user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_map_layout);
        user = (User) getIntent().getSerializableExtra("user");
        MyPostsAdapter adapter = new MyPostsAdapter(PostsActivity.this, user);
        setListAdapter(adapter);
    }


}