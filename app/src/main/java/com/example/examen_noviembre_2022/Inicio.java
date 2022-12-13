package com.example.examen_noviembre_2022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Inicio extends AppCompatActivity {
    TextView txtPantalla;
    RequestQueue requestQueue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        requestQueue = Volley_Singleton.getInstance(this).getRequestQueue();

        Button btnPNum, btnR, btnPut;

        String url = "https://ramiro.uttics.com/api/numero";

        btnPNum = findViewById(R.id.btnPNum);
        btnR = findViewById(R.id.btnR);
        btnPut = findViewById(R.id.btnPut);
        txtPantalla = findViewById(R.id.txtPantalla);

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicio.this, Pantalla3.class);
                startActivity(intent);
            }
        });
        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarNombre();
            }
        });

        btnPNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarNum();
            }
        });
    }

    private void EnviarNombre() {
        String url = "https://ramiro.uttics.com/api/enviarnumero";
        JSONObject persona = new JSONObject();
        JSONObject arreglo = new JSONObject();
        try {
            persona.put("nombre", "Ivonne");
            persona.put("numero", txtPantalla.getText());

            arreglo.put("persona", persona);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest requerir = new JsonObjectRequest(Request.Method.POST, url, arreglo, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String err = error.getMessage().toString();
                Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(requerir);
    }

    private void MostrarNum() {
        String url = "https://ramiro.uttics.com/api/numero";
        JsonObjectRequest requiere = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Carta carta = gson.fromJson(response.toString(), Carta.class);
                Integer num = carta.getNumero()+Integer.parseInt(txtPantalla.getText().toString());
                txtPantalla.setText(num.toString());

                if (num > 21){
                    txtPantalla.setText("Perdiste....Fin del Juego");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(requiere);
    }
}
