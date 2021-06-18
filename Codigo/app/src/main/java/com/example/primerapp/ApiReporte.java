package com.example.primerapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApiReporte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_reporte);
        ReporteAPITask reporte = new ReporteAPITask();
        reporte.execute();
    }

    // Asynctask ---------------------------------------------------------------------------------
    public class ReporteAPITask extends android.os.AsyncTask<String, Void, Integer> {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Integer doInBackground(String... params) {
            URL url = null;
            try {
                url = new URL("https://covid-193.p.rapidapi.com/history?country=Argentina&day=2021-06-17");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection conn;
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("x-rapidapi-key", "f962429069msh4509b9c676b5f63p10a40bjsn73daa23ab641");
                conn.setRequestProperty("x-rapidapi-host", "covid-193.p.rapidapi.com");

                int respCode = conn.getResponseCode();
                String respMessage = conn.getResponseMessage();
                System.out.println(respCode + " " + respMessage);

                InputStream response = new BufferedInputStream(conn.getInputStream());
                InputStreamReader inputStreamReader = new InputStreamReader(response);
                Stream<String> streamOfString= new BufferedReader(inputStreamReader).lines();
                String streamToString = streamOfString.collect(Collectors.joining());
                System.out.println(streamToString);
                String cadenaCortada = streamToString.substring(streamToString.indexOf("\"cases"), streamToString.indexOf(",\"tests"));
                System.out.println(cadenaCortada);
                String nuevosCasos = cadenaCortada.substring(cadenaCortada.indexOf("new")+6,cadenaCortada.indexOf("active")-3);
                System.out.println("Nuevos Casos: "+ nuevosCasos);
                return respCode;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

}
