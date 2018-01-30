package creditos.com.creditohugo.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import creditos.com.creditohugo.Fragments.FragmentAbout;
import creditos.com.creditohugo.Fragments.FragmentHome;
import creditos.com.creditohugo.Fragments.FragmentShare;
import creditos.com.creditohugo.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CharSequence mTitle;
    private FragmentTransaction mFragmentTransaction;
    private FloatingActionButton mfab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mfab = (FloatingActionButton) findViewById(R.id.new_credit);
        mfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewCreditActivity.class);
                startActivityForResult(intent, NewCreditActivity.RESULT_NEW_ID);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.frame_host, new FragmentHome(), FragmentHome.TAG);
        mFragmentTransaction.commit();


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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_credits) {
            mFragmentTransaction.replace(R.id.frame_host, new FragmentHome());
            mfab.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_new_credit) {
            Intent intent = new Intent(this, NewCreditActivity.class);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else if (id == R.id.nav_share) {
            mFragmentTransaction.replace(R.id.frame_host, new FragmentShare());
            mfab.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_about) {
            mFragmentTransaction.replace(R.id.frame_host, new FragmentAbout());
            mfab.setVisibility(View.GONE);
        }
        mFragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NewCreditActivity.RESULT_NEW_ID) {
            if (resultCode == RESULT_OK) {

                FragmentHome home = (FragmentHome)
                        getSupportFragmentManager().findFragmentByTag(FragmentHome.TAG);
                home.dataSaved();
            }
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }
}
