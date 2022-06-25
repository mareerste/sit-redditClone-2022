package com.example.redditcloneapp.post;

import android.app.Activity;
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

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Comment;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.ui.community.CommunityRulesFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class PostCommentFragment extends ListFragment {

    private Post post;
    private Activity activity;
    private String sortType = "Sort";

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
        return inflater.inflate(R.layout.post_map_layout_2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Spinner mySpinner = (Spinner) activity.findViewById(R.id.spinner_sort_posts);
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

//        List<Comment> comments = post.getComments();
//        CommentAdapter adapter = new CommentAdapter(activity,comments, ((PostActivity)getActivity()).getUser(),post);
//        setListAdapter(adapter);
    }

    private void getComments(){
        List<Comment> comments = post.getComments();

        if(sortType.equals("New")){
            Collections.sort(comments, new Comparator<Comment>() {
                @Override
                public int compare(Comment p1, Comment p2) {
                    Date date1 = null,date2 = null;
                    try {
                        date1=new SimpleDateFormat("MM/dd/yyyy").parse(p1.getTimestamp());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        date2=new SimpleDateFormat("MM/dd/yyyy").parse(p2.getTimestamp());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(date1.after(date2))
                        return -1;
                    else if (date2.after(date1))
                        return 1;
                    else
                        return 1;
                }
            });
        } else if(sortType.equals("Top")){
            Collections.sort(comments, new Comparator<Comment>() {
                @Override
                public int compare(Comment p1, Comment p2) {
                    return p2.getReactions() - p1.getReactions();
                }
            });
        } else if(sortType.equals("Old")){
            Collections.sort(comments, new Comparator<Comment>() {
                @Override
                public int compare(Comment p1, Comment p2) {
                    Date date1 = null,date2 = null;
                    try {
                        date1=new SimpleDateFormat("MM/dd/yyyy").parse(p1.getTimestamp());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        date2=new SimpleDateFormat("MM/dd/yyyy").parse(p2.getTimestamp());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(date1.after(date2))
                        return 1;
                    else if (date2.after(date1))
                        return -1;
                    else
                        return -1;
                }
            });
        }

        CommentAdapter adapter = new CommentAdapter(activity,comments, ((PostActivity)getActivity()).getUser(),post);
        setListAdapter(adapter);
    }
}