package fr.wcs.laboutiquedewatto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final GridView shopView = findViewById(R.id.shop_view);
        final ArrayList<ProfilModel> profileList = new ArrayList<>();
        final GridAdapter adapter = new GridAdapter(this, profileList);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();
        if (profileList.size() != 0) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot profilSnap : dataSnapshot.getChildren()) {
                        String name = profilSnap.child("name").getValue(String.class);
                        String image = profilSnap.child("image").getValue(String.class);
                        Toast.makeText(ShopActivity.this, name + image, Toast.LENGTH_SHORT).show();
                        boolean isBought = profilSnap.child("isBought").getValue(Boolean.class);
                        if (isBought){
                            profileList.add(new ProfilModel(name, image));
                            Toast.makeText(ShopActivity.this, "pascool", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }
                        index++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            shopView.setAdapter(adapter);
        }


    }
}
