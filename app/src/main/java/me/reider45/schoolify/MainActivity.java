package me.reider45.schoolify;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import me.reider45.schoolify.fragments.ChemistryFragment;
import me.reider45.schoolify.fragments.MainFragment;
import me.reider45.schoolify.fragments.MathFragment;
import me.reider45.schoolify.fragments.SleepFragment;
import me.reider45.schoolify.fragments.WeatherFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MainFragment main;
    MathFragment math;
    SleepFragment sleep;
    WeatherFragment weather;
    ChemistryFragment chemistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        main = new MainFragment();
        math = new MathFragment();
        sleep = new SleepFragment();
        weather = new WeatherFragment();
        chemistry = new ChemistryFragment();

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, main).commit();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fm = getFragmentManager();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.MainFragment) {
            fm.beginTransaction().replace(R.id.content_frame, main).commit();
        } else
        if (id == R.id.MathFragment) {
            fm.beginTransaction().replace(R.id.content_frame, math).commit();
        } else
        if (id == R.id.SleepFragment) {
            fm.beginTransaction().replace(R.id.content_frame, sleep).commit();
        } else
        if (id == R.id.WeatherFragment) {
            fm.beginTransaction().replace(R.id.content_frame, weather).commit();
        } else
        if (id == R.id.ChemistryFragment) {
            fm.beginTransaction().replace(R.id.content_frame, chemistry).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
