package com.example.redditcloneapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.redditcloneapp.adapters.DrawerController;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.access.SignUpActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationBarMenu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.redditcloneapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://192.168.0.29:8080/RedditClone/"; //PC
//    public static final String BASE_URL = "http://192.168.43.238:8080/RedditClone/"; //PHONE
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) getIntent().getSerializableExtra("user");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        navigationView = binding.navView;
        View hw = navigationView.getHeaderView(0);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_community, R.id.nav_report)
                .setOpenableLayout(drawer)
                .build();

        if(user == null){
            View userView = findViewById(R.id.navbar_with_user);
            userView.setVisibility(View.GONE);
            TextView username = (TextView) hw.findViewById(R.id.nav_header_username);
            TextView description = (TextView) hw.findViewById(R.id.nav_header_description);
            username.setVisibility(View.GONE);
            description.setVisibility(View.GONE);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else{
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);

            View userView = findViewById(R.id.navbar_without_user);
            userView.setVisibility(View.GONE);
            TextView username = (TextView) hw.findViewById(R.id.nav_header_username);
            username.setText(user.getUsername());
            TextView description = (TextView) hw.findViewById(R.id.nav_header_description);
            description.setText(user.getDescription());
        }
        Button btnSignUp = (Button) findViewById(R.id.nav_btn_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignUp = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intentSignUp);
            }
        });

        Button btnSignIn = (Button) findViewById(R.id.nav_btn_sign_in);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignIn = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intentSignIn);
            }
        });

        Button btnSignOut = (Button) findViewById(R.id.nav_btn_sign_out);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                user = null;
//                View userView = findViewById(R.id.navbar_with_user);
//                userView.setVisibility(View.GONE);
//                View userViewNew = findViewById(R.id.navbar_without_user);
//                userViewNew.setVisibility(View.VISIBLE);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
//                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public User getUser(){
        return user;
    }

    public void drawerLocked() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void drawerUnlocked() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
}