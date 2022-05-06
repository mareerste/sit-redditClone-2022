package com.example.redditcloneapp.ui.community;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Flair;

public class CommunityFlairsAdapter extends BaseAdapter {

    private Activity activity;
    private Community community;

    public CommunityFlairsAdapter(Activity activity, Community community){this.activity = activity; this.community=community;}

    @Override
    public int getCount() {
        return community.getFlairs().size();
    }

    @Override
    public Object getItem(int i) {
        return community.getFlairs().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        Flair flair = community.getFlairs().get(i);
        int count = i;
        count++;
        String counter = count+".";
        if(view == null){
            vi = activity.getLayoutInflater().inflate(R.layout.rule_item, null);
        }

        TextView numText = vi.findViewById(R.id.rule_item_num);
        numText.setText(counter);
        TextView flairText = vi.findViewById(R.id.rule_item_text);
        flairText.setText(flair.getName());
        return vi;
    }
}
