package com.fekrah.my4sale.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.fragments.AddAdFragment;
import com.fekrah.my4sale.fragments.CallUsFragment;
import com.fekrah.my4sale.fragments.MainFragment;
import com.fekrah.my4sale.fragments.MyAdsFragment;
import com.fekrah.my4sale.fragments.ProfileFragment;
import com.fekrah.my4sale.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager = getSupportFragmentManager();
    CallUsFragment callUsFragment =new CallUsFragment();
    SearchFragment searchFragment = new SearchFragment();
    AddAdFragment addAdFragment = new AddAdFragment();
    MainFragment mainFragment = new MainFragment();
    MyAdsFragment myAdsFragment = new MyAdsFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager.beginTransaction().add(R.id.container, mainFragment).commit();
        navigationView.setCheckedItem(R.id.nav_main);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    MenuItem editProfile;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        editProfile = menu.findItem(R.id.edit_profile);
        if (getTitle().equals(getString(R.string.profile))) {
            editProfile.setVisible(true);
        } else {
            editProfile.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit_profile) {

            startActivity(new Intent(this,EditProfile.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            fragmentManager.beginTransaction().replace(R.id.container, mainFragment).commit();
            setTitle(R.string.main_page);
            invalidateOptionsMenu();
        } else if (id == R.id.nav_profile) {
            fragmentManager.beginTransaction().replace(R.id.container, profileFragment).commit();
            setTitle(R.string.profile);
            invalidateOptionsMenu();
        } else if (id == R.id.nav_messages) {

        } else if (id == R.id.nav_add_ad) {
            fragmentManager.beginTransaction().replace(R.id.container, addAdFragment).commit();
            setTitle(R.string.new_ad);
            invalidateOptionsMenu();
        } else if (id == R.id.nav_my_ads) {
            fragmentManager.beginTransaction().replace(R.id.container, myAdsFragment).commit();
            setTitle(R.string.my_ads);
            invalidateOptionsMenu();
        } else if (id == R.id.nav_search) {
            fragmentManager.beginTransaction().replace(R.id.container, searchFragment).commit();
            setTitle(R.string.search);
            invalidateOptionsMenu();
        } else if (id == R.id.nav_call_us) {
            fragmentManager.beginTransaction().replace(R.id.container, callUsFragment).commit();
            setTitle(R.string.cal_us);
            invalidateOptionsMenu();
        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_terms) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
