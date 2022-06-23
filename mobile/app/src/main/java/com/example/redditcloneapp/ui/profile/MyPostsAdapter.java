package com.example.redditcloneapp.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.post.PostActivity;
import com.example.redditcloneapp.post.PostEditActivity;
import com.example.redditcloneapp.service.CommunityApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.community.CommunityPostsFragment;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPostsAdapter extends BaseAdapter {

    private Activity activity;
    private List<Post> posts;
    private User user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MyPostsAdapter(Activity activity, User user, List<Post> posts){this.activity = activity;this.user = user ;this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        Post post = posts.get(i);
        if(view == null)
            vi= activity.getLayoutInflater().inflate(R.layout.my_post_item, null);
        TextView postTitle = vi.findViewById(R.id.my_post_title);
        postTitle.setText(post.getTitle());
        postTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PostActivity.class);
                intent.putExtra("user", post.getUser());
                intent.putExtra("post", post);
                activity.startActivity(intent);
            }
        });

//        postDate.setText(post.getCreationDate().format(DateTimeFormatter
//                .ofLocalizedDate(FormatStyle.LONG)));

        Button editBtn = vi.findViewById(R.id.my_post_edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PostEditActivity.class);
                intent.putExtra("post", post);
                activity.startActivity(intent);
            }
        });

        return vi;
    }


}
