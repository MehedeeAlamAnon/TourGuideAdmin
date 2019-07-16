package com.example.anon.user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anon on 02-Feb-18.
 */

public class MuseumActivity extends AppCompatActivity {

    ListView listView;
    List<Place> list;
    ProgressDialog progressDialog;
    //MyAdapter myAdapter;
    //HotelAdapter hotelAdapter;
    PlaceAdapter placeAdapter;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loc_list);

        listView=(ListView)findViewById(R.id.list);

        list= new ArrayList<>();
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Fetching Please wait");
        progressDialog.show();

        databaseReference= FirebaseDatabase.getInstance().getReference(MainActivity.DATABASE_PATH_MUSEUM);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                list.clear();

                for(DataSnapshot snap : dataSnapshot.getChildren()){

                    Place place=snap.getValue(Place.class);
                    list.add(place);

                }

                placeAdapter= new PlaceAdapter(MuseumActivity.this, R.layout.list_item,list);
                listView.setAdapter(placeAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
