package com.filip.Challenge.io.network_communication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.filip.Challenge.io.sockets.ControlSocket;

import java.io.IOException;

import static com.filip.Challenge.MainActivity.controlSocket;

/**
 * Created by FILIP on 17-Sep-16.
 */

public class EstablishConnection extends AsyncTask<Void, Void, Boolean> {

    private Context context;
//    public ControlSocket controlSocket;
    private Boolean succesfullConn = false;

    public EstablishConnection(Context context) {
        this.context = context;
    }
    @Override
    protected Boolean doInBackground(Void... params) {

        try {
            controlSocket = new ControlSocket("192.168.43.134", 45000);
            succesfullConn = true;
        } catch (IOException e) {

        }

        return succesfullConn;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean) {
            Toast.makeText(context, "Welcome!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Connection with server failed! Try again.", Toast.LENGTH_LONG).show();

        }
    }
}
