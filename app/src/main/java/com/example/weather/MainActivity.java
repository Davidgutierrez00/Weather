package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    ImageView imageView;
    TextView country_yt, city_yt, temp_yt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);
        country_yt = findViewById(R.id.country);
        city_yt = findViewById(R.id.city);
        temp_yt = findViewById(R.id.textView4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findWeater();
            }
        });

    }
    public void findWeater(){
        String city = editText.getText().toString();
String url = "https://api.openweathermap.org/data/3.0/onecall/timemachine?lat=39.099724&lon=-94.578331&dt=1643803200"+city+"&appid={7c4ac920fc62998c4ae052c7cb42cfbe}";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject object1 = jsonObject.getJSONObject("sys");
                    String country_find = object1.getString("country");
                    city_yt.setText(country_find);

                    String city_find = jsonObject.getString("name");
                    city_yt.setText(city_find);

                    JSONObject object2 = jsonObject.getJSONObject("main");
                    String temp_find = object1.getString("temp");
                    temp_yt.setText(temp_find+"%");

                    JSONArray jsonArray = jsonObject.getJSONArray("weather");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    String ing = jsonObject1.getString("icon");

                    Picasso.get().load("http://i.imgur.com/Dvpk1R.png").into(imageView);
                    


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
       RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
       requestQueue.add(stringRequest);
    }

}