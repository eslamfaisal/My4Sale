package com.fekrah.my4sale.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.my4sale.R;
import com.fekrah.my4sale.fragments.AddAdFragment;
import com.fekrah.my4sale.fragments.CallUsFragment;
import com.fekrah.my4sale.fragments.CategoriesFragment;
import com.fekrah.my4sale.fragments.ChatsFragment;
import com.fekrah.my4sale.fragments.FavoritesFragment;
import com.fekrah.my4sale.fragments.MainFragment;
import com.fekrah.my4sale.fragments.MyAdsFragment;
import com.fekrah.my4sale.fragments.ProfileFragment;
import com.fekrah.my4sale.fragments.SearchFragment;
import com.fekrah.my4sale.helper.SharedHelper;
import com.fekrah.my4sale.models.Ad;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainFragment.ChangeNewsListener , MyAdsFragment.UpDateMyAd{

    FragmentManager fragmentManager = getSupportFragmentManager();
    CallUsFragment callUsFragment = new CallUsFragment();
    SearchFragment searchFragment = new SearchFragment();
    AddAdFragment addAdFragment = new AddAdFragment();
    MainFragment mainFragment = new MainFragment();
    public static MyAdsFragment myAdsFragment = new MyAdsFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    ChatsFragment chatsFragment = new ChatsFragment();
    CategoriesFragment categoriesFragment = new CategoriesFragment();
    FavoritesFragment favoritesFragment = new FavoritesFragment();
    private SimpleDraweeView profileImage;
    private TextView profileName;

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

        View header = navigationView.getHeaderView(0);
        profileImage = header.findViewById(R.id.profile_image);
        profileName = header.findViewById(R.id.profile_name);
        if (SharedHelper.getKey(this, LoginActivity.IS_LOGIN).equals("no")) {
            profileImage.setImageURI("");
            profileName.setText("");
        } else {
            profileImage.setImageURI(SharedHelper.getKey(this, LoginActivity.IMAGE));
            profileName.setText(SharedHelper.getKey(this, LoginActivity.USER_NAME));
        }

        fragmentManager.beginTransaction().add(R.id.container, mainFragment).commit();
        setTitle(R.string.main_page);
        navigationView.setCheckedItem(0);
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

        if (getTitle().equals(getString(R.string.profile)) &&SharedHelper.getKey(this, LoginActivity.IS_LOGIN).equals("yes")) {
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

            Intent intent = new Intent(this, RegisterActivity.class);
            intent.putExtra("edit", "true");
            startActivity(intent);
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
            if (!SharedHelper.getKey(this, LoginActivity.IS_LOGIN).equals("yes")) {
                startLoginActivity();
            } else {
                fragmentManager.beginTransaction().replace(R.id.container, profileFragment).commit();
                setTitle(R.string.profile);
                invalidateOptionsMenu();
            }

        } else if (id == R.id.nav_messages) {
            if (!SharedHelper.getKey(this, LoginActivity.IS_LOGIN).equals("yes")) {
                startLoginActivity();
            } else {
                fragmentManager.beginTransaction().replace(R.id.container, chatsFragment).commit();
                setTitle(R.string.messages);
                invalidateOptionsMenu();
            }
        } else if (id == R.id.nav_add_ad) {
            if (!SharedHelper.getKey(this, LoginActivity.IS_LOGIN).equals("yes")) {
                startLoginActivity();
            } else {
                fragmentManager.beginTransaction().replace(R.id.container, addAdFragment).commit();
                setTitle(R.string.new_ad);
                invalidateOptionsMenu();
            }
        } else if (id == R.id.nav_my_ads) {
            if (!SharedHelper.getKey(this, LoginActivity.IS_LOGIN).equals("yes")) {

                startLoginActivity();
            } else {
                fragmentManager.beginTransaction().replace(R.id.container, myAdsFragment).commit();
                setTitle(R.string.my_ads);
                invalidateOptionsMenu();
            }
        } else if (id == R.id.nav_search) {
            fragmentManager.beginTransaction().replace(R.id.container, searchFragment).commit();
            setTitle(R.string.search);
            invalidateOptionsMenu();
        } else if (id == R.id.nav_call_us) {
            fragmentManager.beginTransaction().replace(R.id.container, callUsFragment).commit();
            setTitle(R.string.cal_us);
            invalidateOptionsMenu();
        } else if (id == R.id.nav_logout) {
            SharedHelper.putKey(this, LoginActivity.IS_LOGIN, "no");
            profileImage.setImageURI("");
            profileName.setText("");
        } else if (id == R.id.nav_terms) {

        } else if (id == R.id.nav_partition) {
            fragmentManager.beginTransaction().replace(R.id.container, categoriesFragment).commit();
            setTitle(R.string.partitions);
            invalidateOptionsMenu();
        } else if (id == R.id.nav_my_fav) {
            if (!SharedHelper.getKey(this, LoginActivity.IS_LOGIN).equals("yes")) {
                startLoginActivity();
            } else {
                fragmentManager.beginTransaction().replace(R.id.container, favoritesFragment).commit();
                setTitle(R.string.my_fav);
                invalidateOptionsMenu();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        addAdFragment.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12587) {
            profileImage.setImageURI(SharedHelper.getKey(this, LoginActivity.IMAGE));
            profileName.setText(SharedHelper.getKey(this, LoginActivity.USER_NAME));
        }
    }


    @Override
    public void goToNews() {
        fragmentManager.beginTransaction().replace(R.id.container, categoriesFragment).commit();
        setTitle(R.string.partitions);
        invalidateOptionsMenu();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 12587);
    }

    @Override
    public void goUpdate(Ad.AdData ad) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("my_ad",ad);
        addAdFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container, addAdFragment).commit();
        setTitle(R.string.edit);
        invalidateOptionsMenu();
    }
}
