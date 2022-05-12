package com.example.redditcloneapp.ui.profile;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.example.redditcloneapp.databinding.ActivityProfileBinding;
import com.example.redditcloneapp.model.User;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.redditcloneapp.R;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ProfileActivity extends Activity {

    private User user;

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
        textDate.setText(user.getRegistrationDate().format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)));
        TextView textDescr = findViewById(R.id.profile_description_activity);
        textDescr.setText(user.getDescription());
    }

}