package com.example.redditcloneapp.ui.report;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.Reaction;
import com.example.redditcloneapp.model.Report;
import com.example.redditcloneapp.model.enums.ReactionType;
import com.example.redditcloneapp.service.ReactionApiService;
import com.example.redditcloneapp.service.ReportApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;

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

@RequiresApi(api = Build.VERSION_CODES.O)
public class ReportAdapter extends BaseAdapter {
    private Activity activity;
    private List<Report> reports;
    static Retrofit retrofit = null;
    private Community community;

    public ReportAdapter (Activity activity, List<Report> reports, Community community){this.activity = activity;this.reports = reports;this.community = community;}

    @Override
    public int getCount() {
        return reports.size();
    }

    @Override
    public Object getItem(int i) {
        return reports.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        Report report = reports.get(i);

        if(view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.report_item, null);

        System.out.println(report);
        if (report.getPost() != null){
            View postLayout = vi.findViewById(R.id.report_post);
            postLayout.setVisibility(View.VISIBLE);

            TextView postTitle = vi.findViewById(R.id.report_post_title);
            postTitle.setText(report.getPost().getTitle());
            TextView postText = vi.findViewById(R.id.report_post_text);
            postText.setText(report.getPost().getText());
        }

        if (report.getComment() != null){
            View postLayout = vi.findViewById(R.id.report_comment);
            postLayout.setVisibility(View.VISIBLE);

            TextView commentText = vi.findViewById(R.id.report_comment_text);
            commentText.setText(report.getComment().getText());
        }
        TextView userText = vi.findViewById(R.id.report_user);
        TextView time = vi.findViewById(R.id.report_timestamp);
        TextView reasonText = vi.findViewById(R.id.report_reason);

        userText.setText(report.getUser().getUsername());
        time.setText(report.getTimestamp());
        reasonText.setText(report.getReason().toString());

        Button acceptBtn = vi.findViewById(R.id.report_item_btn_accept);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptReport(report);
            }
        });

        Button declineBtn = vi.findViewById(R.id.report_item_btn_reject);
        declineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                declineReport(report);
            }
        });

        return vi;
    }

    private void acceptReport(Report report){
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
        Call<Report> call = reportApiService.acceptReport(report);
        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if(response.isSuccessful()){
//                    TextView karma = view.findViewById(R.id.post_karma);
                    System.out.println(response.body());
                    FragmentTransition.to(ReportFragment.newInstance(community), (FragmentActivity) activity,false,R.id.my_community_fragment);
                }else {
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void declineReport(Report report){
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
        Call<Report> call = reportApiService.declineReport(report);
        call.enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if(response.isSuccessful()){
                    System.out.println(response.body());
                    FragmentTransition.to(ReportFragment.newInstance(community), (FragmentActivity) activity,false,R.id.my_community_fragment);
                }else {
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
