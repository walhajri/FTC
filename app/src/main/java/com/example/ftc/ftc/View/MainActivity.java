package com.example.ftc.ftc.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ftc.ftc.API.RemoteDataSource;
import com.example.ftc.ftc.Model.Metadata;
import com.example.ftc.ftc.Model.Post;
import com.example.ftc.ftc.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Post generateDumbData(int i){
        Post p = new Post();
        p.setDescription("this is the Description");
        p.setLatitude(46.668108);
        p.setLongitude(24.756080);
        Metadata m=new Metadata();
        String[] urls={"https://power.cummins.com/sites/default/files/Food%20Truck_0.jpg","https://torontofoodtrucks.ca/wp-content/uploads/2016/03/20160722-wayhome2048-04.jpg","http://cerveceriaallende.mx/wp-content/uploads/2015/08/Bonkrep.jpg","https://d36tnp772eyphs.cloudfront.net/blogs/2/2016/11/tentempie-600x450.jpg"};
        m.setImgPath(urls[i%urls.length]);
        m.setName("this is the name");
        m.setType(1);
        m.setWorkingHours("12PM - 3AM");
        p.setMetadata(m);
        return p;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPost();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();
    }

    private void init() {
        List<Post> listofPost= new ArrayList<>();
        for (int i=0;i<15;i++) {
            listofPost.add(generateDumbData(i));
            Log.w("loop counter",i+"");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        PostAdapter postAdapter = new PostAdapter(listofPost,this);
        RecyclerView recyclerView =findViewById(R.id.rec1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(postAdapter);
    }

    public void addPost(){
        Intent intent = new Intent(this, AddPostActivity.class);
        startActivity(intent);
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

        if (id == R.id.nav_camera) {
            displayProfile();
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

    public void displayProfile(){
        Intent intent = new Intent(this, DisplayProfileActivity.class);
        startActivity(intent);
    }
}
