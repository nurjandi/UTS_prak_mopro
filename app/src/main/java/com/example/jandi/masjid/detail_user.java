package com.example.jandi.masjid;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

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

public class detail_user extends AppCompatActivity {
    String Url_Data;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<model_user> listItems;
    private TextView textView[] = new TextView[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        Url_Data = "http://installin.000webhostapp.com/public/index.php/user/getUser";
        textView[0] = (TextView) findViewById(R.id.nama);
        textView[1] = (TextView) findViewById(R.id.alamat);
        textView[2] = (TextView) findViewById(R.id.kontak);
        textView[3] = (TextView) findViewById(R.id.email);
        textView[4] = (TextView) findViewById(R.id.jabatan);
        String sessionId= getIntent().getStringExtra("ID");
        loadRecyclerViewData(sessionId);
    }

    public void loadRecyclerViewData(final String id){
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
                            int ID = Integer.parseInt(id);
                            JSONObject o = array.getJSONObject(ID-1);
                            String nama = o.getString("nama");
                            String jabatan = o.getString("jabatan");
                            String gambar = o.getString("gambar");
                            String id = o.getString("id");
                            String password = o.getString("password");
                            String email = o.getString("email");
                            String kontak = o.getString("kontak");
                            String alamat = o.getString("alamat");
                            textView[0].setText(nama);
                            textView[1].setText(alamat);
                            textView[2].setText(kontak);
                            textView[3].setText(email);
                            textView[4].setText(jabatan);

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
