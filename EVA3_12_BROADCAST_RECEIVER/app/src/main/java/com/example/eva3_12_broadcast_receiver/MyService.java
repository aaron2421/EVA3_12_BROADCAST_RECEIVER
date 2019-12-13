package com.example.eva3_12_broadcast_receiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    Thread thilo;
    public MyService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intMensaje = new Intent("MI_SERVICIO");
        intMensaje.putExtra("MENSAJE","OnCreate");
        sendBroadcast(intMensaje);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Intent intMensaje = new Intent("MI_SERVICIO");
        intMensaje.putExtra("MENSAJE","onStart");
        sendBroadcast(intMensaje);
        thilo = new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break; //Detener hilo
                    }
                    Intent intent1 = new Intent("MI_SERVICIO");
                    intent1.putExtra("MENSAJE",""+i++);
                    sendBroadcast(intent1);
                }
            }
        };
        thilo.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent intMensaje = new Intent("MI_SERVICIO");
        intMensaje.putExtra("MENSAJE","onDestroy");
        sendBroadcast(intMensaje);

        //Detener hilo
        thilo.interrupt();
    }
}
