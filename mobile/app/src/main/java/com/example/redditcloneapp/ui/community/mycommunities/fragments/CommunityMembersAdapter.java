package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.redditcloneapp.model.Banned;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Report;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.BannedApiService;
import com.example.redditcloneapp.service.ReportApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;
import com.example.redditcloneapp.ui.profile.ProfileActivity;
import com.example.redditcloneapp.ui.report.ReportFragment;

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

public class CommunityMembersAdapter extends BaseAdapter {
    private Activity activity;
    private List<User> users;
    private Community community;
    private User moderator;
    static Retrofit retrofit = null;
//TODO fake users
    @RequiresApi(api = Build.VERSION_CODES.O)
    public CommunityMembersAdapter(Activity activity, Community community, User moderator, List<User> users){this.activity = activity; this.users = users;this.community = community;this.moderator = moderator;}

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        User user = users.get(i);
        if(view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.community_member_item, null);
        TextView username = vi.findViewById(R.id.my_community_members_username);
        username.setText(user.getUsername());

        Button btnBlock = vi.findViewById(R.id.my_community_members_block_btn);
        isBanned(btnBlock, user);

        Button btnView = vi.findViewById(R.id.my_community_members_view_btn);
        btnView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProfileActivity.class);
                intent.putExtra("user", user);
                activity.startActivity(intent);
            }
        });


        return vi;
    }


    private void isBanned(Button btn, User user){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BannedApiService bannedApiService = retrofit.create(BannedApiService.class);
        Call<Boolean> call = bannedApiService.isReported(community.getId(),user.getUsername());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body()){
                    btn.setText(R.string.unblock);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            btn.setText(R.string.block);
                            deleteBanned(user);
                        }
                    });
                }else {
                    btn.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(View view) {
//                            Banned banned = new Banned(Mokap.getBanneds().size()+1, moderator, community, user);
                            Banned banned = new Banned(community,user);
                            btn.setText(R.string.unblock);
                            saveBanned(banned);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveBanned(Banned banned){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BannedApiService bannedApiService = retrofit.create(BannedApiService.class);
        Call<Banned> call = bannedApiService.saveBanned(banned);
        call.enqueue(new Callback<Banned>() {
            @Override
            public void onResponse(Call<Banned> call, Response<Banned> response) {
                FragmentTransition.to(CommunityMembersFragment.newInstance(community), (FragmentActivity) activity,false,R.id.my_community_fragment);
            }

            @Override
            public void onFailure(Call<Banned> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteBanned(User user){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BannedApiService bannedApiService = retrofit.create(BannedApiService.class);
        Call<Void> call = bannedApiService.deleteBannedByUser(community.getId(),user.getUsername());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                FragmentTransition.to(CommunityMembersFragment.newInstance(community), (FragmentActivity) activity,false,R.id.my_community_fragment);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
