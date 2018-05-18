package fr.wcs.laboutiquedewatto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<ProfilModel> {

    public GridAdapter(Context context, ArrayList<ProfilModel> profiles) {
        super(context, 0, profiles);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ProfilModel profile = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_profile, parent, false);

        }

        ImageView profileImage = convertView.findViewById(R.id.item_picture);
        TextView profileName = convertView.findViewById(R.id.item_name);


        Glide.with(getContext())
                .load(profile.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(profileImage);
        profileName.setText(profile.getName());


        return convertView;

    }
}
