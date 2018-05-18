package fr.wcs.laboutiquedewatto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ArgentActivity extends AppCompatActivity {

    private ImageView retour;
    private EditText email;
    private EditText card;
    private EditText date;
    private EditText crypto;
    private Button valide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argent);

        email = findViewById(R.id.editText_email);
        card = findViewById(R.id.editText_card);
        date = findViewById(R.id.editText_date);
        crypto = findViewById(R.id.editText_crypto);
        valide = findViewById(R.id.button_validation);

        valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));
                TextView textToast = (TextView) layout.findViewById(R.id.text);
                textToast.setText(R.string.paiement);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }
        });

    }
}
