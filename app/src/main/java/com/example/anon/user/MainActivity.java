package com.example.anon.user;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String STORAGE_PATH= "images/";
    public static final String DATABASE_PATH_CAFE="cafes";
    public static final String DATABASE_PATH_HOTEL="hotels";
    public static final String DATABASE_PATH_FAMOUS_ATTRACTION="famous_attractions";
    public static final String DATABASE_PATH_MUSEUM="museums";
    public static final String DATABASE_PATH_HOSPITAL="hospitals";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCustomOnClickListener(R.id.newPlaceTextView, AddPlaceActivity.class);
        setCustomOnClickListener(R.id.newCafeTextView, AddCafeActivity.class);
        setCustomOnClickListener(R.id.newHotelTextView, AddHotelActivity.class);
        setCustomOnClickListener(R.id.newFamousAttractionTextView, AddFamousAttractionActivity.class);
        setCustomOnClickListener(R.id.newMuseumTextView, AddMuseumActivity.class);
        setCustomOnClickListener(R.id.newHospitalTextView, AddHospitalActivity.class);


        setCustomOnClickListener(R.id.updateCafeTextView, UpdateCafe.class);
        setCustomOnClickListener(R.id.updateHotelTextView, UpdateHotel.class);
        setCustomOnClickListener(R.id.updateFamousAttractionTextView, UpdateFamousAttraction.class);
        setCustomOnClickListener(R.id.updateMuseumTextView, UpdateMuseum.class);
        setCustomOnClickListener(R.id.updateHospitalTextView, UpdateHospital.class);
    }

    public void setCustomOnClickListener(int resourceID, final Class className) {
        View view = findViewById(resourceID);
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), className);
                        startActivity(intent);
                    }
                }
        );
    }
}
