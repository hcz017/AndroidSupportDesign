package com.hcz017.androidsupportdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private TextView mTvContent;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //获得头像ImageView
        View headerView = navigationView.getHeaderView(0);
        CircleImageView ivAvatar = (CircleImageView) headerView.findViewById(R.id.profile_image);
        ivAvatar.setImageResource(R.drawable.avatar);

//        mTvContent = (TextView) findViewById(R.id.tv_content);
        customNaviMenu(navigationView);
    }

    public void customNaviMenu(NavigationView navigationView){
        //取消显示统一配色（即显示本色）
        navigationView.setItemIconTintList(null);
        // TODO: 2017/4/6  如何让item文字显示不同的颜色，和icon一致？
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
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        navigationView.setCheckedItem(id);
        Fragment fragment = null;
        String title = null;


        switch (id) {
            case R.id.nav_anim_vct_dw:
                fragment = new AnimatedVectorDrawableFragment();
                title = getString(R.string.recycler_view_title);
                break;
            case R.id.nav_recycler:
                fragment = new RecyclerViewFragment();
                title = getString(R.string.recycler_view_title);
                break;
            case R.id.nav_slideshow:
//            mTvContent.setText("Slideshow");
                break;
            case R.id.nav_manage:
//            mTvContent.setText("Manage");
                break;
            case R.id.nav_share:
                Intent mScrollingNoClpsIntent = new Intent(this, ScrollingNoClps.class);
                startActivity(mScrollingNoClpsIntent);
                break;
            case R.id.nav_send:
                Intent mScrollingIntent = new Intent(this, ScrollingActivity.class);
                startActivity(mScrollingIntent);
                break;
            case R.id.nav_tab_layout:
                Intent tabLayoutIntent = new Intent(this, MyTabLayout.class);
                startActivity(tabLayoutIntent);
            default:
                break;
        }
        
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        // set the toolbar title
        if (fragment != null && getSupportActionBar() != null) {
            Log.d(TAG, "displayView: title " + title);
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.fab:
                Intent secActivityIntent = new Intent(this, SecActivity.class);
                startActivity(secActivityIntent);
                break;
            default:
                break;
        }
    }
}
