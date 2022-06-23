package com.example.redditcloneapp.post;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Comment;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;

public class CommentCommentsFragment extends ListFragment {

    private Comment comment;
    private User user;
    private Post post;

    public CommentCommentsFragment(Comment comment, User user, Post post){this.comment = comment; this.user = user;this.post = post;}

    public static CommentCommentsFragment newInstance(Comment comment, User user, Post post){return new CommentCommentsFragment(comment, user, post);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CommentAdapter adapter = new CommentAdapter(getActivity(),comment, user, post);
        setListAdapter(adapter);

    }
}

