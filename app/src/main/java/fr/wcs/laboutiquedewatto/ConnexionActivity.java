package fr.wcs.laboutiquedewatto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ConnexionActivity extends AppCompatActivity {

    public static final int PASSWORD_HIDDEN = 1;
    public static final int PASSWORD_VISIBLE = 2;
    int mPasswordVisibility = PASSWORD_HIDDEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextPseudo = findViewById(R.id.et_pseudo);
        final EditText editTextPassword = findViewById(R.id.et_password);
        final EditText editTextPassword2 = findViewById(R.id.et_password2);
        final ImageView imageViewPassword = findViewById(R.id.iv_password_connection);
        final ImageView imageViewPassword2 = findViewById(R.id.iv_password_connection2);
        final ImageView imageViewLigne = findViewById(R.id.iv_ligne);
        final ImageView imageViewLigneBis = findViewById(R.id.iv_ligne_bis);
        final Button buttonConnexion = findViewById(R.id.b_log_in);
        final Button buttonMember = findViewById(R.id.b_create);
        final TextView textViewOr = findViewById(R.id.tv_or);
        final TextView textViewForgottenPassword = findViewById(R.id.tv_forgotten_password);

        imageViewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mPasswordVisibility == PASSWORD_HIDDEN) {
                    editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mPasswordVisibility = PASSWORD_VISIBLE;
                } else {
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPasswordVisibility = PASSWORD_HIDDEN;
                }
            }
        });

        imageViewPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mPasswordVisibility == PASSWORD_HIDDEN) {
                    editTextPassword2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mPasswordVisibility = PASSWORD_VISIBLE;
                } else {
                    editTextPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPasswordVisibility = PASSWORD_HIDDEN;
                }
            }
        });

        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMainActivity = new Intent(ConnexionActivity.this, MainActivity.class);
                startActivity(intentMainActivity);
            }
        });

        buttonMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextPassword2.setVisibility(View.VISIBLE);
                imageViewPassword2.setVisibility(View.VISIBLE);
                buttonConnexion.setVisibility(View.GONE);
                imageViewLigne.setVisibility(View.GONE);
                imageViewLigneBis.setVisibility(View.GONE);
                textViewOr.setVisibility(View.GONE);
                textViewForgottenPassword.setVisibility(View.GONE);
            }
        });
    }
}

