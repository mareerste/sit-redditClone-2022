package com.example.redditcloneapp.post;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Flair;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.enums.ReportReason;
import com.example.redditcloneapp.service.CommunityApiService;
import com.example.redditcloneapp.service.PostApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.community.CommunityPostsFragment;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostEditActivity extends AppCompatActivity {

    static Retrofit retrofitPost = null;
    private Post post;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_edit);
        post = (Post) getIntent().getSerializableExtra("post");
        Button cancelBtn = findViewById(R.id.my_post_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        EditText postTitle = findViewById(R.id.my_post_title_edit);
        postTitle.setText(post.getTitle());
        EditText postText = findViewById(R.id.my_post_text_edit);
        postText.setText(post.getText());

        Spinner mySpinner = (Spinner) findViewById(R.id.my_post_flair_edit);
//        mySpinner.setAdapter(new ArrayAdapter<ReportReason>(this, android.R.layout.simple_spinner_item, post.getCommunity().getFlairs()));
        ArrayAdapter<Flair> adapter = new ArrayAdapter<Flair>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, Mokap.getCommunities().get(0).getFlairs());//TODO fake comm
        mySpinner.setAdapter(adapter);

        Button saveBtn = findViewById(R.id.my_post_save_btn);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                post.setFlair((Flair) mySpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(postTitle.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),R.string.post_title_error, Toast.LENGTH_SHORT).show();
                else if(postText.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),R.string.post_text_error, Toast.LENGTH_SHORT).show();
                else{

                    post.setTitle(postTitle.getText().toString());
                    post.setText(postText.getText().toString());

                    Toast.makeText(getApplicationContext(),post.toString(),Toast.LENGTH_LONG).show();
                    updatePost();
                }


            }
        });

    }

    private void updatePost() {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


            retrofitPost = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        PostApiService postApiService = retrofitPost.create(PostApiService.class);

        Call<Post> call = postApiService.updatePost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(PostEditActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PostEditActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}