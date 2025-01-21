package com.ios7.wallify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;  // You can use Picasso or Glide for image loading

import java.util.List;

public class DeveloperAdapter extends BaseAdapter {
    private Context context;
    private List<Developer> developerList;

    // Constructor to initialize the adapter with context and list
    public DeveloperAdapter(Context context, List<Developer> developerList) {
        this.context = context;
        this.developerList = developerList;
    }

    @Override
    public int getCount() {
        return developerList.size();
    }

    @Override
    public Object getItem(int position) {
        return developerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the custom view
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.devs, null);  // Ensure devs.xml is the correct layout
        }

        // Get the current developer object
        Developer developer = developerList.get(position);

        // Get the TextView and ImageView from the inflated view
        TextView nameTextView = convertView.findViewById(R.id.textview4);
        ImageView imageView = convertView.findViewById(R.id.circleimageview1);

        // Set the name and load the image
        nameTextView.setText(developer.getName());
        Picasso.get().load(developer.getImageUrl()).into(imageView);  // Using Picasso for image loading

        return convertView;
    }
}
