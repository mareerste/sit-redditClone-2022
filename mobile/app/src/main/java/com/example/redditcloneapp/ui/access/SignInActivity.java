package com.example.redditcloneapp.ui.access;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.adapters.PostAdapter;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.PostApiService;
import com.example.redditcloneapp.service.UserApiService;
import com.example.redditcloneapp.service.client.ApiClient;
import com.example.redditcloneapp.service.client.ApiClientService;
import com.example.redditcloneapp.service.client.Role;
import com.example.redditcloneapp.service.client.UserLogin;
import com.example.redditcloneapp.service.client.TokenResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {
//    static final String BASE_URL = "http://192.168.0.29:8080/RedditClone/";
    static final String TAG = SignInActivity.class.getSimpleName();
    static Retrofit retrofit = null;
    static Retrofit retrofitLogin = null;
    private SharedPreferences sharedPreferences;
    private boolean validation = false;

    public static final String mypreference = "mypreference";
    public static final String Username = "usernameKey";
    public static final String Role = "roleKey";
    public static final String TOKEN = "tokenKey";


    EditText usernameTW, passwordTW;
    Button buttonSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameTW = findViewById(R.id.sign_in_username);
        passwordTW = findViewById(R.id.sign_in_password);
        buttonSignIn = findViewById(R.id.btn_sign_in);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(isValid())
                    getLogin();
            }
        });
    }

    private boolean isValid(){
        if(usernameTW.getText().toString().equals("")) {
            Toast.makeText(SignInActivity.this, R.string.user_username_error, Toast.LENGTH_SHORT).show();
            usernameTW.setBackgroundResource(R.drawable.textview_border);
            return false;
        }else{
            usernameTW.setBackgroundResource(0);
        }

        if(passwordTW.getText().toString().equals("")) {
            Toast.makeText(SignInActivity.this, R.string.user_password_error, Toast.LENGTH_SHORT).show();
            passwordTW.setBackgroundResource(R.drawable.textview_border);
            return false;
        }
        else{
            passwordTW.setBackgroundResource(0);
        }
        return true;
    }

    private void getLogin(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        ApiClientService apiClientService = retrofit.create(ApiClientService.class);

        String usernameText = usernameTW.getText().toString();
        String passwordText = passwordTW.getText().toString();
        UserLogin userLogin = new UserLogin(usernameText, passwordText);
        Call<TokenResponse> call = apiClientService.getToken(userLogin);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                    if(response.isSuccessful()){
//                        if(role.equals(Role.USER.toString()))
//                            System.out.println("USER LOGGED");
//                        else if(role.equals(Role.MODERATOR.toString()))
//                            System.out.println("MODERATOR LOGGED");
//                        else if(role.equals(Role.ADMINISTRATOR.toString()))
//                            System.out.println("ADMIN LOGGED");
                        SharedPreferences sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(Username, response.body().getUsername())
                                    .putString(Role,response.body().getRole())
                                    .putString(TOKEN,response.body().getAccess_token())
                                    .commit();

                        getUser(response.body());
                    }else{
                        Toast.makeText(getApplicationContext(), R.string.signInError, Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(SignInActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMain(User user){
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onClickCreate(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),"Create account", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
        startActivity(intent);
    }

    private void getUser(TokenResponse tokenResponse){
        if (retrofitLogin == null) {
            retrofitLogin = new Retrofit.Builder()
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        UserApiService userApiService = retrofitLogin.create(UserApiService.class);


        Call<User> call = userApiService.getUser(tokenResponse.getUsername());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful()){
                    getSharedPreferences("loggedUser",MODE_PRIVATE)
                            .edit()
                            .putString("user",response.body().getUsername())
                            .commit();
                    loadMain(response.body());
                }else{
                    Toast.makeText(getApplicationContext(), R.string.signInError, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignInActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}