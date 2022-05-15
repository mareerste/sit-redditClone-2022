package com.example.redditcloneapp.post;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Post;

public class PostCommentFragment extends ListFragment {


    public static PostCommentFragment newInstance() {
        return new PostCommentFragment();
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
        CommentAdapter adapter = new CommentAdapter(getActivity(),((PostActivity)getActivity()).getPost(), ((PostActivity)getActivity()).getUser());
        setListAdapter(adapter);
    }
}