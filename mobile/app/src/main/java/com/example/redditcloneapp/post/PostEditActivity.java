package com.example.redditcloneapp.post;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Flair;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.enums.ReportReason;

public class PostEditActivity extends AppCompatActivity {

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
                    mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            post.setFlair((Flair) mySpinner.getSelectedItem());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                    Toast.makeText(getApplicationContext(),post.toString(),Toast.LENGTH_LONG).show();
                    finish();
                }


            }
        });

    }
}