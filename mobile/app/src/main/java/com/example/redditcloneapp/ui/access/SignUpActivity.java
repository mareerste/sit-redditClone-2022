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

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.User;

public class SignUpActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText usernameET = findViewById(R.id.sign_up_username);
        EditText passwordET = findViewById(R.id.sign_up_password);
        EditText mailET = findViewById(R.id.sign_up_email);
        EditText descriptionET = findViewById(R.id.sign_up_description);

        Button btnSubmit = findViewById(R.id.sign_up_btn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameET.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.user_username_error, Toast.LENGTH_LONG).show();
                    usernameET.setBackgroundResource(R.drawable.textview_border);
                    usernameET.setHint("user123");
                } else if(passwordET.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.user_password_error, Toast.LENGTH_LONG).show();
                    passwordET.setBackgroundResource(R.drawable.textview_border);
                    passwordET.setHint("user123");
                } else if(mailET.getText().toString().equals("") || !mailET.getText().toString().contains("@")) {
                    Toast.makeText(getApplicationContext(), R.string.user_mail_error, Toast.LENGTH_LONG).show();
                    mailET.setBackgroundResource(R.drawable.textview_border);
                    mailET.setHint("user123@gmail.com");
                } else if(descriptionET.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.user_description_error, Toast.LENGTH_LONG).show();
                    descriptionET.setBackgroundResource(R.drawable.textview_border);
                    descriptionET.setHint("Professional reddit user");
                } else {
                    User user = new User(usernameET.getText().toString(), passwordET.getText().toString(), mailET.getText().toString(), descriptionET.getText().toString());
                    Toast.makeText(getApplicationContext(), user.toString(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}