package com.example.pharmaship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    static NavigationView navigationView;
    LinearLayout navHeaderLayout;
    TextView navHeaderName, navHeaderEmail;
    View headerView;
    public final String TAG = "message";
    public static MenuActivity returnInstance() {
        return new MenuActivity();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initializeWidgetsAndListeners(savedInstanceState);
        setHeaderCredentials();
    }

    private void initializeWidgetsAndListeners(Bundle savedInstanceState) {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ShoppingCartFragment()).commit();
            navigationView.setCheckedItem(R.id.shopping_cart);
        }
        headerView = navigationView.getHeaderView(0);

        navHeaderName = headerView.findViewById(R.id.navHeaderName);
        navHeaderEmail = headerView.findViewById(R.id.navHeaderEmail);
    }
    public static void setShoppingFragment() {
        navigationView.setCheckedItem(R.id.shopping_cart);
    }
    private void setHeaderCredentials() {
        ArrayList<String> list = LocalDatabase.retrieveLoginData();
        navHeaderName.setText(list.get(0));
        navHeaderEmail.setText(list.get(2));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.map:

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new PharmaciesFragment()).commit();

                break;
            case R.id.shopping_cart:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new ShoppingCartFragment()).commit();

                break;
            case R.id.profile:

                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                finish();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
