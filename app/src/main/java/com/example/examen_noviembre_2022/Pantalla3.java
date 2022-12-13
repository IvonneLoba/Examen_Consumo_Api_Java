package com.example.examen_noviembre_2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class Pantalla3 extends AppCompatActivity {
    RecyclerView rv1;
    private List<Ganadores> Ganadores;
    private Ganadores_Adapter ganadores_Adapter;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla3);

        rv1 = findViewById(R.id.rv1);

        requestQueue=Volley_Singleton.getInstance(this).getRequestQueue();
        Mostrar();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv1.setLayoutManager(linearLayoutManager);
    }

    private void Mostrar() {
        String url="https://ramiro.uttics.com/api/ganadores";
        JsonObjectRequest requiere = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                Gson gson = new Gson();
                Respuesta respuesta = gson.fromJson(response.toString(), Respuesta.class);

                Ganadores = respuesta.data;

                ganadores_Adapter = new Ganadores_Adapter(Ganadores);
                rv1.setAdapter(ganadores_Adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(requiere);
    }
}
//Para el envio de info ay que crear un jsonobject, instanciarlo, crearle un
//jsonbody.put("Nombre", "Ramiro"), va dentro de un try catch;
//Ese jsonobject va sustituyeno el null en el parametro que se envia.
// Sustituir el Method.GET por el MEthod.POST, antes de la url.
//Voy a Enviar mi nombre a la url para interactuar con la api.
//Mandar Nombre Por el Header.

/////////////////////////////////////////////////////////////////////////////////////////////////

//GETHeader, creo un HashMap:
//Map (HashMap) =Es una colección de Arreglos, aqui puedo crear un
// arreglo No Asociativo porque seria <Integer, String> headers = new HAshMap<Integer, String>();
//ó
//HAshMap<String, String> headers = new HAshMap<String, String>();
//headers XIOKEY
//Arreglo Asociativo es un string de posición.
//Arreglo tipo entero como posicion pero con string por si es un Nombre.