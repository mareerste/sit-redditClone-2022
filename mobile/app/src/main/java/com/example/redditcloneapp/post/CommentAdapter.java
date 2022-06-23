package com.example.redditcloneapp.post;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Comment;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.Report;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.model.enums.ReportReason;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.profile.ProfileActivity;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private Activity activity;
    private Post post;
    private List<Comment> comments;
    private User user;

    public CommentAdapter (Activity activity, Post post, User user){this.activity = activity;this.post = post;this.comments=post.getComments();this.user = user;}
    public CommentAdapter (Activity activity, Comment comment,User user, Post post){this.activity = activity ;this.comments=comment.getChildComments();this.user = user;this.post = post;}

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
        userText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProfileActivity.class);
                intent.putExtra("user", comment.getUser());
                activity.startActivity(intent);
            }
        });
        TextView date = vi.findViewById(R.id.post_comment_date);
//        date.setText(comment.getTimestamp().format(DateTimeFormatter
//                .ofLocalizedDate(FormatStyle.LONG)));
        date.setText(comment.getTimestamp());
        TextView text = vi.findViewById(R.id.post_comment_text);
        text.setText(comment.getText());
        TextView karma = vi.findViewById(R.id.post_comment_karma);
        karma.setText(comment.getReactions().toString());
        Button reportBtn = vi.findViewById(R.id.post_comment_report);
        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_report);
                TextView text = dialog.findViewById(R.id.dialog_report_text);
                text.setText(view.getContext().getResources().getString(R.string.report_comment_text) + "?");

                Spinner mySpinner = (Spinner) dialog.findViewById(R.id.dialog_report_spinner);
                mySpinner.setAdapter(new ArrayAdapter<ReportReason>(activity, android.R.layout.simple_spinner_item, ReportReason.values()));
                Button btnLeft = dialog.findViewById(R.id.dialog_report_submit);
                btnLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Report report = new Report(Mokap.getReports().size()+1, (ReportReason) mySpinner.getSelectedItem(), user,  comment);
                        Toast toast = Toast.makeText(view.getContext(),report.toString(),Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                Button btnRight = dialog.findViewById(R.id.dialog_report_cancel);
                btnRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        if(user == null){
            vi.findViewById(R.id.post_comment_vote_layout).setVisibility(View.GONE);
            vi.findViewById(R.id.post_comment_reply_report_layout).setVisibility(View.GONE);
            userText.setClickable(false);
        }



            Button childCommentsBtn = vi.findViewById(R.id.comment_comments_btn);
            childCommentsBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(comment.getChildComments().size() != 0) {

                        FragmentTransition.to(CommentCommentsFragment.newInstance(comment, user, post), (FragmentActivity) view.getContext(), false, R.id.post_single_comments_fragment);
                    }else{
                        Toast.makeText(view.getContext(), view.getContext().getResources().getString(R.string.no_sub_comments),Toast.LENGTH_LONG).show();
                    }
                }
            });


            Button childCommentsBackBtn = vi.findViewById(R.id.comment_comments_back_btn);
            childCommentsBackBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransition.to(PostCommentFragment.newInstance(post, (PostActivity) activity), (FragmentActivity) view.getContext(), false, R.id.post_single_comments_fragment);
//                    FragmentTransition.to(PostCommentFragment.newInstance(post, this), this, false, R.id.post_single_comments_fragment);
                }
            });


        return vi;
    }
}
