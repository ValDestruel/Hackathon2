package fr.wcs.laboutiquedewatto;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    String planet;
    String eyeColor;
    String hairColor;
    String skinColor;
    String species;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        final ArrayList<String> genderFilter = new ArrayList<>();
        final ArrayList<String> planetFilter = new ArrayList<>();
        final ArrayList<String> eyeColorFilter = new ArrayList<>();
        final ArrayList<String> hairColorFilter = new ArrayList<>();
        final ArrayList<String> skinColorFilter = new ArrayList<>();
        final ArrayList<String> speciesFilter = new ArrayList<>();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String gender = snapshot.child("gender").getValue(String.class);
                    if (snapshot.hasChild("homeworld")) {
                        if (snapshot.child("homeworld").hasChild("0")) {
                            planet = snapshot.child("homeworld").child("0").getValue(String.class);
                        } else {
                            planet = snapshot.child("homeworld").getValue(String.class);
                        }

                    }

                    if (snapshot.hasChild("eyeColor")) {
                        eyeColor = snapshot.child("eyeColor").getValue(String.class);
                    }
                    if (snapshot.hasChild("hairColor")) {
                        hairColor = snapshot.child("hairColor").getValue(String.class);
                    }
                    if (snapshot.hasChild("skinColor")) {
                        skinColor = snapshot.child("skinColor").getValue(String.class);
                    }
                    if (snapshot.hasChild("species")) {
                        species = snapshot.child("species").getValue(String.class);
                    }

                    if (!genderFilter.contains(gender))
                        genderFilter.add(gender);
                    if (!planetFilter.contains(planet))
                        planetFilter.add(planet);
                    if (!eyeColorFilter.contains(eyeColor))
                        eyeColorFilter.add(eyeColor);
                    if (!hairColorFilter.contains(hairColor))
                        hairColorFilter.add(hairColor);
                    if (!skinColorFilter.contains(skinColor))
                        skinColorFilter.add(skinColor);
                    if (!speciesFilter.contains(species))
                        speciesFilter.add(species);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final Spinner genderSpinner = findViewById(R.id.gender_filter);
        final Spinner planetSpinner = findViewById(R.id.planet_filter);
        final Spinner eyeSpinner = findViewById(R.id.eye_filter);
        final Spinner hairSpinner = findViewById(R.id.hair_filter);
        final Spinner skinSpinner = findViewById(R.id.skin_filter);
        final Spinner speciesSpinner = findViewById(R.id.species_filter);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, genderFilter);
        ArrayAdapter<String> planetAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, planetFilter);
        ArrayAdapter<String> eyeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, eyeColorFilter);
        ArrayAdapter<String> hairAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, hairColorFilter);
        ArrayAdapter<String> skinAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, skinColorFilter);
        ArrayAdapter<String> speciesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, speciesFilter);

        genderSpinner.setAdapter(genderAdapter);
        planetSpinner.setAdapter(planetAdapter);
        eyeSpinner.setAdapter(eyeAdapter);
        hairSpinner.setAdapter(hairAdapter);
        skinSpinner.setAdapter(skinAdapter);
        speciesSpinner.setAdapter(speciesAdapter);

        genderSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });



    }
}
