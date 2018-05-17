package fr.wcs.laboutiquedewatto;

import android.content.Intent;
import android.content.res.Resources;
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

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    int mStratAngle = 180;
    int mEndAngle = 270;
    FloatingActionMenu mActionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*nom des cartes*/
        al = new ArrayList<>();
        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");
        al.add("html");
        al.add("c++");
        al.add("css");
        al.add("javascript");

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al);
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
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
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
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
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