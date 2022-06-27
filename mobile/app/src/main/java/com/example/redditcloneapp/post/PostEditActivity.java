package com.example.redditcloneapp.post;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.model.Flair;
import com.example.redditcloneapp.model.Mokap;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.enums.ReportReason;
import com.example.redditcloneapp.service.CommunityApiService;
import com.example.redditcloneapp.service.ImageApiService;
import com.example.redditcloneapp.service.PostApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FileUtil;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.community.CommunityPostsFragment;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostEditActivity extends AppCompatActivity {

    static Retrofit retrofitPost = null;
    static Retrofit retrofitImage = null;
    private Post post;
    ImageView imageView;
    Button editImage, saveImage;
    Uri uri;
    String filePath = "";
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
        saveImage = findViewById(R.id.save_change);
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
        editImage = findViewById(R.id.my_post_picture_edit);
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });


        imageView = findViewById(R.id.my_post_picture);
        if(post.getImagePath() != null)
            getPostImage(post, imageView);

        Spinner mySpinner = (Spinner) findViewById(R.id.my_post_flair_edit);
//        mySpinner.setAdapter(new ArrayAdapter<ReportReason>(this, android.R.layout.simple_spinner_item, post.getCommunity().getFlairs()));
        ArrayAdapter<Flair> adapter = new ArrayAdapter<Flair>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, Mokap.getCommunities().get(0).getFlairs());//TODO fake comm
        mySpinner.setAdapter(adapter);

        Button saveBtn = findViewById(R.id.my_post_save_btn);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                post.setFlair((Flair) mySpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
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

                    Toast.makeText(getApplicationContext(),post.toString(),Toast.LENGTH_LONG).show();
                    updatePost();
                }


            }
        });

    }

    private void updatePost() {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


            retrofitPost = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(MainActivity.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        PostApiService postApiService = retrofitPost.create(PostApiService.class);

        if(!filePath.equals(""))
            post.setImagePath(filePath);
        System.out.println(post);
        Call<Post> call = postApiService.updatePost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(PostEditActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PostEditActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPostImage(Post post, ImageView image) {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofitImage = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostApiService postApiServiceImage = retrofitImage.create(PostApiService.class);

        Call<ResponseBody> call = postApiServiceImage.getImage(post.getImagePath());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    byte[] imageByteArray = new byte[0];
                    try {
                        imageByteArray = response.body().bytes();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Glide.with(getApplicationContext())
                            .load(imageByteArray)
                            .into(image);
//                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
//                    image.setImageBitmap(bmp);
                }else{
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    System.out.println("Image not succ" + response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("GRESKA ZA IMAGE" + t.getMessage());
            }
        });
    }

    private void chooseFile(){
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
                    imageView.setVisibility(View.VISIBLE);
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
                Toast.makeText(PostEditActivity.this, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}