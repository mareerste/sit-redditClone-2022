package com.example.redditcloneapp.ui.profile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.adapters.PostAdapter;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.UserApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.ui.access.SignInActivity;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsActivity extends ListActivity {
    static final String TAG = PostsActivity.class.getSimpleName();
    static Retrofit retrofit = null;
    private User user;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_map_layout);
        user = (User) getIntent().getSerializableExtra("user");
        getPosts();
    }

    private void getPosts(){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(getSharedPreferences(SignInActivity.mypreference, MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        UserApiService userApiService = retrofit.create(UserApiService.class);
        Call<List<Post>> call = userApiService.getUserPosts(user.getUsername());
        call.enqueue(new Callback<List<Post>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Post>> call, retrofit2.Response<List<Post>> response) {
                MyPostsAdapter adapter = new MyPostsAdapter(PostsActivity.this, user, response.body());
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }



}