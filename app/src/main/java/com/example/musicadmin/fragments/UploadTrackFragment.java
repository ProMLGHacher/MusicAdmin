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

public class UploadTrackFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_upload_track, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();

        Button uploadButton = root.findViewById(R.id.uploadButton);
        Button nextButton = root.findViewById(R.id.nextButtoned);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.chooseAudio();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.getMusicPath() == null) {
                    mainActivity.makeToast("Ты не выбрал трек");
                } else {
                    NavHostFragment.findNavController(UploadTrackFragment.this).navigate(R.id.action_uploadTrackFragment_to_uploadCoverFragment);
                }
            }
        });

        return root;
    }
}