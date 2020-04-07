package com.example.pharmaship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "message";
    public static boolean usernameFocused = false;
    public static boolean passwordClicked = false;
    EditText usernameEditText, passwordEditText;
    Button loginButton;
    TextView signUpTextView;
    public static boolean permissionsGranted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeWidgets();
        clickListeners(this);
        checkPermissions();
    }

    private void initializeWidgets() {
        usernameEditText = findViewById(R.id.loginUsername);
        passwordEditText = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.signUpTextView);
    }

    private void clickListeners(final Activity activity) {
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SignUpActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }
        });
    }

    public boolean loginErrorHandling(String username, String password) {
        CharSequence text = "";
        if (username.equals("") || password.equals("")) {
            text = "Empty Username or Password";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;

    }

    private void login() {
        if (permissionsGranted) {
            String loginUsername = usernameEditText.getText().toString();
            String loginPassword = passwordEditText.getText().toString();
            String[] storedCredentials = new String[2];
            ArrayList<String> tempCredentials = LocalDatabase.retrieveLoginData();
            for (int i = 0; i < tempCredentials.size() - 1; i++) {
                storedCredentials[i] = tempCredentials.get(i);
            }

            loginErrorHandling(storedCredentials[0], storedCredentials[1]);
            if (loginUsername.equals(storedCredentials[0]) && loginPassword.equals(storedCredentials[1])) {
                CharSequence text = "Successful Login";
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toast.show();
                LocalDatabase.saveLogin(true);
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                finish();
            } else {
                CharSequence text = "Unsuccessful Login";
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {

        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionsGranted = true;
            }
            else {
                permissionsGranted = false;
            }

        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

