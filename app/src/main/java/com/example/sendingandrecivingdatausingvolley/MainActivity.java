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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<FetchDataProvider> list = new ArrayList<FetchDataProvider>();
    private RecyclerAdapter recyclerAdapter;
    private FloatingActionButton floatingActionButton;

    private static MainActivity instance = null;

    public static MainActivity getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = MainActivity.this;
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
        backgroundTask.getArrayList();
        recyclerAdapter = new RecyclerAdapter(list, MainActivity.this);
        recyclerView.setAdapter(recyclerAdapter);

    }

    public void parseJsonData(JSONArray response){
        int count = 0;
        while (count < response.length()) {

            try {
                JSONObject jsonObject = response.getJSONObject(count);
                FetchDataProvider fetchDataProvider = new FetchDataProvider(
                        jsonObject.getString("Date"),
                        jsonObject.getString("Time"),
                        jsonObject.getString("Details"));
                list.add(fetchDataProvider);
                count++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        recyclerAdapter.notifyDataSetChanged();
    }

    public boolean isNetWorkAvailable(){

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
