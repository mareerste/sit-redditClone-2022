package com.example.redditcloneapp.ui.community;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;

import java.text.NumberFormat;

public class CommunityRuleAdapter extends BaseAdapter {

    private Activity activity;
    private Community community;

    public CommunityRuleAdapter (Activity activity, Community community){this.activity = activity;this.community = community;}
    @Override
    public int getCount() {
        return community.getRules().size();
    }

    @Override
    public Object getItem(int i) {
        return community.getRules().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        String rule = community.getRules().get(i);
        int count = i;
        count++;
        String counterText = count+".";
        if(view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.rule_item, null);
        TextView counter = vi.findViewById(R.id.rule_item_num);
        counter.setText(counterText);
        TextView ruleText = vi.findViewById(R.id.rule_item_text);
        ruleText.setText(rule);

        return vi;
    }
}
