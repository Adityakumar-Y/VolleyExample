package com.example.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvDetail;
    private Button btnGet, btnUser;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        tvDetail = (TextView) findViewById(R.id.tvDetail);
        btnGet = (Button) findViewById(R.id.btnGet);
        btnUser = (Button) findViewById(R.id.btnUser);
        requestQueue = Volley.newRequestQueue(this);

        btnGet.setOnClickListener(this);
        btnUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGet:
                jsonParse();
                break;
            case R.id.btnUser:
                getUsers();
                break;
        }
    }

    private void getUsers() {
        Intent intent = new Intent(MainActivity.this, GithubActivity.class);
        startActivity(intent);
    }

    private void jsonParse() {
        String url = "https://api.myjson.com/bins/kp9wz";
        Log.d("OK", "Hello");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");
                            String s = "";
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                s += "Name: " + employee.getString("firstname") + "\n";
                                s += "Age: " + employee.getInt("age") + "\n";
                                s += "Email: " + employee.getString("mail") + "\n\n";
                            }
                            tvDetail.setText(s);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
