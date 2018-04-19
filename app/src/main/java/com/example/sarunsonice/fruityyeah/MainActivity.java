package com.example.sarunsonice.fruityyeah;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    //ham_s
    private  String [] mDrawerTitle = {"Fruit"+"\n"+"(NOUN)"+"\n"+
            "The sweet and fleshy product of a tree or other plant that contains seed and can be eaten as food"};
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView mListView;


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    //ham_e

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();

            mDrawerLayout = findViewById(R.id.drawer_layout);
            actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                    mDrawerLayout,
                    R.string.open_drawer,
                    R.string.close_drawer);
            mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mListView = findViewById(R.id.drawer);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
                    android.R.layout.simple_list_item_1,mDrawerTitle );
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemValue = (String) mListView.getItemAtPosition(position);

                    mDrawerLayout.closeDrawers();
                    Toast.makeText(getApplicationContext(),
                            "Position :" + position + "  ListItem : " + itemValue, Toast.LENGTH_SHORT)
                            .show();
                }
            });

    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        switch (item.getItemId()) {
            case R.id.mnuNew:
                Toast.makeText(this, "New!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mnuHelp:
                Toast.makeText(this, "Help!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //menu

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.images_1, "Mango", "A fleshy, oval, yellowish-red tropical fruit that is eaten ripe or used green for pickles or chutneys."));
        mExampleList.add(new ExampleItem(R.drawable.images_2, "Orange", "A large round juicy citrus fruit with a tough bright reddish-yellow rind."));
        mExampleList.add(new ExampleItem(R.drawable.images_3, "Watermelon", "The large fruit of a plant of the gourd family, with smooth green skin, red pulp, and watery juice."));
        mExampleList.add(new ExampleItem(R.drawable.images_4, "Strawberry", "A sweet soft red fruit with a seed-studded surface."));
        mExampleList.add(new ExampleItem(R.drawable.images_5, "Lemon", "A pale yellow oval citrus fruit with thick skin and fragrant, acidic juice."));
        mExampleList.add(new ExampleItem(R.drawable.images_6, "Mangosteen", "A tropical fruit with sweet juicy white segments of flesh inside a thick reddish-brown rind."));
        mExampleList.add(new ExampleItem(R.drawable.images_7, "Grape", "A berry (typically green, purple, or black) growing in clusters on a grapevine, eaten as fruit and used in making wine."));
        mExampleList.add(new ExampleItem(R.drawable.images_8, "Kiwi fruit", "A fruit with a thin hairy skin, green flesh, and black seeds."));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("Example Item", mExampleList.get(position));

                startActivity(intent);
            }
        });
    }
}