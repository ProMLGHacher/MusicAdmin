package com.example.musicadmin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.musicadmin.MainActivity;
import com.example.musicadmin.R;


public class UploadCoverFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_upload_cover, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();

        Button chooseImage = root.findViewById(R.id.chooseImage);
        Button nextFragment = root.findViewById(R.id.nextFragment);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.chooseImage();
            }
        });

        nextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.getCoverPath() == null) {
                    mainActivity.makeToast("Ты не выбрал трек");
                } else {
                    mainActivity.upload();
                    mainActivity.makeToast("Твой трек загружен!");
                    NavHostFragment.findNavController(UploadCoverFragment.this).navigate(R.id.action_uploadCoverFragment_to_musicNameAndArtistName);
                }
            }
        });

        return root;
    }
}