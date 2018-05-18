package fr.wcs.laboutiquedewatto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent;
        intent = getIntent();
        ProfilModel profil = intent.getParcelableExtra("Profil");

        ImageView image = findViewById(R.id.image);
        TextView name = findViewById(R.id.name);
        TextView desc = findViewById(R.id.desc);
        TextView eye = findViewById(R.id.eye);
        TextView hair = findViewById(R.id.hair);
        TextView skin = findViewById(R.id.skin);
        ImageView money = findViewById(R.id.price_pic);
        TextView price = findViewById(R.id.price);

        image.setImageBitmap(getBitmapFromURL(profil.getImage()));
        name.setText(profil.getName());
        desc.setText(String.format("%s %s de %s", profil.getGender(), profil.getSpecies(), profil.getHomeworld()));
        eye.setText(profil.getEyeColor());
        hair.setText(profil.getHairColor());
        skin.setText(profil.getSkinColor());
        money.setImageResource(R.drawable.dataries);
        price.setText(profil.getPrice());



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
