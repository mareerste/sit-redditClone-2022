package com.example.redditcloneapp.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.Report;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.model.enums.ReportReason;
import com.example.redditcloneapp.post.PostActivity;
import com.example.redditcloneapp.service.PostApiService;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.profile.ProfileActivity;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostAdapter extends BaseAdapter {

    private Activity activity;
    private User user;
    private List<Post> posts;
    private Community community;

    static Retrofit retrofit = null;

    public PostAdapter(Activity activity, User user, List<Post> posts){this.activity = activity;this.user=user;this.posts = posts;}

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int getCount() { return posts.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Object getItem(int i) {return posts.get(i); }

    @Override
    public long getItemId(int i) { return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Post post = posts.get(position);
//        community = getPostCommunity(post);

        if(convertView == null){
            vi = activity.getLayoutInflater().inflate(R.layout.post_item, null);}
        TextView title = (TextView) vi.findViewById(R.id.post_title);
        TextView text = (TextView) vi.findViewById(R.id.post_text);
        TextView karma = (TextView) vi.findViewById(R.id.post_karma);
        karma.setText(post.getReactions().toString());
        TextView userText = vi.findViewById(R.id.post_user);
        TextView flair = vi.findViewById(R.id.post_flair);
        TextView date = vi.findViewById(R.id.post_date);
        TextView communityTW = vi.findViewById(R.id.post_community);
        Button btnPost = vi.findViewById(R.id.btn_view_post);
        Button btnReport = vi.findViewById(R.id.btn_post_report);
        title.setText(post.getTitle());
        text.setText(post.getText());

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        communityTW.setText("community");//TODO Fake comm
        communityTW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CommunityActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("post", post);
//                intent.putExtra("community", community);//TODO comm
                activity.startActivity(intent);

            }
        });
//        date.setText(post.getCreationDate().format(DateTimeFormatter
//                .ofLocalizedDate(FormatStyle.LONG)));
        date.setText(post.getCreationDate());

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
        if(user == null){
            vi.findViewById(R.id.post_vote_layout).setVisibility(View.GONE);
            btnReport.setVisibility(View.GONE);
            userText.setClickable(false);
        }

        return vi;
    }

    public Community getPostCommunity(Post post){
        final Community[] returnCommunity = new Community[1];
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        PostApiService postApiService = retrofit.create(PostApiService.class);
        Call<Community> call = postApiService.getCommunityForPost(post.getId());
        call.enqueue(new Callback<Community>() {

            @Override
            public void onResponse(Call<Community> call, Response<Community> response) {
                returnCommunity[0] = response.body();
                System.out.println("RESPONSE: " + response.body().toString());
                Toast.makeText(activity.getApplicationContext(), response.body().toString(),Toast.LENGTH_LONG).show();
                community = response.body();
            }

            @Override
            public void onFailure(Call<Community> call, Throwable t) {
                System.out.println("RESPONSE: " + t.toString());
                Log.e(MainActivity.TAG, t.toString());
            }
        });
        return returnCommunity[0];
    }
}