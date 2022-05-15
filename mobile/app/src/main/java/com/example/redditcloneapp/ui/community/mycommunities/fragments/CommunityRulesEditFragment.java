package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;

import java.util.ArrayList;

public class CommunityRulesEditFragment extends Fragment {
    private Community community;

    public CommunityRulesEditFragment(Community community) {
        this.community = community;
    }

    public static CommunityRulesEditFragment newInstance(Community community){return new CommunityRulesEditFragment(community);}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransition.to(CommunityRulesListFragment.newInstance(community), getActivity(),false,R.id.my_community_rules_list_rules);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community_rules_edit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Community community = ((MyCommunityActivity) getActivity()).getCommunity();
        EditText ruleText = getActivity().findViewById(R.id.my_community_rules_text_rule);

        Button addBtn = getActivity().findViewById(R.id.my_community_rules_btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newText = ruleText.getText().toString();
//                Toast toast = Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT);
//                toast.show();
                if (!newText.equals("")){
                    ArrayList<String> rules = community.getRules();
                    rules.add(newText);
                    community.setRules(rules);
                    FragmentTransition.to(CommunityRulesListFragment.newInstance(community), getActivity(),false,R.id.my_community_rules_list_rules);
                }else{
                    Toast toast = Toast.makeText(getContext(), R.string.rule_add_error, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


//        FragmentTransition.to(CommunityRulesListFragment.newInstance(), (FragmentActivity) activity,false,R.id.my_community_rules_list_rules);
    }
}