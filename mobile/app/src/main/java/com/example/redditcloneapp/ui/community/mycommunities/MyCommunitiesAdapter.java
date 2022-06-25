package com.example.redditcloneapp.ui.community.mycommunities;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;

import java.util.ArrayList;
import java.util.List;

public class MyCommunitiesAdapter extends BaseAdapter {
    private Activity activity;
    private List<Community> communities;

    public MyCommunitiesAdapter (Activity activity, List<Community> communities){this.activity = activity; this.communities = communities;}

    @Override
    public int getCount() {
        return communities.size();
    }

    @Override
    public Object getItem(int i) {
        return communities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        Community community = communities.get(i);

        int count = i;
        count++;
        String counterText = count+".";

        if (view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.rule_item, null);
        TextView counter = vi.findViewById(R.id.rule_item_num);
        counter.setText(counterText);
        TextView commText = vi.findViewById(R.id.rule_item_text);
        commText.setText(community.getName());

        return vi;
    }
}
