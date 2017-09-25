package com.example.jpr.dopecurrencyconverter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private TextView mButtonDo;
    private RecyclerView mRecyclerView;
    private String mJSONURLString = "https://restcountries.eu/rest/v2/all";
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Countries> countryName = new ArrayList<>();

        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        // Get the widget reference from XML layout
        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mButtonDo = (TextView) findViewById(R.id.btn_do);
        mRecyclerView = (RecyclerView) findViewById(R.id.meh);

        // Set a click listener for button widget
        mButtonDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                // Initialize a new JsonArrayRequest instance
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        mJSONURLString,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // Do something with response
                                for(int i=0;i<response.length();i++){
                                // Process the JSON
                                try{
                                    // Loop through the array elements
                                    Countries countries = new Countries();
                                        // Get current json object
                                        JSONObject student = response.getJSONObject(i);

                                        // Get the current student (json object) data
                                        countries.setCountryName(student.getString("name"));
                                    countries.setFlag(student.getString("flag"));
                                        countryName.add(countries);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }}
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                // Do something when error occurred
                                Snackbar.make(
                                        mCLayout,
                                        "Error...",
                                        Snackbar.LENGTH_LONG
                                ).show();
                            }
                        }
                );

                adapter = new CountryAdapter(getBaseContext(), countryName);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);
            }
        });
    }
}