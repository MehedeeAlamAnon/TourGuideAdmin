package com.example.anon.user;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Anon on 02-Feb-18.
 */

public class PlaceAdapter extends ArrayAdapter<Place> {

    Activity activity;
    int resource;
    List<Place> list;

    public PlaceAdapter(Activity activity, int resource, List<Place> list) {
        super(activity, resource, list);
        this.activity = activity;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater= activity.getLayoutInflater();
        View view=layoutInflater.inflate(resource,null);

        ImageView imageView= (ImageView) view.findViewById(R.id.photoImageView);
        TextView name= (TextView) view.findViewById(R.id.nameTextView);
        TextView description= (TextView)view.findViewById(R.id.descriptionTextView);
        TextView address= (TextView)view.findViewById(R.id.addressTextView);
        TextView openTime= (TextView)view.findViewById(R.id.workHoursTextView);
        TextView phone=(TextView)view.findViewById(R.id.phoneTextView);
        TextView website=(TextView)view.findViewById(R.id.siteTextView);

        name.setText(list.get(position).getName());
        description.setText(list.get(position).getDescription());
        address.setText(list.get(position).getAddress());
        openTime.setText(list.get(position).getOpenTime());
        phone.setText(list.get(position).getPhone());
        website.setText(list.get(position).getWebsite());


        Glide.with(activity).load(list.get(position).getImageUri()).into(imageView);

        return view;
    }
}
