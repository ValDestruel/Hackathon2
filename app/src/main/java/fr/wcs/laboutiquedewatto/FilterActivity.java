package fr.wcs.laboutiquedewatto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bumptech.glide.load.resource.gif.GifDrawableEncoder;
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
    String name;
    String gender;
    String image;
    String price;

    String genderA;
    String genderB;
    String planetA;
    String planetB;
    String specieA;
    String specieB;
    String eyeA;
    String eyeB;
    String hairA;
    String hairB;
    String skinA;
    String skinB;


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
        final ArrayList<ProfilModel> allProfiles = new ArrayList<>();
        final ArrayList<ProfilModel> selectedProfiles = new ArrayList<>();


        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    gender = snapshot.child("gender").getValue(String.class);
                    name = snapshot.child("name").getValue(String.class);
                    image = snapshot.child("image").getValue(String.class);
                    price = snapshot.child("price").getValue(String.class);

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

                    allProfiles.add(new ProfilModel(name, gender, species, planet, image, hairColor, eyeColor, skinColor, price));



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

        genderSpinner.setSelection(0);
        planetSpinner.setSelection(0);
        eyeSpinner.setSelection(0);
        hairSpinner.setSelection(0);
        skinSpinner.setSelection(0);
        speciesSpinner.setSelection(0);


        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < allProfiles.size(); i++) {
                    genderA = allProfiles.get(i).getGender().toLowerCase();
                    genderB = genderSpinner.getSelectedItem().toString().toLowerCase();
                    if ((genderA.contains(genderB)) && (!selectedProfiles.contains(allProfiles.get(i)))) {
                        selectedProfiles.add(allProfiles.get(i));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        planetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProfiles.clear();
                for (int i = 0; i < allProfiles.size(); i++) {
                    planetA = allProfiles.get(i).getHomeworld().toLowerCase();
                    planetB = planetSpinner.getSelectedItem().toString().toLowerCase();
                    if (planetA.contains(planetB)&& (!selectedProfiles.contains(allProfiles.get(i)))) {
                        selectedProfiles.add(allProfiles.get(i));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        eyeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < allProfiles.size(); i++) {
                    eyeA = allProfiles.get(i).getEyeColor().toLowerCase();
                    eyeB = eyeSpinner.getSelectedItem().toString().toLowerCase();
                    if (eyeA.contains(eyeB)&& (!selectedProfiles.contains(allProfiles.get(i)))) {
                        selectedProfiles.add(allProfiles.get(i));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hairSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < allProfiles.size(); i++) {
                    hairA = allProfiles.get(i).getHairColor().toLowerCase();
                    hairB = hairSpinner.getSelectedItem().toString().toLowerCase();
                    if (hairA.contains(hairB)&& (!selectedProfiles.contains(allProfiles.get(i)))) {
                        selectedProfiles.add(allProfiles.get(i));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        skinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < allProfiles.size(); i++) {
                    skinA = allProfiles.get(i).getSkinColor().toLowerCase();
                    skinB = skinSpinner.getSelectedItem().toString().toLowerCase();
                    if (skinA.contains(skinB)&& (!selectedProfiles.contains(allProfiles.get(i)))) {
                        selectedProfiles.add(allProfiles.get(i));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        speciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i < allProfiles.size(); i++) {
                    specieA = allProfiles.get(i).getSpecies().toLowerCase();
                    specieB = speciesSpinner.getSelectedItem().toString().toLowerCase();
                    if (specieA.contains(specieB)&& (!selectedProfiles.contains(allProfiles.get(i)))) {
                        selectedProfiles.add(allProfiles.get(i));
                    }
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button search = findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, ResultActivity.class);
                intent.putParcelableArrayListExtra("Profils", selectedProfiles);
                startActivity(intent);
                selectedProfiles.clear();
            }
        });

    }
}
