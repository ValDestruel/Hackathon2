package fr.wcs.laboutiquedewatto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


public class ConnexionActivity extends AppCompatActivity {

    public static final int PASSWORD_HIDDEN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        final EditText editTextPseudo = findViewById(R.id.et_pseudo);
        final EditText editTextPassword = findViewById(R.id.et_password);
        final EditText editTextPassword2 = findViewById(R.id.et_password2);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final ImageView imageViewLigne = findViewById(R.id.iv_ligne);
        final ImageView imageViewLigneBis = findViewById(R.id.iv_ligne_bis);
        final Button buttonConnexion = findViewById(R.id.b_log_in);
        final Button buttonMember = findViewById(R.id.b_create);
        final TextView textViewOr = findViewById(R.id.tv_or);


        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(ConnexionActivity.this, MainActivity.class));
            finish();
        }


        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = editTextPseudo.getText().toString();
                String mdp = editTextPassword.getText().toString();

                if (pseudo.isEmpty()) {
                    Toast.makeText(ConnexionActivity.this, "Entrez un pseudo", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mdp.isEmpty()) {
                    Toast.makeText(ConnexionActivity.this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInAnonymously().addOnCompleteListener(ConnexionActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(ConnexionActivity.this, "Connection échouée", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intentMainActivity = new Intent(ConnexionActivity.this, MainActivity.class);
                            startActivity(intentMainActivity);
                        }
                    }
                });


            }
        });

        buttonMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMember.setText("Je rejoins la communauté !");
                editTextPassword2.setVisibility(View.VISIBLE);
                buttonConnexion.setVisibility(View.GONE);
                imageViewLigne.setVisibility(View.GONE);
                imageViewLigneBis.setVisibility(View.GONE);
                textViewOr.setVisibility(View.GONE);
                buttonMember.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pseudo = editTextPseudo.getText().toString();

                        String mdp = editTextPassword.getText().toString();

                        String mdp2 = editTextPassword2.getText().toString();

                        if (pseudo.isEmpty() || mdp.isEmpty() || mdp2.isEmpty()) {
                            Toast.makeText(ConnexionActivity.this, "Veuillez entrer tous les champs", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if (mdp.length() < 6) {
                            Toast.makeText(ConnexionActivity.this, "Le mot de passe est trop court", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if (!mdp2.contains(mdp)) {
                            Toast.makeText(ConnexionActivity.this, "Les mots de passe sont différents", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            startActivity(new Intent(ConnexionActivity.this, MainActivity.class));

                        }

                    }
                });

            }
        });
    }
}


