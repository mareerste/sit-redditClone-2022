package com.example.redditcloneapp.ui.community;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Administrator;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.model.Moderator;
import com.example.redditcloneapp.model.User;

public class CommunityModeratorsAdapter extends BaseAdapter {

    private Activity activity;
    private Community community;
    private User user;

    public CommunityModeratorsAdapter (Activity activity, Community community,User user){this.activity = activity; this.community = community;this.user = user;}
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
        if (user instanceof Administrator){
            moderatorText.setClickable(true);
            moderatorText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(activity);
                    dialog.setContentView(R.layout.dialog_ban_or_view_user);
                    TextView text = dialog.findViewById(R.id.dialog_ban_or_view_text);
                    text.setText(moderator.getUsername());
                    Button btnLeft = dialog.findViewById(R.id.dialog_ban_or_view_btn_left);
                    btnLeft.setText("Suspend");
                    Button btnRight = dialog.findViewById(R.id.dialog_ban_or_view_btn_right);
                    btnRight.setText(R.string.view);
                    dialog.setTitle("Moderators");
                    dialog.show();
                }
            });
        }


        return vi;
    }
}
