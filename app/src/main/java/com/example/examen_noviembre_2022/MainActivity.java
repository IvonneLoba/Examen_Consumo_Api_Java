package com.example.examen_noviembre_2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Response.Listener<Ganadores>ganadoresListener;

    String nombre = "Nombre";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtnombre = findViewById(R.id.txtNombre);
        txtnombre.setText(nombre);

        setContentView(R.layout.activity_main);

        CountDownTimer t = new CountDownTimer(4000, 1000) {
            TextView t= findViewById(R.id.txtConteo);
            @Override
            public void onTick(long l) {
                t.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), Inicio.class);
                intent.putExtra("nombre", nombre);

                startActivity(intent);
            }
        } .start();


    }
    @Override
    public void onClick(View view) {

    }
}