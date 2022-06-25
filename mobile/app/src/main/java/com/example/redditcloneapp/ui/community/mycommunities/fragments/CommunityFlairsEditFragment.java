package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Flair;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.service.CommunityApiService;
import com.example.redditcloneapp.service.FlairApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.community.CommunityPostsFragment;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityFlairsEditFragment extends Fragment {
    private Community community;
    static Retrofit retrofit = null;

    public CommunityFlairsEditFragment(Community community) {
        this.community = community;
    }

    public static CommunityFlairsEditFragment newInstance(Community community){return new CommunityFlairsEditFragment(community);}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransition.to(CommunityFairsListFragment.newInstance(community), getActivity(),false,R.id.my_community_flairs_list_flairs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community_flairs_edit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Community community = ((MyCommunityActivity) getActivity()).getCommunity();
        EditText flairText = getActivity().findViewById(R.id.my_community_flairs_text_flair);

        Button addBtn = getActivity().findViewById(R.id.my_community_flairs_btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newText = flairText.getText().toString();
//                Toast toast = Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT);
//                toast.show();
                if (!newText.equals("")){
                    ArrayList<Flair> flairs = community.getFlairs();
                    Flair newFlair = new Flair(newText);
                    saveFlair(newFlair,community);
                }else{
                    Toast toast = Toast.makeText(getContext(), R.string.flair_add_error, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void saveFlair(Flair flair, Community community) {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(getActivity().getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlairApiService flairApiService = retrofit.create(FlairApiService.class);

        Call<Flair> call = flairApiService.saveFlair(flair);
        call.enqueue(new Callback<Flair>() {
            @Override
            public void onResponse(Call<Flair> call, Response<Flair> response) {
                if(response.isSuccessful()){
                    ArrayList<Flair> flairs = community.getFlairs();
                    flairs.add(response.body());
                    community.setFlairs(flairs);
                    FragmentTransition.to(CommunityFairsListFragment.newInstance(community), getActivity(),false, R.id.my_community_flairs_list_flairs);
                }else{
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Flair> call, Throwable t) {
                Toast.makeText(getActivity(), "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}