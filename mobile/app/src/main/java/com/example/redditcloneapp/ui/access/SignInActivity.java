package com.example.redditcloneapp.ui.access;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.redditcloneapp.R;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void onClick(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),"Create account", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}