package com.example.redditcloneapp.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.redditcloneapp.R;
import com.example.redditcloneapp.databinding.FragmentCommunityBinding;
import com.example.redditcloneapp.tools.FragmentTransition;
import com.example.redditcloneapp.ui.community.mycommunities.MyCommunitiesFragment;

public class CommunityFragment extends Fragment {

    private FragmentCommunityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CommunityViewModel slideshowViewModel =
                new ViewModelProvider(this).get(CommunityViewModel.class);

        binding = FragmentCommunityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        FragmentTransition.to(MyCommunitiesFragment.newInstance(), getActivity(),false, R.id.my_communities_list);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}