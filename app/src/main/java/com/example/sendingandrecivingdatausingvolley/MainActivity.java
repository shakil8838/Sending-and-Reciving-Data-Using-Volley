package com.example.sendingandrecivingdatausingvolley;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sendingandrecivingdatausingvolley.adapters.RecyclerAdapter;
import com.example.sendingandrecivingdatausingvolley.apis.BackgroundTask;
import com.example.sendingandrecivingdatausingvolley.dataProviders.FetchDataProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<FetchDataProvider> list;
    private RecyclerAdapter recyclerAdapter;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.AddItem);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddInfo.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.TheList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);
        list = backgroundTask.getArrayList();
        recyclerAdapter = new RecyclerAdapter(list,MainActivity.this);
        recyclerView.setAdapter(recyclerAdapter);

    }

    public boolean isNetWorkAvailable(){

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
