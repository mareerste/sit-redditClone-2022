package com.example.redditcloneapp.post;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.ui.community.CommunityRulesFragment;

public class PostCommentFragment extends ListFragment {

    private Post post;
    private Activity activity;

    public PostCommentFragment(Post post, Activity activity) {
        this.post = post;this.activity = activity;
    }

    public static PostCommentFragment newInstance(Post post, PostActivity postActivity) {
        return new PostCommentFragment(post,postActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CommentAdapter adapter = new CommentAdapter(activity,post, ((PostActivity)getActivity()).getUser());
        setListAdapter(adapter);
    }
}