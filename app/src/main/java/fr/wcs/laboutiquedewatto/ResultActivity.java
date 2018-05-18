package fr.wcs.laboutiquedewatto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final ListView listView = findViewById(R.id.result_list);
        final Intent intent = getIntent();
        final ArrayList<ProfilModel> profils = intent.getParcelableArrayListExtra("Profils");
        final ResultAdapter adapter = new ResultAdapter(this, profils);
        //adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
