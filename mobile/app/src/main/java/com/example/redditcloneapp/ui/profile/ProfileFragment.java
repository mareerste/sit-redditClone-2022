package com.example.redditcloneapp.ui.profile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.redditcloneapp.MainActivity;
import com.example.redditcloneapp.R;
import com.example.redditcloneapp.databinding.FragmentProfileBinding;
import com.example.redditcloneapp.model.User;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ProfileFragment extends Fragment {

    private User user;
    private FragmentProfileBinding binding;
    private Activity activity;

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
        TextView usernameShow = view.findViewById(R.id.profile_username);
        TextView passwordShow = view.findViewById(R.id.profile_password);
        TextView mailShow = view.findViewById(R.id.profile_email);
        TextView dateShow = view.findViewById(R.id.profile_reg_date);
        TextView descShow = view.findViewById(R.id.profile_description);

        TextView usernameEdit = view.findViewById(R.id.profile_username_edit);
        TextView passwordEdit = view.findViewById(R.id.profile_password_edit);
        EditText mailEdit = view.findViewById(R.id.profile_email_edit);
        TextView dateEdit = view.findViewById(R.id.profile_reg_date_edit);
        EditText descEdit = view.findViewById(R.id.profile_description_edit);

        View editLayout = view.findViewById(R.id.profile_show_btns);
        View saveLayout = view.findViewById(R.id.profile_edit_btns);

        View informationLayout = view.findViewById(R.id.profile_show_informations);
        View editableLayout = view.findViewById(R.id.profile_edit_informations);


        if(user != null){
            usernameShow.setText(user.getUsername());
            passwordShow.setText(user.getPassword());
            mailShow.setText(user.getEmail());
            dateShow.setText(user.getRegistrationDate().format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.LONG)));
            descShow.setText(user.getDescription());
            editLayout.setVisibility(View.VISIBLE);

            usernameEdit.setText(user.getUsername());
            passwordEdit.setText(user.getPassword());
            mailEdit.setText(user.getEmail());
            dateEdit.setText(user.getRegistrationDate().format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.LONG)));
            descEdit.setText(user.getDescription());


        }

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
                dialog.show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}