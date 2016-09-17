package com.filip.Challenge;

import android.app.Application;

import com.filip.Challenge.io.network_communication.EstablishConnection;

/**
 * Created by FILIP on 17-Sep-16.
 */

public class MyApplicationClass extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        EstablishConnection conn = new EstablishConnection(getApplicationContext());
        conn.execute();

    }
}
