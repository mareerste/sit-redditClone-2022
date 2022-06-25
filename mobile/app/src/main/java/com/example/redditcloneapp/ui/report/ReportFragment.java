package com.example.redditcloneapp.ui.report;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.databinding.FragmentReportBinding;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.Report;
import com.example.redditcloneapp.service.ReportApiService;
import com.example.redditcloneapp.service.UserApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunitiesFragment;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;
import com.example.redditcloneapp.ui.profile.MyPostsAdapter;
import com.example.redditcloneapp.ui.profile.PostsActivity;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportFragment extends ListFragment {
    public FragmentReportBinding binding;
    private Community community;
    static Retrofit retrofit = null;

    public ReportFragment(Community community) {
        this.community = community;
    }

    public static ReportFragment newInstance(Community community){return new ReportFragment(community);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ReportViewModel reportViewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        binding = FragmentReportBinding.inflate(inflater, container, false);

        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity().getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.Role, "") != null && !getActivity().getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.Role, "").equals("ROLE_USER")){
            getReports();

        }else{
            Toast.makeText(getContext(), "No access", Toast.LENGTH_LONG).show();

        }

    }

    private void getReports(){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(getActivity().getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReportApiService reportApiService = retrofit.create(ReportApiService.class);
        Call<List<Report>> call = reportApiService.getCommunityReportedPosts(community.getId());
        call.enqueue(new Callback<List<Report>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Report>> call, retrofit2.Response<List<Report>> response) {
//                ReportAdapter adapter = new ReportAdapter(getActivity(), response.body());
//                setListAdapter(adapter);
                System.out.println("RESPONSE BEF" + response.body());
                loadReports(response.body());
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void loadReports(List<Report> loadedReports){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(getActivity().getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ReportApiService reportApiService = retrofit.create(ReportApiService.class);
        System.out.println("COMMUNITY" + community);
        Call<List<Report>> call = reportApiService.getCommunityReportedComments();
        call.enqueue(new Callback<List<Report>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Report>> call, retrofit2.Response<List<Report>> response) {
//                System.out.println(response.body());
//                List<Report> newList = response.body();
                for (Report r:response.body()) {
                    loadedReports.add(r);
                }
                ReportAdapter adapter = new ReportAdapter(getActivity(), loadedReports, community);
                setListAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}