package com.example.musicadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;

    Uri musicPath;
    Uri coverPath;

    String musicName;
    String artist;
    String musicURL;
    String coverURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

    }


    public Uri getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(Uri musicPath) {
        this.musicPath = musicPath;
    }

    public Uri getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(Uri coverPath) {
        this.coverPath = coverPath;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public void upload() {
        MusicInfo musicInfo = new MusicInfo();

        musicInfo.artist = artist;
        musicInfo.musicName = musicName;

        musicInfo.coverURL = "https://firebasestorage.googleapis.com/v0/b/firstproj-d32ba.appspot.com/o/covers%2Fcover_" + musicName + "_" + artist + "?alt=media&token=bff6786e-8b00-47c2-8b53-ee0f3e20acea";
        musicInfo.musicURL = "https://firebasestorage.googleapis.com/v0/b/firstproj-d32ba.appspot.com/o/mp3%2Fmp3_" + musicName + "_" + artist + "?alt=media&token=ff61bf38-2c8c-4ffc-bff3-3e39fca0497b";


        firebaseDatabase.getReference("tracksInfo").child(musicName + "_" + artist).setValue(musicInfo);

        StorageReference refImage = storage.getReference().child("covers").child("cover" + "_" + musicName + "_" + artist);
        refImage.putFile(coverPath);

        StorageReference refMusic = storage.getReference().child("mp3").child("mp3" + "_" + musicName + "_" + artist);
        refMusic.putFile(musicPath);



        musicPath = null;
        coverPath = null;
    }

    public void makeToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 71);
    }

    public void chooseAudio() {
        Intent intent = new Intent();
        intent.setType("audio/mp3");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Music File"), 72);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 71 && resultCode == RESULT_OK && data != null && data.getData() != null ) {
            coverPath = data.getData();
        }
        if(requestCode == 72 && resultCode == RESULT_OK && data != null && data.getData() != null ) {
            musicPath = data.getData();
        }
    }
}