package kik.ivyhunpas.kikivyhun.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import kik.ivyhunpas.kikivyhun.R;
import kik.ivyhunpas.kikivyhun.entities.User;
import kik.ivyhunpas.kikivyhun.utils.database.DatabaseManager;
import kik.ivyhunpas.kikivyhun.utils.gps.UserLocationListener;

public class LoginActivity extends AppCompatActivity {

    public static final String LOGIN_KEY = "LOGIN_KEY";
    public static final String PASSWORD_KEY = "PASSWORD_KEY";

    private EditText login;
    private EditText password;
    private CheckBox passwordSave;
    private Button connection;
    private Button register;

    private SharedPreferences sharedPreferences; // Fichier de sauvegarde des préférences utilisateur

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.login = (EditText) findViewById(R.id.editTextLogin);
        this.password = (EditText) findViewById(R.id.editTextPassword);
        this.passwordSave = (CheckBox) findViewById(R.id.checkBoxSavePassword);
        this.connection = (Button) findViewById(R.id.btnConnect);
        this.register = (Button) findViewById(R.id.btnRegister);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences.contains(LOGIN_KEY) && sharedPreferences.contains(PASSWORD_KEY)) {
            login.setText(sharedPreferences.getString(LOGIN_KEY, null));
            password.setText(sharedPreferences.getString(PASSWORD_KEY, null));
            passwordSave.setChecked(true);
        }


        // Listeners

        // Click sur le bouton connection
        this.connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Si le savepass est checké on sauvegarde les log dans sharedpref
                if (LoginActivity.this.passwordSave.isChecked()) {
                    LoginActivity.this.sharedPreferences.edit()
                            .putString(LoginActivity.LOGIN_KEY, LoginActivity.this.login.getText().toString())
                            .apply();
                    LoginActivity.this.sharedPreferences.edit()
                            .putString(LoginActivity.PASSWORD_KEY, LoginActivity.this.password.getText().toString())
                            .apply();
                } else {
                    sharedPreferences.edit().remove(LOGIN_KEY).apply();
                    sharedPreferences.edit().remove(PASSWORD_KEY).apply();
                }

                if (LoginActivity.this.login.getText().toString().equals("login") && LoginActivity.this.password.getText().toString().equals("password")) {
                    User currentUser = new User();
                    currentUser.setLogin("login");
                    currentUser.setPassword("pass");
                    currentUser.setFirstname("firstname");
                    currentUser.setLastname("lastname");


                    // Force et récupère la localisation gps
                    UserLocationListener.getInstance()
                            .setUserLocationListener(LoginActivity.this, currentUser);

                    startActivity(new Intent(LoginActivity.this, EventsActivity.class));
                }
            }
        });

        // Click sur le bouton register
        this.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseManager databaseManager = new DatabaseManager(LoginActivity.this);
                User user = new User();
                user.setLogin(login.getText().toString());
                user.setPassword(password.getText().toString());

                databaseManager.insertUserData(user);

                Log.d("TEST", String.valueOf(user.getId()));
            }


                //startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });


    }
}
