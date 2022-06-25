package com.example.redditcloneapp.post;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import com.example.redditcloneapp.model.Reaction;
import com.example.redditcloneapp.model.Report;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.model.enums.ReactionType;
import com.example.redditcloneapp.model.enums.ReportReason;
import com.example.redditcloneapp.service.CommentApiService;
import com.example.redditcloneapp.service.ReactionApiService;
import com.example.redditcloneapp.service.ReportApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.profile.ProfileActivity;

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

public class CommentAdapter extends BaseAdapter {
    private Activity activity;
    private Post post;
    private List<Comment> comments;
    private User user;
    private Boolean userBlocked;

    static Retrofit retrofitComment = null;
    static Retrofit retrofit = null;

//    public CommentAdapter (Activity activity, Post post, User user){this.activity = activity;this.post = post;this.comments=post.getComments();this.user = user;}
//    public CommentAdapter (Activity activity, Post post, User user){this.activity = activity;this.post = post;this.comments=post.getComments();this.user = user;}
//    public CommentAdapter (Activity activity, Comment comment,User user, Post post){this.activity = activity ;this.comments=comment.getChildComments();this.user = user;this.post = post;}
    public CommentAdapter (Activity activity, List<Comment> comments,User user, Post post, Boolean userBlocked){this.activity = activity ;this.comments=comments;this.user = user;this.post = post;this.userBlocked = userBlocked;}
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
        getCommentKarma(comment,karma);
//        karma.setText(comment.getReactions().toString());
        Button reportBtn = vi.findViewById(R.id.comment_report_btn);
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
                        Report report = new Report((ReportReason) mySpinner.getSelectedItem(), comment);
                        saveReport(report);
                        dialog.dismiss();
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

        Button voteUp = vi.findViewById(R.id.comment_vote_up_btn);
        voteUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteUpComment(comment,karma);
            }
        });

        Button voteDown = vi.findViewById(R.id.comment_vote_down_btn);
        voteDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteDownComment(comment,karma);
            }
        });

        if(user == null || userBlocked){
            vi.findViewById(R.id.comment_vote_up_btn).setVisibility(View.GONE);
            vi.findViewById(R.id.comment_vote_down_btn).setVisibility(View.GONE);
            vi.findViewById(R.id.comment_replay_btn).setVisibility(View.GONE);
            vi.findViewById(R.id.comment_report_btn).setVisibility(View.GONE);
            userText.setClickable(false);
        }

            Button replyBtn = vi.findViewById(R.id.comment_replay_btn);
            replyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(activity);
                    dialog.setContentView(R.layout.reply_on_comment);
                    TextView text = dialog.findViewById(R.id.reply_comment_text);
                    Button save = dialog.findViewById(R.id.reply_comment_btn);
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(text.getText().toString() != ""){
                                Comment newComment = new Comment(text.getText().toString());
                                saveComment(newComment, comment);
                            }else{
                                Toast.makeText(view.getContext(),view.getContext().getResources().getString(R.string.no_sub_comments),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog.show();
                }
            });

            Button childCommentsBtn = vi.findViewById(R.id.comment_comments_btn);
            childCommentsBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(comment.getChildComments().size() != 0) {

                        FragmentTransition.to(CommentCommentsFragment.newInstance(comment, user, post, userBlocked), (FragmentActivity) view.getContext(), false, R.id.post_single_comments_fragment);
                    }else{
                        Toast.makeText(view.getContext(), view.getContext().getResources().getString(R.string.no_sub_comments),Toast.LENGTH_LONG).show();
                    }
                }
            });


            Button childCommentsBackBtn = vi.findViewById(R.id.comment_comments_back_btn);
            childCommentsBackBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransition.to(PostCommentFragment.newInstance(post, (PostActivity) activity, userBlocked), (FragmentActivity) view.getContext(), false, R.id.post_single_comments_fragment);
//                    FragmentTransition.to(PostCommentFragment.newInstance(post, this), this, false, R.id.post_single_comments_fragment);
                }
            });


        return vi;
    }

    private void saveComment(Comment newComment, Comment oldComment) {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


            retrofitComment = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        CommentApiService commentApiService = retrofitComment.create(CommentApiService.class);
        Call<Comment> call = commentApiService.saveComment(newComment);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(response.isSuccessful()){
                    ArrayList<Comment> comments = oldComment.getChildComments();
                    comments.add(response.body());
                    System.out.println("OLD COMMEND BEFORE"+oldComment.toString());
                    oldComment.setChildComments(comments);
                    System.out.println("OLD COMMEND "+oldComment.toString());
                    System.out.println("RESPONSE "+response.body().toString());
                    Toast.makeText(activity, oldComment.toString(), Toast.LENGTH_LONG).show();
                    updateComment(oldComment);
                }else{
                    Toast.makeText(activity, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateComment(Comment comment) {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


            retrofitComment = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        CommentApiService commentApiService = retrofitComment.create(CommentApiService.class);
        Call<Comment> call = commentApiService.updateComment(comment);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(response.isSuccessful()){
                    Intent intent = activity.getIntent();
                    activity.finish();
                    activity.startActivity(intent);
                }else{
                    Toast.makeText(activity, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCommentKarma(Comment comment, TextView karma) {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofitComment = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReactionApiService reactionApiService = retrofitComment.create(ReactionApiService.class);

        Call<Integer> call = reactionApiService.getCommentKarma(comment.getId());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
//                    karma.setText(post.getReactions().toString());
                    karma.setText(response.body().toString());
                }else{
                    Toast.makeText(activity, response.toString(), Toast.LENGTH_LONG).show();
                    System.out.println("Not succ" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("GRESKA" + t.getMessage());
            }
        });
    }

    private void voteUpComment(Comment comment, TextView karma){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofitComment = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ReactionApiService reactionApiService = retrofitComment.create(ReactionApiService.class);
        Reaction reaction = new Reaction(ReactionType.UPVOTE,null, comment.getId());

        Call<Reaction> call = reactionApiService.saveReaction(reaction);
        call.enqueue(new Callback<Reaction>() {
            @Override
            public void onResponse(Call<Reaction> call, Response<Reaction> response) {
                if(response.isSuccessful()){
//                    TextView karma = view.findViewById(R.id.post_karma);
                    getCommentKarma(comment,karma);
                }else {
                    Toast.makeText(activity, activity.getResources().getString(R.string.up_vote_error_msg_comment), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Reaction> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void voteDownComment(Comment comment, TextView karma) {
        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofitComment = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ReactionApiService reactionApiService = retrofitComment.create(ReactionApiService.class);
        Reaction reaction = new Reaction(ReactionType.DOWNVOTE, null, comment.getId());

        Call<Reaction> call = reactionApiService.saveReaction(reaction);
        call.enqueue(new Callback<Reaction>() {
            @Override
            public void onResponse(Call<Reaction> call, Response<Reaction> response) {
                if (response.isSuccessful()) {
//                    TextView karma = view.findViewById(R.id.post_karma);
                    getCommentKarma(comment,karma);
                } else {
                    Toast.makeText(activity, activity.getResources().getString(R.string.up_vote_error_msg_comment), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Reaction> call, Throwable t) {
                Toast.makeText(activity, "System error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveReport (Report report){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReportApiService reportApiService = retrofit.create(ReportApiService.class);

        Call<Report> call = reportApiService.saveReport(report);
        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if(response.isSuccessful()){
                    Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.comment_reported),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.report_comment_error_msg),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
