package com.example.pharmaship;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LocalDatabase {
    public static ArrayList<String> credentials;
    public static List<Prescription> prescriptionList;
    public static Marker tempMarker;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String LOGIN_SAVED = "loginSaved";
    public static final String TAG = "message";
    public static boolean SUCCESSFUL_SIGN_UP = false;
    public static Context context;
    public static SharedPreferences sharedPreferences;
    public static void initialSetup(Context context) {

        credentials = new ArrayList<>();
        prescriptionList = new ArrayList<>();
        LocalDatabase.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    }



    public static void saveLogin(boolean login) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGIN_SAVED, login);
        editor.apply();
    }
    public static boolean retrieveLoginSaved() {
        return sharedPreferences.getBoolean(LOGIN_SAVED,false);
    }

    public static void saveLoginInfo(String username, String emailAddress, String password) {
        Toast toast;
        int duration = Toast.LENGTH_SHORT;
        CharSequence sequence="";
        if (username.equals("") || emailAddress.equals("") || password.equals("")) {
            sequence = "Incomplete Sign Up";
            toast = Toast.makeText(context,sequence,duration);
            toast.show();
            SUCCESSFUL_SIGN_UP = false;
            return;
        }
        SUCCESSFUL_SIGN_UP = true;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(EMAIL, password);
        editor.putString(PASSWORD, emailAddress);
        editor.apply();
    }
    public static ArrayList<String> retrieveLoginData() {
        credentials = retrieveDataHelper();
        return credentials;
    }
    private static ArrayList<String> retrieveDataHelper() {
        ArrayList<String> list = new ArrayList<>();
        list.add(sharedPreferences.getString(USERNAME, ""));
        list.add(sharedPreferences.getString(EMAIL, ""));
        list.add(sharedPreferences.getString(PASSWORD, ""));


        return list;
    }
    public static void addPrescription(Prescription prescription) {
        prescriptionList.add(prescription);
    }
    private static boolean checkSignUpValidity(String s) {
        return s.equals("");
    }
}
