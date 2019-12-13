package com.example.eva3_12_broadcast_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button boton1;
    Button boton2;
    TextView texto;

    BroadcastReceiver broadcastReceiver;
    Intent intServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intServicio = new Intent(this,MyService.class);
        broadcastReceiver = new MiReceptorDifusion();
        IntentFilter filtro = new IntentFilter("MI_SERVICIO");
        registerReceiver(broadcastReceiver,filtro);

        boton1 = findViewById(R.id.button); // start service
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intServicio);
            }
        });
        boton2 = findViewById(R.id.button2);// stop service
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intServicio);
            }
        });

        texto = findViewById(R.id.text);


    }

    class MiReceptorDifusion extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            texto.setText(texto.getText() +
                    intent.getStringExtra("MENSAJE")
                    +"\n");
        }
    }
}
