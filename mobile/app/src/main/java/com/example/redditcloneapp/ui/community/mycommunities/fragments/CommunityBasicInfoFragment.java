package com.example.redditcloneapp.ui.community.mycommunities.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.databinding.FragmentCommunityBasicInfoBinding;
import com.example.redditcloneapp.databinding.FragmentProfileBinding;
import com.example.redditcloneapp.model.Community;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.CommunityActivity;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunityActivity;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class CommunityBasicInfoFragment extends Fragment {

    public static CommunityBasicInfoFragment newInstance(){return new CommunityBasicInfoFragment();}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_community_basic_info, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Community community = ((MyCommunityActivity) getActivity()).getCommunity();

        TextView comName = getView().findViewById(R.id.my_community_name);
        comName.setText(community.getName());
        EditText comNameEdit = getView().findViewById(R.id.my_community_name_edit);
        comNameEdit.setText(community.getName());
        TextView comDate = getView().findViewById(R.id.my_community_date);
        comDate.setText(community.getCreationDate());
        TextView comSus = getView().findViewById(R.id.my_community_is_suspended);
        comSus.setText(community.getSuspended().toString());
        TextView comReas = getView().findViewById(R.id.my_community_sus_reason);
        comReas.setText(community.getSuspendedReason());
        TextView comDes = getView().findViewById(R.id.my_community_desc);
        comDes.setText(community.getDescription());
        EditText comDesEdit = getView().findViewById(R.id.my_community_desc_edit);
        comDesEdit.setText(community.getDescription());
        ScrollView scrollView = getView().findViewById(R.id.my_community_desc_scroll);

        View editView = getView().findViewById(R.id.my_community_basic_btn_edit_layout);
        View saveView = getView().findViewById(R.id.my_community_basic_btn_save_layout);

        Button editBtn = getView().findViewById(R.id.my_community_basic_btn_edit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editView.setVisibility(View.GONE);
                saveView.setVisibility(View.VISIBLE);
                comName.setVisibility(View.GONE);
                comNameEdit.setVisibility(View.VISIBLE);
                comDes.setVisibility(View.GONE);
                scrollView.setVisibility(View.GONE);
                comDesEdit.setVisibility(View.VISIBLE);
            }
        });
        Button btnSaveBasic = getView().findViewById(R.id.my_community_basic_btn_save);
        btnSaveBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comNameEdit.getText().toString().equals("")) {
                    Toast.makeText(getContext(), R.string.community_name_error, Toast.LENGTH_LONG).show();
                } else if (comDesEdit.getText().toString().equals("")) {
                    Toast.makeText(getContext(), R.string.community_desc_error, Toast.LENGTH_LONG).show();
                } else {
                    community.setName(comNameEdit.getText().toString());
                    community.setDescription(comDesEdit.getText().toString());

                    FragmentTransition.to(CommunityBasicInfoFragment.newInstance(), getActivity(),false,R.id.my_community_fragment);
                }
            }
        });

        Button cancelBtn = (Button) getView().findViewById(R.id.my_community_basic_btn_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editView.setVisibility(View.VISIBLE);
                saveView.setVisibility(View.GONE);
                comName.setVisibility(View.VISIBLE);
                comNameEdit.setVisibility(View.GONE);
                comDes.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.VISIBLE);
                comDesEdit.setVisibility(View.GONE);
            }
        });
    }
}