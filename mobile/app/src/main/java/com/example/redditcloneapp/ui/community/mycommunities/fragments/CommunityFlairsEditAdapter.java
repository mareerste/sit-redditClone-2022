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
import com.example.redditcloneapp.model.Flair;
import com.example.redditcloneapp.tools.FragmentTransition;

import java.util.ArrayList;

public class CommunityFlairsEditAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Flair> flairs;
    private Community community;

    public CommunityFlairsEditAdapter(Activity activity, Community community){this.activity = activity; this.flairs=community.getFlairs();this.community = community;}

    @Override
    public int getCount() {
        return flairs.size();
    }

    @Override
    public Object getItem(int i) {
        return flairs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        Flair flair = flairs.get(i);
        if(view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.rule_edit_item, null);
        TextView flairName = vi.findViewById(R.id.my_community_rules_name);
        flairName.setText(flair.getName());
        Button deleteBtn = vi.findViewById(R.id.my_community_rules_delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flairs.remove(i);
                FragmentTransition.to(CommunityFairsListFragment.newInstance(community), (FragmentActivity) activity,false, R.id.my_community_flairs_list_flairs);
            }
        });


        return vi;
    }
}
