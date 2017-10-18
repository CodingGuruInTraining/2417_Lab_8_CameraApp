package com.example.hl4350hb.androidcameraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.provider.MediaStore;
import android.graphics.Bitmap;


public class MainActivity extends AppCompatActivity {

    Button mTakePictureButton;
    ImageView mCameraPicture;

    private static int TAKE_PICTURE_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCameraPicture = (ImageView) findViewById(R.id.camera_picture);
        mTakePictureButton = (Button) findViewById(R.id.take_picture_button);

        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeThumbnailPicture();
            }
        });
    }

    private void takeThumbnailPicture() {
        // Implicit Intent to open an app which can take a picture, often the built-in camera app
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Checks if there is a camera on deice
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pictureIntent, TAKE_PICTURE_REQUEST_CODE);
        } else {
            Toast.makeText(this, "Your device does not have a camera app", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PICTURE_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            mCameraPicture.setImageBitmap(thumbnail);
        }
    }
}
