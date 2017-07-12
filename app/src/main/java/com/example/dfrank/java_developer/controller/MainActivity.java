package com.example.dfrank.java_developer.controller;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dfrank.java_developer.ItemAdapter;
import com.example.dfrank.java_developer.R;
import com.example.dfrank.java_developer.api.Client;
import com.example.dfrank.java_developer.api.Service;
import com.example.dfrank.java_developer.model.Item;
import com.example.dfrank.java_developer.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    TextView disconnected;
    SwipeRefreshLayout swipe;
    ProgressDialog progressDialog;
    private void initViews(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching java developers in lagos");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        recyclerView.smoothScrollToPosition(0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadJSON();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(getApplication(), "Java developers in lagos refreshed", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void loadJSON() {
        disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client client = new Client();
            Service service = client.getClient().create(Service.class);
            Call<ItemResponse> call = service.getItems();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items = response.body().getItem();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swipe.setRefreshing(false);
                    progressDialog.hide();
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                    Toast.makeText(getApplicationContext(), "Error fetching data, please check your internet connection", Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            Log.d("message", e.getMessage());
            Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
