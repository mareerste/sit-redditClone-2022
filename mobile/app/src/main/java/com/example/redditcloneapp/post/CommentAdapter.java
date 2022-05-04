package com.example.redditcloneapp.post;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Comment;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private Activity activity;
    private Post post;
    private List<Comment> comments;

    public CommentAdapter (Activity activity, Post post){this.activity = activity;this.post = post;this.comments=post.getComments();}

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vi = view;
        Comment comment = comments.get(i);

        if(view == null){
            vi = activity.getLayoutInflater().inflate(R.layout.comment_item, null);}
        TextView userText = vi.findViewById(R.id.post_comment_user);
        userText.setText(comment.getUser().getUsername());
        TextView date = vi.findViewById(R.id.post_comment_date);
        date.setText(comment.getTimestamp().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)));
        TextView text = vi.findViewById(R.id.post_comment_text);
        text.setText(comment.getText());
        TextView karma = vi.findViewById(R.id.post_comment_karma);
        karma.setText(comment.getCommentReaction());
        return vi;
    }
}
