package com.example.anon.user;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anon on 03-Feb-18.
 */

public class PlaceList extends ArrayAdapter<Place> {

    private Activity context;
    private List<Place> placeList;

    public PlaceList(Activity context, List<Place> placeList){

        super(context,R.layout.list_layout, placeList);
        this.context=context;
        this.placeList=placeList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewName= (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDescription= (TextView) listViewItem.findViewById(R.id.textViewDescription);
        TextView textViewAddress= (TextView) listViewItem.findViewById(R.id.textViewAddress);
        TextView textViewOpenTime= (TextView) listViewItem.findViewById(R.id.textViewOpenTime);
        TextView textViewPhone= (TextView)listViewItem.findViewById(R.id.textViewPhone);
        TextView textViewWebsite= (TextView)listViewItem.findViewById(R.id.textViewWebsite);

        Place place=placeList.get(position);
        textViewName.setText(place.getName());
        textViewDescription.setText(place.getDescription());
        textViewAddress.setText(place.getAddress());
        textViewOpenTime.setText(place.getOpenTime());
        textViewPhone.setText(place.getPhone());
        textViewWebsite.setText(place.getWebsite());


        return listViewItem;

    }
}
