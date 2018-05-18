package fr.wcs.laboutiquedewatto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ResultAdapter extends ArrayAdapter<ProfilModel> {

    ResultAdapter(Context context, ArrayList<ProfilModel> profiles) {
        super(context, 0, profiles);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ProfilModel profile = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_result, parent, false);
        }

        TextView profilName = convertView.findViewById(R.id.name);
        TextView profilDesc = convertView.findViewById(R.id.desc);
        TextView profilEye = convertView.findViewById(R.id.eye);
        TextView profilHair = convertView.findViewById(R.id.hair);
        TextView profilSkin = convertView.findViewById(R.id.skin);
        ImageView profilImage = convertView.findViewById(R.id.image);


        profilName.setText(profile.getName());
        profilDesc.setText(String.format("%s %s de %s", profile.getGender(), profile.getSpecies(), profile.getHomeworld()));
        profilEye.setText(profile.getEyeColor());
        profilHair.setText(profile.getHairColor());
        profilSkin.setText(profile.getSkinColor());
        Glide.with(getContext()).load(profile.getImage()).into(profilImage);


        return convertView;
    }
}
