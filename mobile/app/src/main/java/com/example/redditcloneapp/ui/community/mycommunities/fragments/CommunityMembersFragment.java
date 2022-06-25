package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.CommunityApiService;
import com.example.redditcloneapp.service.UserApiService;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.community.CommunityPostsFragment;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityMembersFragment extends ListFragment {
    static Retrofit retrofit = null;
    private Community community;

    public CommunityMembersFragment(Community community) {
        this.community = community;
    }

    public static CommunityMembersFragment newInstance(Community community){return new CommunityMembersFragment(community);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUsers();

//        CommunityMembersAdapter adapter = new CommunityMembersAdapter(getActivity(), ((MyCommunityActivity)getActivity()).getCommunity(), ((MyCommunityActivity)getActivity()).getUser());
//        setListAdapter(adapter);
    }

    private void getUsers(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        UserApiService userApiService = retrofit.create(UserApiService.class);
        Call<List<User>> call = userApiService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                CommunityMembersAdapter adapter = new CommunityMembersAdapter(getActivity(), ((MyCommunityActivity)getActivity()).getCommunity(), ((MyCommunityActivity)getActivity()).getUser(), response.body());
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}