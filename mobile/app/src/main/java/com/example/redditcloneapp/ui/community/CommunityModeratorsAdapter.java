package com.example.redditcloneapp.ui.community;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Moderator;

public class CommunityModeratorsAdapter extends BaseAdapter {

    private Activity activity;
    private Community community;

    public CommunityModeratorsAdapter (Activity activity, Community community){this.activity = activity; this.community = community;}
    @Override
    public int getCount() {
        return community.getModerators().size();
    }

    @Override
    public Object getItem(int i) {
        return community.getModerators().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        Moderator moderator = community.getModerators().get(i);
        int count = i;
        count++;
        String counter = count+".";

        if(view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.rule_item, null);
        TextView num = vi.findViewById(R.id.rule_item_num);
        num.setText(counter);
        TextView moderatorText = vi.findViewById(R.id.rule_item_text);
        moderatorText.setText(moderator.getUsername());

        return vi;
    }
}
