package com.example.redditcloneapp.ui.report;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.databinding.FragmentReportBinding;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.access.SignInActivity;

public class ReportFragment extends ListFragment {
    public FragmentReportBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ReportViewModel reportViewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        binding = FragmentReportBinding.inflate(inflater, container, false);

        return inflater.inflate(R.layout.post_map_layout, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity().getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.Role, "") != null && !getActivity().getSharedPreferences(SignInActivity.mypreference, Context.MODE_PRIVATE).getString(SignInActivity.Role, "").equals("ROLE_USER")){
            ReportAdapter adapter = new ReportAdapter(getActivity());
            setListAdapter(adapter);
        }else{

            Toast.makeText(getContext(), "No access", Toast.LENGTH_LONG).show();

        }

    }
}