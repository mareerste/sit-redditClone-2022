package com.example.redditcloneapp.ui.report;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Report;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ReportAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Report> reports = Mokap.getReports();

    public ReportAdapter (Activity activity){this.activity = activity;}

    @Override
    public int getCount() {
        return reports.size();
    }

    @Override
    public Object getItem(int i) {
        return reports.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        Report report = reports.get(i);

        if(view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.report_item, null);

        System.out.println(report);
        if (report.getPost() != null){
            View postLayout = vi.findViewById(R.id.report_post);
            postLayout.setVisibility(View.VISIBLE);

            TextView postTitle = vi.findViewById(R.id.report_post_title);
            postTitle.setText(report.getPost().getTitle());
            TextView postText = vi.findViewById(R.id.report_post_text);
            postText.setText(report.getPost().getText());
        }

        if (report.getComment() != null){
            View postLayout = vi.findViewById(R.id.report_comment);
            postLayout.setVisibility(View.VISIBLE);

            TextView commentText = vi.findViewById(R.id.report_comment_text);
            commentText.setText(report.getComment().getText());
        }
        TextView userText = vi.findViewById(R.id.report_user);
        TextView time = vi.findViewById(R.id.report_timestamp);
        TextView reasonText = vi.findViewById(R.id.report_reason);

        userText.setText(report.getUser().getUsername());
        time.setText(report.getTimestamp());
        reasonText.setText(report.getReason().toString());

        return vi;
    }
}
