package com.example.anon.user;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

/**
 * Created by Anon on 03-Feb-18.
 */

public class UpdateCafeActivity extends AppCompatActivity {

    ImageView imageView;
    EditText name,description,address,openTime,phone,website;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    public static final String STORAGE_PATH= "images/";
    public static final String DATABASE_PATH_CAFE="cafes";
    private Uri imageUri;
    public String updateId;
    public String image_prev;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_place_info);

        Intent intent=getIntent();

        String id_prev=intent.getStringExtra(UpdateHotel.PLACE_ID);
        String name_prev=intent.getStringExtra(UpdateHotel.PLACE_NAME);
        String description_prev=intent.getStringExtra(UpdateHotel.PLACE_DESCRIPTION);
        String address_prev=intent.getStringExtra(UpdateHotel.PLACE_ADDRESS);
        String openTime_prev=intent.getStringExtra(UpdateHotel.PLACE_OPEN_TIME);
        String phone_prev=intent.getStringExtra(UpdateHotel.PLACE_PHONE);
        String website_prev=intent.getStringExtra(UpdateHotel.PLACE_WEBSITE);
        String imageUri_prev=intent.getStringExtra(UpdateHotel.PLACE_IMAGE_URI);


        imageView=(ImageView)findViewById(R.id.insertImages);
        name=(EditText)findViewById(R.id.insertName);
        description=(EditText)findViewById(R.id.insertDescription);
        address=(EditText)findViewById(R.id.insertAddress);
        openTime=(EditText)findViewById(R.id.insertOpenTime);
        phone=(EditText)findViewById(R.id.insertPhone);
        website=(EditText)findViewById(R.id.insertWebsite);

        name.setText(name_prev);
        description.setText(description_prev);
        address.setText(address_prev);
        openTime.setText(openTime_prev);
        phone.setText(phone_prev);
        website.setText(website_prev);

        image_prev=imageUri_prev;

        Glide.with(this).load(imageUri_prev).into(imageView);

        //storageReference= FirebaseStorage.getInstance().getReference();
        //databaseReference= FirebaseDatabase.getInstance().getReference(DATABASE_PATH_HOTEL);

        updateId=id_prev;

        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference(DATABASE_PATH_CAFE);

    }


    public void browseImages(View view){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),0);


    }

    protected void onActivityResult(int requetCode, int resultCode, Intent data){
        super.onActivityResult(requetCode, resultCode, data);
        if(requetCode ==0 && resultCode== RESULT_OK){

            imageUri=data.getData();
            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getActualImage(Uri uri){

        ContentResolver contentResolver= getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void uploadData(View view){

        if(imageUri!=null){

            //insert data
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference reference=storageReference.child(STORAGE_PATH+ System.currentTimeMillis()+ "."+ getActualImage(imageUri));
            reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    String NAME= name.getText().toString();
                    String DESCRIPTION= description.getText().toString();
                    String ADDRESS= address.getText().toString();
                    String OPENTIME= openTime.getText().toString();
                    String PHONE= phone.getText().toString();
                    String WEBSITE= website.getText().toString();
                    //String ID= databaseReference.push().getKey();
                    //Person person =new Person(NAME,EMAIL,taskSnapshot.getDownloadUrl().toString());
                    Place place =new Place(updateId,NAME,DESCRIPTION,ADDRESS,OPENTIME,PHONE,WEBSITE,taskSnapshot.getDownloadUrl().toString());



                    databaseReference.child(updateId).setValue(place);

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"DATA UPLOADED",Toast.LENGTH_LONG).show();


                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    })

                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double totalProgess= (100*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded %" + (int)totalProgess);


                        }
                    });


        } else{

            //Show message

            String NAME= name.getText().toString();
            String DESCRIPTION= description.getText().toString();
            String ADDRESS= address.getText().toString();
            String OPENTIME= openTime.getText().toString();
            String PHONE= phone.getText().toString();
            String WEBSITE= website.getText().toString();
            //String ID= databaseReference.push().getKey();
            //Person person =new Person(NAME,EMAIL,taskSnapshot.getDownloadUrl().toString());
            Place place =new Place(updateId,NAME,DESCRIPTION,ADDRESS,OPENTIME,PHONE,WEBSITE,image_prev);



            databaseReference.child(updateId).setValue(place);

            Toast.makeText(getApplicationContext(),"DATA UPLOADED",Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), "Please select data first", Toast.LENGTH_LONG).show();

        }


    }
}
