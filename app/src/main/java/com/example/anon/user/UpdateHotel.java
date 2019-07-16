package com.example.anon.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.anon.user.MainActivity.DATABASE_PATH_HOTEL;

/**
 * Created by Anon on 03-Feb-18.
 */

public class UpdateHotel extends AppCompatActivity {

    public static final String PLACE_NAME= "placename";
    public static final String PLACE_DESCRIPTION= "placedescription";
    public static final String PLACE_ADDRESS= "placeaddress";
    public static final String PLACE_OPEN_TIME= "placeopentime";
    public static final String PLACE_PHONE= "placephone";
    public static final String PLACE_WEBSITE= "placewebsite";
    public static final String PLACE_IMAGE_URI= "placeimageuri";
    public static final String PLACE_ID= "placeid";

    ListView listViewPlaces;
    private DatabaseReference databaseReference;
    List<Place> placeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_place);
        databaseReference= FirebaseDatabase.getInstance().getReference(DATABASE_PATH_HOTEL);

        listViewPlaces= (ListView)findViewById(R.id.listViewPlaces);
        placeList =new ArrayList<>();


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                placeList.clear();

                for(DataSnapshot hotelSnapshot : dataSnapshot.getChildren()){

                    Place place= hotelSnapshot.getValue(Place.class);
                    placeList.add(place);

                }

                PlaceList adapter = new PlaceList(UpdateHotel.this,placeList);
                listViewPlaces.setAdapter(adapter);


                listViewPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Place place=placeList.get(i);
                        Intent intent= new Intent(getApplicationContext(), UpdateHotelActivity.class);

                        intent.putExtra(PLACE_ID, place.getId ());
                        intent.putExtra(PLACE_NAME,place.getName());
                        intent.putExtra(PLACE_DESCRIPTION,place.getDescription());
                        intent.putExtra(PLACE_ADDRESS,place.getAddress());
                        intent.putExtra(PLACE_OPEN_TIME,place.getOpenTime());
                        intent.putExtra(PLACE_PHONE,place.getPhone());
                        intent.putExtra(PLACE_WEBSITE,place.getWebsite());
                        intent.putExtra(PLACE_IMAGE_URI,place.getImageUri());
                        startActivity(intent);




                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
