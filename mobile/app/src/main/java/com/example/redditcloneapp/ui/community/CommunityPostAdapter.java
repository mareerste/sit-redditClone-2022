package com.example.redditcloneapp.ui.community;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.Reaction;
import com.example.redditcloneapp.model.Report;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.model.enums.ReactionType;
import com.example.redditcloneapp.model.enums.ReportReason;
import com.example.redditcloneapp.post.PostActivity;
import com.example.redditcloneapp.service.ReactionApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
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

public class CommunityPostAdapter extends BaseAdapter {
    private Activity activity;
    private Community community;
    private List<Post>posts;
    private User user;
    static Retrofit retrofit = null;

    public CommunityPostAdapter (Activity activity, User user, List<Post> posts, Community community){this.activity = activity;this.user = user;this.posts = posts;this.community = community;}

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
        TextView communityTW = vi.findViewById(R.id.post_community);
        Button btnPost = vi.findViewById(R.id.btn_view_post);
        Button btnReport = vi.findViewById(R.id.btn_post_report);
        title.setText(post.getTitle());
        text.setText(post.getText());
//        karma.setText(post.getReactions().toString());
        getPostKarma(post, karma);
        userText.setText(post.getUser().getUsername());
        userText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProfileActivity.class);
                intent.putExtra("user", post.getUser());
                activity.startActivity(intent);
            }
        });
        flair.setText(post.getFlair().getName());
//        communityTW.setText(community.getName());
        communityTW.setVisibility(View.GONE);
        communityTW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(), community.toString(),Toast.LENGTH_SHORT);//TODO fake
                toast.show();
                Intent intent = new Intent(activity, CommunityActivity.class);
                intent.putExtra("user", user );
                intent.putExtra("community", community);
                activity.startActivity(intent);

            }
        });
        date.setText(post.getCreationDate());
//        date.setText(post.getCreationDate().format(DateTimeFormatter
//                .ofLocalizedDate(FormatStyle.LONG)));

        Button voteUp = vi.findViewById(R.id.post_arrow_up);
        voteUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteUpPost(post,karma);
            }
        });

        Button voteDown = vi.findViewById(R.id.post_arrow_down);
        voteDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voteDownPost(post,karma);
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"CLICK NA REPORT",Toast.LENGTH_SHORT).show();
                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_report);
                TextView text = dialog.findViewById(R.id.dialog_report_text);
                text.setText(view.getContext().getResources().getString(R.string.report_post_text) + " " + post.getTitle() + "?");

                Spinner mySpinner = (Spinner) dialog.findViewById(R.id.dialog_report_spinner);
                mySpinner.setAdapter(new ArrayAdapter<ReportReason>(activity, android.R.layout.simple_spinner_item, ReportReason.values()));
                Button btnLeft = dialog.findViewById(R.id.dialog_report_submit);
                btnLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Report report = new Report(Mokap.getReports().size()+1, (ReportReason) mySpinner.getSelectedItem(), user,  post);
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

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity, PostActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("post", post);
                activity.startActivity(intent);
            }
        });
        if(user == null){
            vi.findViewById(R.id.post_arrow_up).setVisibility(View.GONE);
            vi.findViewById(R.id.post_arrow_down).setVisibility(View.GONE);
            btnReport.setVisibility(View.GONE);
            userText.setClickable(false);
        }

        return vi;
    }

    private void getPostKarma(Post post, TextView karma) {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReactionApiService reactionApiService = retrofit.create(ReactionApiService.class);

        Call<Integer> call = reactionApiService.getPostsKarma(post.getId());
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

    private void voteUpPost(Post post, TextView karma){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ReactionApiService reactionApiService = retrofit.create(ReactionApiService.class);
        Reaction reaction = new Reaction(ReactionType.UPVOTE,post.getId(), null);

        Call<Reaction> call = reactionApiService.saveReaction(reaction);
        call.enqueue(new Callback<Reaction>() {
            @Override
            public void onResponse(Call<Reaction> call, Response<Reaction> response) {
                if(response.isSuccessful()){
//                    TextView karma = view.findViewById(R.id.post_karma);
                    getPostKarma(post,karma);
                }else {
                    Toast.makeText(activity, activity.getResources().getString(R.string.up_vote_error_msg_post), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Reaction> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void voteDownPost(Post post, TextView karma) {
        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ReactionApiService reactionApiService = retrofit.create(ReactionApiService.class);
        Reaction reaction = new Reaction(ReactionType.DOWNVOTE, post.getId(), null);

        Call<Reaction> call = reactionApiService.saveReaction(reaction);
        call.enqueue(new Callback<Reaction>() {
            @Override
            public void onResponse(Call<Reaction> call, Response<Reaction> response) {
                if (response.isSuccessful()) {
//                    TextView karma = view.findViewById(R.id.post_karma);
                    getPostKarma(post, karma);
                } else {
                    Toast.makeText(activity, activity.getResources().getString(R.string.up_vote_error_msg_post), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Reaction> call, Throwable t) {
                Toast.makeText(activity, "System error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
