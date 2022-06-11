package com.example.redditcloneapp.ui.community;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.adapters.PostAdapter;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.service.CommunityApiService;
import com.example.redditcloneapp.service.PostApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityPostsFragment extends ListFragment {
    static Retrofit retrofit = null;
    static final String TAG = CommunityActivity.class.getSimpleName();
    private Community community;

    public CommunityPostsFragment(Community community) {
        this.community = community;
    }

    public static CommunityPostsFragment newInstance(Community community){return new CommunityPostsFragment(community);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPosts();
//        System.out.println("TESTAD"+ Mokap.getPostsForCommunity(((CommunityActivity)getActivity()).getCommunity()));
    }

    private void getPosts(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        CommunityApiService communityApiService = retrofit.create(CommunityApiService.class);
        Call<List<Post>> call = communityApiService.getCommunityPosts(community.getId());
        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, retrofit2.Response<List<Post>> response) {
                CommunityPostAdapter adapter = new CommunityPostAdapter(getActivity(),((CommunityActivity)getActivity()).getUser(),response.body());
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }



}

