package com.example.primerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import static android.widget.ArrayAdapter.*;

public class Parametros extends AppCompatActivity {

    private Spinner cantPersonas;
    private Spinner mascarilla;
    private Spinner ventana;
    private Spinner tiempoHablando;
    private Spinner volumenVoz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros);

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float battery = (level / (float)scale)*100;
        SimpleDialog msjBateria = new SimpleDialog("Información de la batería", "Nivel de Batería: ", String.valueOf(battery));
        msjBateria.createSimpleDialog("Información de la batería", "Nivel de Batería: ", String.valueOf(battery));

        cantPersonas = findViewById(R.id.comboPersonas);
        ArrayAdapter<CharSequence> adapterPersonas = ArrayAdapter.createFromResource(this, R.array.personas, android.R.layout.simple_spinner_dropdown_item);
        cantPersonas.setAdapter(adapterPersonas);

        mascarilla = findViewById(R.id.comboMascarillas);
        ArrayAdapter<CharSequence> adapterMascarilla = ArrayAdapter.createFromResource(this,R.array.mascarillas, android.R.layout.simple_spinner_dropdown_item);
        mascarilla.setAdapter(adapterMascarilla);

        ventana = findViewById(R.id.comboVentanas);
        ArrayAdapter<CharSequence> adapterVentana = ArrayAdapter.createFromResource(this, R.array.ventanas, android.R.layout.simple_spinner_dropdown_item);
        ventana.setAdapter(adapterVentana);

        tiempoHablando = findViewById(R.id.comboTiempoHablando);
        ArrayAdapter<CharSequence> adapterTiempo = ArrayAdapter.createFromResource(this,R.array.tiempoHablando, android.R.layout.simple_spinner_dropdown_item);
        tiempoHablando.setAdapter(adapterTiempo);

        volumenVoz = findViewById(R.id.comboVolumenDeVoz);
        ArrayAdapter<CharSequence> adapterVolumen = ArrayAdapter.createFromResource(this,R.array.volumenDeVoz, android.R.layout.simple_spinner_dropdown_item);
        volumenVoz.setAdapter(adapterVolumen);

        }

}