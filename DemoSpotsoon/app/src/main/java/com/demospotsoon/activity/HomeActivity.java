package com.demospotsoon.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.demospotsoon.R;
import com.demospotsoon.fragment.CameraFragment;
import com.demospotsoon.fragment.GalleryFragment;
import com.demospotsoon.fragment.HomeFragment;
import com.demospotsoon.fragment.SettingsFragment;
import com.demospotsoon.fragment.SlideShowFragment;
import com.demospotsoon.model.DataItem;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG_HOME = "home";
    private final String TAG_IMPORT = "import";
    private final String TAG_GALLERY = "gallery";
    private final String TAG_SLIDESHOW = "slideshow";
    private final String TAG_TOOL = "tools";
    public String CURRENT_TAG = TAG_HOME;

    private DrawerLayout drawer;
    private NavigationView navigationView;

    private String[] activityTitles;
    private int navItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        populateView();
    }

    // initiate all views
    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        navItemIndex = 0;
        CURRENT_TAG = TAG_HOME;
    }

    private void populateView() {
        loadFragment();
    }

    // set toolbar title
    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    // selecting appropriate nav menu item
    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    // load select fragment
    private void loadFragment() {
        selectNavMenu();
        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }
        Fragment fragment = getHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
        drawer.closeDrawers();
    }

    // Create instance of selecting fragment
    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                CameraFragment cameraFragment = new CameraFragment();
                return cameraFragment;
            case 2:
                GalleryFragment galleryFragment = new GalleryFragment();
                return galleryFragment;
            case 3:
                SlideShowFragment slideshowFragment = new SlideShowFragment();
                return slideshowFragment;
            case 4:
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return new HomeFragment();
        }
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Handle navigation view item clicks here.
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
        } else if (id == R.id.nav_camera) {
            navItemIndex = 1;
            CURRENT_TAG = TAG_IMPORT;
        } else if (id == R.id.nav_gallery) {
            navItemIndex = 2;
            CURRENT_TAG = TAG_GALLERY;
        } else if (id == R.id.nav_slideshow) {
            navItemIndex = 3;
            CURRENT_TAG = TAG_SLIDESHOW;
        } else if (id == R.id.nav_manage) {
            navItemIndex = 4;
            CURRENT_TAG = TAG_TOOL;
        }

        drawer.closeDrawer(GravityCompat.START);

        //Checking if the item is in checked state or not, if not make it in checked state
        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        item.setChecked(true);

        loadFragment();
        return true;
    }
}
