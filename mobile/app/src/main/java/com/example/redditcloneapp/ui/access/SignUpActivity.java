package com.example.redditcloneapp.ui.access;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.UserApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    static Retrofit retrofit = null;
    static final String TAG = SignUpActivity.class.getSimpleName();
    private boolean validation = false;
    EditText usernameET, passwordET, mailET, descriptionET;
    Button btnSubmit;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usernameET = findViewById(R.id.sign_up_username);
        passwordET = findViewById(R.id.sign_up_password);
        mailET = findViewById(R.id.sign_up_email);
        descriptionET = findViewById(R.id.sign_up_description);

        btnSubmit = findViewById(R.id.sign_up_btn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isValid()){
                    User user = new User(usernameET.getText().toString(), passwordET.getText().toString(), mailET.getText().toString(), descriptionET.getText().toString());
                    saveUser(user);
                }
            }
        });
    }

    private boolean isValid(){
        usernameET.setBackgroundResource(0);
        passwordET.setBackgroundResource(0);
        mailET.setBackgroundResource(0);
        descriptionET.setBackgroundResource(0);
        if(usernameET.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), R.string.user_username_error, Toast.LENGTH_LONG).show();
            usernameET.setBackgroundResource(R.drawable.textview_border);
            usernameET.setHint("user123");
            return false;
        } else if(passwordET.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), R.string.user_password_error, Toast.LENGTH_LONG).show();
            passwordET.setBackgroundResource(R.drawable.textview_border);
            passwordET.setHint("user123");
            return false;
        } else if(mailET.getText().toString().equals("") || !mailET.getText().toString().contains("@")) {
            Toast.makeText(getApplicationContext(), R.string.user_mail_error, Toast.LENGTH_LONG).show();
            mailET.setBackgroundResource(R.drawable.textview_border);
            mailET.setHint("user123@gmail.com");
            return false;
        } else if(descriptionET.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), R.string.user_description_error, Toast.LENGTH_LONG).show();
            descriptionET.setBackgroundResource(R.drawable.textview_border);
            descriptionET.setHint("Professional reddit user");
            return false;
        } else {
            return true;
        }
    }

    private void saveUser(User user) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        UserApiService userApiService = retrofit.create(UserApiService.class);

        Call<User> call = userApiService.saveUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),R.string.signUpError, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}