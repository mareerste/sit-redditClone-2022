package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.tools.FragmentTransition;

import java.util.ArrayList;
import java.util.List;

public class CommunityRulesAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<String> rules;
    private Community community;

    public CommunityRulesAdapter (Activity activity, Community community){this.activity = activity;this.rules = community.getRules();this.community = community;}
    @Override
    public int getCount() {
        return rules.size();
    }

    @Override
    public Object getItem(int i) {
        return rules.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        String rule = rules.get(i);
        if(view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.rule_edit_item,null);
        TextView ruleName = vi.findViewById(R.id.my_community_rules_name);
        ruleName.setText(rule);
        Button deleteBtn = vi.findViewById(R.id.my_community_rules_delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rules.remove(i);
                FragmentTransition.to(CommunityRulesListFragment.newInstance(community), (FragmentActivity) activity,false,R.id.my_community_rules_list_rules);
            }
        });

        return vi;
    }
}
