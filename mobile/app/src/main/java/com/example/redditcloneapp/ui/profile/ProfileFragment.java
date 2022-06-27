package com.example.redditcloneapp.ui.profile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.databinding.FragmentProfileBinding;
import com.example.redditcloneapp.model.Post;
import com.example.redditcloneapp.model.User;
import com.example.redditcloneapp.service.ImageApiService;
import com.example.redditcloneapp.service.PostApiService;
import com.example.redditcloneapp.service.UserApiService;
import com.example.redditcloneapp.service.client.MyServiceInterceptor;
import com.example.redditcloneapp.tools.FileUtil;
import com.example.redditcloneapp.ui.access.SignInActivity;
import com.example.redditcloneapp.ui.access.SignUpActivity;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

public class ProfileFragment extends Fragment {

    private User user;
    private FragmentProfileBinding binding;
    private Activity activity;

    static final String TAG = ProfileActivity.class.getSimpleName();
    static Retrofit retrofit = null;
    static Retrofit retrofitKarma = null;
    static Retrofit retrofitImage = null;
    private String filePath = "";
    Uri uri;

    TextView usernameShow, usernameEdit,displayNameShow,mailShow, passwordShow, descShow, dateShow, dateEdit, karmaShow;
    EditText  descEdit, mailEdit, displayNameEdit;
    Button uploadImage, saveImage;
    ImageView imageView;
    View editLayout, saveLayout, editableLayout, informationLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        user = ((MainActivity)getActivity()).getUser();
        activity = getActivity();
        ProfileViewModel galleryViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = binding.getRoot();
        usernameShow = view.findViewById(R.id.profile_username);
        displayNameShow = view.findViewById(R.id.profile_display_name);
        passwordShow = view.findViewById(R.id.profile_password);
        mailShow = view.findViewById(R.id.profile_email);
        dateShow = view.findViewById(R.id.profile_reg_date);
        descShow = view.findViewById(R.id.profile_description);
        karmaShow = view.findViewById(R.id.profile_karma);
        uploadImage = view.findViewById(R.id.profile_upload_image);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });
        saveImage = view.findViewById(R.id.profile_save_image);
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
        imageView = view.findViewById(R.id.profile_image);

        usernameEdit = view.findViewById(R.id.profile_username_edit);
        mailEdit = view.findViewById(R.id.profile_email_edit);
        displayNameEdit = view.findViewById(R.id.profile_display_name_edit);
        dateEdit = view.findViewById(R.id.profile_reg_date_edit);
        descEdit = view.findViewById(R.id.profile_description_edit);

        editLayout = view.findViewById(R.id.profile_show_btns);
        saveLayout = view.findViewById(R.id.profile_edit_btns);

        informationLayout = view.findViewById(R.id.profile_show_informations);
        editableLayout = view.findViewById(R.id.profile_edit_informations);


        if(user != null){
//            usernameShow.setText(user.getUsername());
//            passwordShow.setText(user.getPassword());
//            displayNameShow.setText(user.getDisplayName());
//            mailShow.setText(user.getEmail());
////            dateShow.setText(user.getRegistrationDate().format(DateTimeFormatter
////                    .ofLocalizedDate(FormatStyle.LONG)));
//            dateShow.setText(user.getRegistrationDate());
//            descShow.setText(user.getDescription());
//            editLayout.setVisibility(View.VISIBLE);
//
//            usernameEdit.setText(user.getUsername());
//            passwordEdit.setText(user.getPassword());
//            mailEdit.setText(user.getEmail());
////            dateEdit.setText(user.getRegistrationDate().format(DateTimeFormatter
////                    .ofLocalizedDate(FormatStyle.LONG)));
//            dateEdit.setText(user.getRegistrationDate());
//            descEdit.setText(user.getDescription());
        }
        getUser();
        Button editBtn = view.findViewById(R.id.profile_edit_btn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                informationLayout.setVisibility(View.GONE);
                editableLayout.setVisibility(View.VISIBLE);
                System.out.println("INFORMATIOL "+informationLayout);
                System.out.println("EDITABLEL "+editableLayout);
                saveLayout.setVisibility(View.VISIBLE);
                editLayout.setVisibility(View.GONE);
            }
        });

        Button backBtn = view.findViewById(R.id.profile_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                informationLayout.setVisibility(View.VISIBLE);
                editableLayout.setVisibility(View.GONE);
                saveLayout.setVisibility(View.GONE);
                editLayout.setVisibility(View.VISIBLE);
            }
        });

        Button saveBtn = view.findViewById(R.id.profile_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidChange()){
                    saveUser();
                }
            }
        });

        Button commentsBtn = view.findViewById(R.id.profile_my_comments_btn);
        commentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CommentsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        Button postsBtn = view.findViewById(R.id.profile_my_posts_btn);
        postsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        Button editPasswordBtn = view.findViewById(R.id.profile_edit_password_btn);
        editPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_change_password_layout);
                dialog.setTitle("Change password");
                EditText old,newPw,repeatPw;
                Button back,submit;
                old = dialog.findViewById(R.id.password_changer_old);
                newPw = dialog.findViewById(R.id.password_changer_new);
                repeatPw = dialog.findViewById(R.id.password_changer_repeat);
                back = dialog.findViewById(R.id.dialog_password_back);
                submit = dialog.findViewById(R.id.dialog_password_submit);

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(old.getText().toString().equals("") || newPw.getText().toString().equals("") || repeatPw.getText().toString().equals(""))
                            Toast.makeText(getContext(), getResources().getString(R.string.change_pw_error_msg_empty_fields), Toast.LENGTH_LONG).show();
                        else{
                            String password = activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.Password, "");
                            if(!newPw.getText().toString().equals(repeatPw.getText().toString()))
                                Toast.makeText(getContext(), getResources().getString(R.string.change_pw_error_msg_dont_match), Toast.LENGTH_LONG).show();
                            else if(!password.equals(old.getText().toString()))
                                Toast.makeText(getContext(), getResources().getString(R.string.change_pw_error_msg_dont_match_old), Toast.LENGTH_LONG).show();
                            else{
                                user.setPassword(newPw.getText().toString());
                                saveApiUser(user);
                                dialog.dismiss();
                            }
                        }
                    }
                });



                dialog.show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean isValidChange(){
        if(mailEdit.getText().toString().equals("") || !mailEdit.getText().toString().contains("@")) {
            Toast.makeText(activity.getApplicationContext(), R.string.user_mail_error, Toast.LENGTH_LONG).show();
            mailEdit.setBackgroundResource(R.drawable.textview_border);
            mailEdit.setHint("user123@gmail.com");
            return false;
        } else if(descEdit.getText().toString().equals("")) {
            Toast.makeText(activity.getApplicationContext(), R.string.user_description_error, Toast.LENGTH_LONG).show();
            descEdit.setBackgroundResource(R.drawable.textview_border);
            descEdit.setHint("Professional reddit user");
            return false;
        } else {
            return true;
        }
    }

    private void getUser(){

        String username = activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.Username, "");

        retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApiService userApiService = retrofit.create(UserApiService.class);
        Call<User> call = userApiService.getUser(username);
        call.enqueue(new Callback<User>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {

                if(response.isSuccessful()) {
                    usernameShow.setText(user.getUsername());
                    passwordShow.setText(user.getPassword());
                    displayNameShow.setText(user.getDisplayName());
                    mailShow.setText(user.getEmail());
//            dateShow.setText(user.getRegistrationDate().format(DateTimeFormatter
//                    .ofLocalizedDate(FormatStyle.LONG)));
                    dateShow.setText(user.getRegistrationDate());
                    descShow.setText(user.getDescription());
                    editLayout.setVisibility(View.VISIBLE);

                    usernameEdit.setText(user.getUsername());
                    mailEdit.setText(user.getEmail());
                    displayNameEdit.setText(user.getDisplayName());
//            dateEdit.setText(user.getRegistrationDate().format(DateTimeFormatter
//                    .ofLocalizedDate(FormatStyle.LONG)));
                    dateEdit.setText(user.getRegistrationDate());
                    descEdit.setText(user.getDescription());

                    getUsersKarma(username);
                    if(user.getAvatar() != null)
                        getProfileImage();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void getUsersKarma(String username){
//        String username = activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.Username, "");
        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofitKarma = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApiService userApiService = retrofitKarma.create(UserApiService.class);

        Call<Integer> call = userApiService.getUsersKarma(username);
        call.enqueue(new Callback<Integer>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<Integer> call, retrofit2.Response<Integer> response) {

                if(response.isSuccessful()) {
                    karmaShow.setText(response.body().toString());
                }else{
                    karmaShow.setText("0");
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void saveUser(){
        user.setDescription(descEdit.getText().toString());
        user.setEmail(mailEdit.getText().toString());
        if(!displayNameEdit.getText().toString().equals(""))
            user.setDisplayName(displayNameEdit.getText().toString());
        else
            user.setDisplayName(null);

        saveApiUser(user);
    }

    private void saveApiUser(User user){
        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if(!filePath.equals(""))
            user.setAvatar(filePath);
        UserApiService userApiService = retrofit.create(UserApiService.class);
        Call<User> call = userApiService.updateUser(user);
        call.enqueue(new Callback<User>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                informationLayout.setVisibility(View.VISIBLE);
                editableLayout.setVisibility(View.GONE);
                saveLayout.setVisibility(View.GONE);
                editLayout.setVisibility(View.VISIBLE);
                getUser();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void chooseFile() {
        String [] permissions = new String[]{
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        if(ActivityCompat.checkSelfPermission(activity,permissions[0]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(activity,permissions[1]) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(activity,permissions,1);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            uri = data.getData();
            File dir = activity.getExternalFilesDir(null);
            if(dir != null){
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                    saveImage.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void uploadImage(){
        File file = new File(FileUtil.getPath(uri, activity));
        System.out.println(file.toString());
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));
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
                Toast.makeText(activity, "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getProfileImage() {

        MyServiceInterceptor interceptor = new MyServiceInterceptor(activity.getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.TOKEN, ""));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofitImage = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostApiService postApiServiceImage = retrofitImage.create(PostApiService.class);

        Call<ResponseBody> call = postApiServiceImage.getImage(user.getAvatar());
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

                    Glide.with(activity.getApplicationContext())
                            .load(imageByteArray)
                            .into(imageView);
//                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
//                    image.setImageBitmap(bmp);
                }else{
                    Toast.makeText(activity.getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    System.out.println("Image not succ" + response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), "System error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("GRESKA ZA IMAGE" + t.getMessage());
            }
        });
    }

}