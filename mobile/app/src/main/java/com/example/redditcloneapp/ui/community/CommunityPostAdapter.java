package com.example.redditcloneapp.ui.community;

import android.app.Activity;
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
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.post.PostActivity;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class CommunityPostAdapter extends BaseAdapter {
    private Activity activity;
    private Community community;
    private ArrayList<Post>posts;
    private User user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CommunityPostAdapter (Activity activity,User user, Community community){this.activity = activity;this.user = user; this.community = community;this.posts = Mokap.getPostsForCommunity(community);}

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
            vi = activity.getLayoutInflater().inflate(R.layout.post_item, null);
        TextView title = (TextView) vi.findViewById(R.id.post_title);
        TextView text = (TextView) vi.findViewById(R.id.post_text);
        TextView karma = (TextView) vi.findViewById(R.id.post_karma);
        TextView userText = vi.findViewById(R.id.post_user);
        TextView flair = vi.findViewById(R.id.post_flair);
        TextView date = vi.findViewById(R.id.post_date);
        TextView community = vi.findViewById(R.id.post_community);
        Button btnPost = vi.findViewById(R.id.btn_view_post);
        Button btnReport = vi.findViewById(R.id.btn_post_report);
        title.setText(post.getTitle());
        text.setText(post.getText());
        karma.setText(post.getPostReaction());
        userText.setText(post.getUser().getUsername());
        flair.setText(post.getFlair().getName());
        community.setText(post.getCommunity().getName());
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(), post.getCommunity().toString(),Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(activity, CommunityActivity.class);
                intent.putExtra("user", user );
                intent.putExtra("community", post.getCommunity());
                activity.startActivity(intent);

            }
        });
        date.setText(post.getCreationDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)));

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, PostActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("post", post);
                activity.startActivity(intent);
                Toast toast = Toast.makeText(view.getContext(), post.toString(),Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        return vi;
    }
}