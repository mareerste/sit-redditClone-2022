package com.example.redditcloneapp.ui.profile;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.adapters.PostAdapter;
import com.example.redditcloneapp.databinding.ActivityProfileBinding;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.PostApiService;
import com.example.redditcloneapp.service.UserApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.redditcloneapp.R;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends Activity {

    private User user;
    static Retrofit retrofit = null;
    static final String TAG = ProfileActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user = (User) getIntent().getSerializableExtra("user");
        TextView textUsername = findViewById(R.id.profile_username_activity);
        textUsername.setText(user.getUsername());
        TextView textMail = findViewById(R.id.profile_email_activity);
        textMail.setText(user.getEmail());
        TextView textDate = findViewById(R.id.profile_reg_date_activity);
//        textDate.setText(user.getRegistrationDate().format(DateTimeFormatter
//                .ofLocalizedDate(FormatStyle.LONG)));
        textDate.setText(user.getRegistrationDate());
        TextView textDescr = findViewById(R.id.profile_description_activity);
        textDescr.setText(user.getDescription());
        getUser();
    }

    private void getUser(){

        String username = getSharedPreferences(SignInActivity.mypreference, MODE_PRIVATE).getString(SignInActivity.Username, "");
        System.out.println("USERNAME"+username);

        retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApiService userApiService = retrofit.create(UserApiService.class);
        Call<User> call = userApiService.getUser(username);
        call.enqueue(new Callback<User>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }


}