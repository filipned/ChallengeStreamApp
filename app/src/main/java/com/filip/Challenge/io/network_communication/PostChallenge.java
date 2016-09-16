package com.filip.Challenge.io.network_communication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.filip.Challenge.io.sockets.ControlSocket;
import com.filip.Challenge.io.sockets.DataSocket;

import java.io.IOException;

import model.ChallengeListItem;

/**
 * Created by FILIP on 14-Aug-16.
 */

public class PostChallenge extends AsyncTask<Void, Void, String> {

    private ControlSocket controlSocket;
    private DataSocket dataSocket;
    private Context context;

    private String answer;
    private String successfulyAdded = "/";
    private ChallengeListItem challengeListItem;


    public PostChallenge(ChallengeListItem challengeListItem, Context context) {
        this.challengeListItem = challengeListItem;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            controlSocket = new ControlSocket("192.168.43.134", 45000);
//          1. Saljemo obavestenje o namjeri za slanje objekta ChallengeListItem
            controlSocket.sendChallengeRequest(ControlSocket.ADD_CHALLENGE_REQUEST);
//          2. Primamo odgovor od servera
            answer = controlSocket.recieveAnswer();
//          3. Provjeravamo da li je server odobrio slanje
            if(answer.equals("good")) {
                dataSocket = new DataSocket("192.168.43.134", 46000);
//              4. Saljemo objekat ChallengeListItem
                dataSocket.sendChallenge(challengeListItem);
//              5. Provjeravamo da li je objekat ChallengeListItem uspjesno poslat i ispisujemo odgovarajucu poruku
                if(controlSocket.recieveAnswer().equals("good")) {
                    successfulyAdded = "good";
                }
            }
//          6. Zatvaramo kontrolnu vezu i vezu podataka
//            controlSocket.closeControlStreams();
//            dataSocket.closeObjectDataStreams();
//            controlSocket.close();
//            dataSocket.close();
            controlSocket.shutdownInput();
            controlSocket.shutdownOutput();
            dataSocket.shutdownOutput();
            dataSocket.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return successfulyAdded;
    }



    @Override
    protected void onPostExecute(String s) {
        if(s.equals("good"))
            Toast.makeText(context, "Challenge added successfully!", Toast.LENGTH_SHORT);
        else
            Toast.makeText(context, "Adding challenge failed! Try again.", Toast.LENGTH_SHORT);

    }
}
