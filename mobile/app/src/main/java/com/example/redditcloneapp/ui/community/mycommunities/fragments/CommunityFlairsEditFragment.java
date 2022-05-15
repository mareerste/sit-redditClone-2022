package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Flair;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;

import java.util.ArrayList;

public class CommunityFlairsEditFragment extends Fragment {
    private Community community;

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
                    Flair newFlair = new Flair(flairs.size()+1, newText);
                    flairs.add(newFlair);
                    community.setFlairs(flairs);
                    FragmentTransition.to(CommunityFairsListFragment.newInstance(community), getActivity(),false, R.id.my_community_flairs_list_flairs);
                }else{
                    Toast toast = Toast.makeText(getContext(), R.string.flair_add_error, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
}