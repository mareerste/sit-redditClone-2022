package com.example.redditcloneapp.ui.community.mycommunities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Moderator;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.service.UserApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.access.SignUpActivity;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.profile.MyPostsAdapter;
import com.example.redditcloneapp.ui.profile.PostsActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCommunitiesFragment extends ListFragment {
    static Retrofit retrofit = null;
    private List<Community> myCommunities = new ArrayList<>();

    public static MyCommunitiesFragment newInstance(){return new MyCommunitiesFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Community community = myCommunities.get(position);
        Intent intent = new Intent((MainActivity)getActivity(),MyCommunityActivity.class);
        intent.putExtra("community", community);
        intent.putExtra("user", ((MainActivity)getActivity()).getUser());
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if(((MainActivity)getActivity()).getUser() instanceof Moderator) {
//
//            //naci communitye za usera//TODO mycommunities
////            MyCommunitiesAdapter adapter = new MyCommunitiesAdapter(getActivity(), Mokap.getUsersCommunities((Moderator) ((MainActivity) getActivity()).getUser()));
////            setListAdapter(adapter);
//        }
        getUserCommunities();
    }

    private void getUserCommunities(){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(getActivity().getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApiService userApiService = retrofit.create(UserApiService.class);
        Call<List<Community>> call = userApiService.getUserCommunities(((MainActivity) getActivity()).getUser().getUsername());
        call.enqueue(new Callback<List<Community>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Community>> call, retrofit2.Response<List<Community>> response) {
                myCommunities = response.body();
                MyCommunitiesAdapter adapter = new MyCommunitiesAdapter(getActivity(), response.body());
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Community>> call, Throwable t) {
                Toast.makeText(getActivity(), "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}