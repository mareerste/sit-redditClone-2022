package com.example.redditcloneapp.post;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Comment;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;

import java.util.Arrays;
import java.util.List;

public class CommentCommentsFragment extends ListFragment {

    private Comment comment;
    private User user;
    private Post post;
    private String sortType = "Sort";

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
        Spinner mySpinner = (Spinner) getActivity().findViewById(R.id.spinner_sort_posts);
        mySpinner.setVisibility(View.GONE);
        List<String> list = Arrays.asList("Sort","Top","New","Old");
        mySpinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,list));
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = mySpinner.getSelectedItem().toString();
                if (!selected.equals("Sort")) {
                    sortType = selected;
                    getComments();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                getComments();
            }
        });
        getComments();

    }

    private void getComments(){
        List<Comment> comments = comment.getChildComments();
        CommentAdapter adapter = new CommentAdapter(getActivity(),comments, user, post);
        setListAdapter(adapter);
    }
}

