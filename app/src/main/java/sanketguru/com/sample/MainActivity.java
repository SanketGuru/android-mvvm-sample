package sanketguru.com.sample;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sanketguru.com.sample.Search.SearchFragment;
import sanketguru.com.sample.home.HomeFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener, OnFragmentInteractionListener {
    private static final String TRANSLATION_X_KEY = "TRANSLATION_X_KEY";
    private static final String CARD_ELEVATION_KEY = "CARD_ELEVATION_KEY";
    private static final String SCALE_KEY = "SCALE_KEY";
    //region View binding
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.mainView)
    CardView mainView;
    @BindView(R.id.nav_view)
    NavigationView navView;
    //endregion

    private Unbinder unBinder;
    private MainViewModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unBinder = ButterKnife.bind(this);

        mModel = ViewModelProviders.of(this).get(MainViewModel.class);
        setUpView();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {

        unBinder.unbind();
        super.onDestroy();
    }

    //region  DrawerLayout.DrawerListener
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        float moveFactor = navView.getWidth() * slideOffset;
        mainView.setTranslationX(moveFactor);
        mainView.setScaleX(1 - slideOffset / 4);
        mainView.setScaleY(1 - slideOffset / 5);
        mainView.setCardElevation(slideOffset * (15 * toPx(MainActivity.this)));
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    //endregion
    //TODO move to util class
    private float toPx(Context context) {
        return context.getResources().getDisplayMetrics().density;
        // return (this * density).toInt()
    }

    private void setUpView() {//        mModel.getUserDetail().observe(this, nameObserver);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                mModel.getUserDetail().setValue(new UserDetails(++daddt + "assj", "ff", new Date()));
//            }
//        });

        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //  drawer.addDrawerListener(toggle);
        //toggle.syncState();
        drawer.addDrawerListener(MainActivity.this);
        drawer.setScrimColor(Color.TRANSPARENT);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
      
        Fragment homeFragment= SearchFragment.newInstance("Hi","Ho");
        addFragmentToMain(homeFragment,false);
    }

    //region fragment inter action
    @Override
    public void onFragmentInteraction(@NotNull Object data) {

    }

    @Override
    public void addFragmentToMain(@NotNull Fragment fragment, boolean addtobackstack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainView, fragment);
        if (addtobackstack) {
            fragmentTransaction.addToBackStack("MainBack");
        }
        fragmentTransaction.commit();

    }

    @Override
    public void setTitleHead(@NotNull CharSequence title) {

    }

    @Override
    public void hideToolbar(boolean hide) {

    }

    @Override
    public void lockDrawer(boolean lock) {
        if (lock) {
            // Drawer will be open through swipe by user
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            // User will not be able to open Drawer but it can be open from application programmatically
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

    }
    //end region
}
