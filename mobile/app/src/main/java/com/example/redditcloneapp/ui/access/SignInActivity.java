package com.example.redditcloneapp.ui.access;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.User;

import java.io.Serializable;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Button button = findViewById(R.id.btn_sign_in);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                EditText usernameTW = findViewById(R.id.sign_in_username);
                String usernameText = usernameTW.getText().toString();

                EditText passwordTW = findViewById(R.id.sign_in_password);
                String passwordText = passwordTW.getText().toString();
                User user = Mokap.login(usernameText,passwordText);

                if (user != null) {
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    intent.putExtra("user", (Serializable) user);
                    startActivity(intent);
                }else{
                    Toast msg = Toast.makeText(getApplicationContext(), R.string.signInError, Toast.LENGTH_SHORT);
                    msg.show();
                }
            }
        });
    }

    public void onClickCreate(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),"Create account", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}