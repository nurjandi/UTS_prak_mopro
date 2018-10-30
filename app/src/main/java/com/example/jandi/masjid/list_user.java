package com.example.jandi.masjid;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class list_user extends AppCompatActivity {
    private static final String Url_Data = "http://installin.000webhostapp.com/public/index.php/user/getUser";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<model_user> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        loadRecyclerViewData();
    }
    public void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data ...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url_Data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("data");
                            for(int i=0; i<array.length(); i++){
                                JSONObject o = array.getJSONObject(i);
                                model_user item = new model_user(
                                        o.getString("nama"),
                                        o.getString("jabatan"),
                                        o.getString("gambar"),
                                        o.getString("id"),
                                        o.getString("password"),
                                        o.getString("email"),
                                        o.getString("kontak"),
                                        o.getString("alamat")
                                );
                            listItems.add(item);
                            }
                            adapter = new UserAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}
