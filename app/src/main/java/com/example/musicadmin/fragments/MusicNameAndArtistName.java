package com.example.musicadmin.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.musicadmin.MainActivity;
import com.example.musicadmin.R;

public class MusicNameAndArtistName extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_music_name_and_artist_name, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();

        EditText trackNameEditText = root.findViewById(R.id.trackNameEditText);
        EditText artistEditText = root.findViewById(R.id.artistEditText);
        Button button = root.findViewById(R.id.nextButton);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                if (trackNameEditText.getText().toString().trim().length() > 0 | artistEditText.getText().toString().trim().length() > 0) {
                    mainActivity.setArtist(artistEditText.getText().toString());
                    mainActivity.setMusicName(trackNameEditText.getText().toString());

                    NavHostFragment.findNavController(MusicNameAndArtistName.this).navigate(R.id.action_musicNameAndArtistName_to_uploadTrackFragment);
                } else {
                    mainActivity.makeToast("Некоторые поля не были зполнены");
                }
            }
        });

        return root;
    }
}