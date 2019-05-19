package com.example.cardview_gridlayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF677589")));

        mRelativeLayout = (RelativeLayout)findViewById(R.id.rl);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        String[] animal = {
                "Aardvark",
                "Albatross",
                "Alligator",
                "Alpaca",
                "Ant",
                "Anteater",
                "Antelope",
                "Ape",
                "Armadillo",
                "DonKey",
                "Baboon",
                "Badger",
                "Barracuda",
                "Bear",
                "Beaver",
                "Bee"
        };

        mLayoutManager = new GridLayoutManager(mContext,3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AnimalsAdapter(mContext, animals);

        mRecyclerView.setAdapter(mAdapter);
    }
}
