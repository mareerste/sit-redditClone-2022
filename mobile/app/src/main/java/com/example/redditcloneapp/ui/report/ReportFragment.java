package com.example.redditcloneapp.ui.report;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.databinding.FragmentReportBinding;

public class ReportFragment extends Fragment {
    public FragmentReportBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ReportViewModel reportViewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        binding = FragmentReportBinding.inflate(inflater, container, false);

        return inflater.inflate(R.layout.fragment_report, container, false);
    }
}