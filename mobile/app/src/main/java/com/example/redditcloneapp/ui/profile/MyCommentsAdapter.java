package com.example.redditcloneapp.ui.profile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Comment;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.Report;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.model.enums.ReportReason;
import com.example.redditcloneapp.post.PostEditActivity;
import com.example.redditcloneapp.service.CommentApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.ui.access.SignInActivity;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCommentsAdapter extends BaseAdapter {

    private Activity activity;
    private List<Comment> comments;
    private User user;
    static Retrofit retrofitComment = null;

    public MyCommentsAdapter(Activity activity, List<Comment> comments, User user) {
        this.activity = activity;
        this.comments = comments;
        this.user = user;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        Comment comment = comments.get(i);
        if(view == null)
            vi = activity.getLayoutInflater().inflate(R.layout.my_post_item, null);
        TextView commentId = vi.findViewById(R.id.my_post_title);
        commentId.setText(comment.getId().toString());

        Button editBtn = vi.findViewById(R.id.my_post_edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(activity, PostEditActivity.class);
//                intent.putExtra("post", post);
//                activity.startActivity(intent);
                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_ban_or_view_user);
                TextView text = dialog.findViewById(R.id.dialog_ban_or_view_text);
                text.setText(comment.getText());
                EditText newText = dialog.findViewById(R.id.dialog_ban_or_view_edit_text);
                newText.setVisibility(View.VISIBLE);


                Button btnLeft = dialog.findViewById(R.id.dialog_ban_or_view_btn_left);
                btnLeft.setText("Submit");
                btnLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!newText.getText().toString().equals("")){
                            comment.setText(newText.getText().toString());
                            updateComment(comment);
                            dialog.dismiss();
                        }else{
                            Toast.makeText(activity.getApplicationContext(),activity.getResources().getString(R.string.comment_text_error),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                Button btnRight = dialog.findViewById(R.id.dialog_ban_or_view_btn_right);
                btnRight.setText("Cancel");
                btnRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        return vi;
    }

    private void updateComment(Comment comment) {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofitComment = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommentApiService commentApiService = retrofitComment.create(CommentApiService.class);
        Call<Comment> call = commentApiService.updateComment(comment);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(response.isSuccessful()){
                    Toast.makeText(activity, "Updated", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(activity, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
