package fr.wcs.laboutiquedewatto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final GridView shopView = findViewById(R.id.shop_view);
        final ArrayList<ProfilModel> profileList = new ArrayList<>();
        final GridAdapter adapter = new GridAdapter(this, profileList);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot profilSnap : dataSnapshot.getChildren()) {
                    String name = profilSnap.child("name").getValue(String.class);
                    String image = profilSnap.child("image").getValue(String.class);
                    profileList.add(new ProfilModel(name, image));
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        shopView.setAdapter(adapter);


    }
}
