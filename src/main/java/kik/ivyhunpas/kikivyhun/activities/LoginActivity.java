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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import kik.ivyhunpas.kikivyhun.R;
import kik.ivyhunpas.kikivyhun.entities.User;
import kik.ivyhunpas.kikivyhun.utils.gps.MyLocationListener;

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


                    // Force et récupère la location gps
                    LocationManager locationManager = (LocationManager)
                            getSystemService(Context.LOCATION_SERVICE);

                    LocationListener locationListener = new MyLocationListener(LoginActivity.this, currentUser);
                    if (ActivityCompat.checkSelfPermission(
                            LoginActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(
                            LoginActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                    }


                    currentUser.setGps(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));

                    startActivity(new Intent(LoginActivity.this, EventsActivity.class));
                }
            }
        });

        // Click sur le bouton register
        this.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }
}
