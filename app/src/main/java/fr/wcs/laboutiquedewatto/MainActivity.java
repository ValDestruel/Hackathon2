package fr.wcs.laboutiquedewatto;

import android.content.Intent;
import android.content.res.Resources;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int mStratAngle = 180;
    int mEndAngle = 270;
    FloatingActionMenu mActionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        final ArrayList<String> al = new ArrayList<>();
        final SwipeAdapter arrayAdapter = new SwipeAdapter(this, al);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        ref.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                  String image = snapshot.child("image").getValue(String.class);
                  al.add(image);
                  arrayAdapter.notifyDataSetChanged();
              }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });

        /*fonction swipe*/
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }
            /*action déclenché au passage a gauche de la carte*/
            @Override
            public void onLeftCardExit(Object dataObject) {

            }
            /*action déclench au passage a droite de la carte*/
            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
            /*permet de rajouter du contenu d'apres ce que je comprends pour le moment*/
            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here

            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();

            }
        });


        /** Partie menu Circle**/
        //Image bouton Menu
        ImageView iconMenu = new ImageView(this);
        iconMenu.setImageDrawable(ContextCompat.getDrawable(getApplication(), R.drawable.button_action_dark_touch));

        //creation bouton Menu
        final FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(iconMenu)
                .build();

        SubActionButton.Builder listeBuilder = new SubActionButton.Builder(this);

        final ImageView ivFiltre = new ImageView(this);
        ivFiltre.setImageDrawable(ContextCompat.getDrawable(getApplication(), R.drawable.icon_password));
        final SubActionButton filtre = listeBuilder.setContentView(ivFiltre).build();

        final ImageView ivListe = new ImageView(this);
        ivListe.setImageDrawable(ContextCompat.getDrawable(getApplication(), R.drawable.ligne));
        final SubActionButton liste = listeBuilder.setContentView(ivListe).build();

        final ImageView ivArgent = new ImageView(this);
        ivArgent.setImageDrawable(ContextCompat.getDrawable(getApplication(), R.drawable.button_sub_action));
        final SubActionButton argent = listeBuilder.setContentView(ivArgent).build();

        Resources ressource = getResources();
        int valuePx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, ressource.getDisplayMetrics());

        DrawerLayout.LayoutParams layoutParam = new DrawerLayout.LayoutParams(valuePx, valuePx);
        filtre.setLayoutParams(layoutParam);
        liste.setLayoutParams(layoutParam);
        argent.setLayoutParams(layoutParam);

        mActionMenu = new FloatingActionMenu.Builder(MainActivity.this)
                .addSubActionView(filtre)
                .addSubActionView(liste)
                .addSubActionView(argent)
                .setStartAngle(mStratAngle)
                .setEndAngle(mEndAngle)
                .attachTo(actionButton)
                .build();

        ivFiltre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentProfile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intentProfile);
            }
        });

        ivListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentListe = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intentListe);
            }
        });

        ivArgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentArgent = new Intent(MainActivity.this, ArgentActivity.class);
                startActivity(intentArgent);
            }
        });
    }
}