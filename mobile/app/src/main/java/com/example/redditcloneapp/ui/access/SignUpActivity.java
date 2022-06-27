package com.example.redditcloneapp.ui.access;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.ImageApiService;
import com.example.redditcloneapp.service.UserApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FileUtil;
import com.example.redditcloneapp.ui.community.CommunityActivity;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    static Retrofit retrofit = null;
    static final String TAG = SignUpActivity.class.getSimpleName();
    Uri uri;
    private boolean validation = false;
    EditText usernameET, passwordET, mailET, descriptionET;
    Button btnSubmit;
    Button chooseImage, saveImage;
    ImageView imageView;
    static Retrofit retrofitImage = null;
    String filePath = "";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usernameET = findViewById(R.id.sign_up_username);
        passwordET = findViewById(R.id.sign_up_password);
        mailET = findViewById(R.id.sign_up_email);
        descriptionET = findViewById(R.id.sign_up_description);
        chooseImage = findViewById(R.id.sign_up_pick_image);
        saveImage = findViewById(R.id.sign_up_save_image);
        imageView = findViewById(R.id.sign_up_image);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });

        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });

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
        if(!filePath.equals(""))
            user.setAvatar(filePath);
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

    private void chooseFile() {
        String [] permissions = new String[]{
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        if(ActivityCompat.checkSelfPermission(this,permissions[0]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,permissions[1]) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,permissions,1);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            uri = data.getData();
            File dir = getExternalFilesDir(null);
            if(dir != null){
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                    saveImage.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void uploadImage(){
        File file = new File(FileUtil.getPath(uri, this));
        System.out.println(file.toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

        MyServiceInterceptor interceptor = new MyServiceInterceptor(getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofitImage = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageApiService imageApiService = retrofitImage.create(ImageApiService.class);
        Call<Map<String,String>> call = imageApiService.saveImage(part);
        call.enqueue(new Callback<Map<String,String>>() {
            @Override
            public void onResponse(Call<Map<String,String>> call, Response<Map<String,String>> response) {
                filePath = response.body().get("path");
                System.out.println(response.body().get("path"));
            }

            @Override
            public void onFailure(Call<Map<String,String>> call, Throwable t) {
                System.out.println("T ERROR" + t.getMessage());
                Toast.makeText(SignUpActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}