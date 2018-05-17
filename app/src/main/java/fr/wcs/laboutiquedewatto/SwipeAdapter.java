package fr.wcs.laboutiquedewatto;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SwipeAdapter extends ArrayAdapter<String> {

    public SwipeAdapter(Context context, ArrayList<String> images) {
        super(context, 0, images);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        String image = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);

        }

        ImageView profileImage = convertView.findViewById(R.id.iv_swipe);
        Bitmap bitmap = getBitmapFromURL(image);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(null, bitmap);
        drawable.setCircular(true);

        profileImage.setImageDrawable(drawable);


        return convertView;

    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }




}
