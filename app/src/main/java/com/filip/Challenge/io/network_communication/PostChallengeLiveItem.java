package com.filip.Challenge.io.network_communication;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.filip.Challenge.io.sockets.ControlSocket;
import com.filip.Challenge.io.sockets.DataSocket;

import java.io.IOException;

import model.ChallengeLiveItem;
import model.User;

/**
 * Created by FILIP on 19-Aug-16.
 */

public class PostChallengeLiveItem extends AsyncTask<Void, Void, Boolean> {

    private ChallengeLiveItem challengeLiveItem;
    private User user;
    private String challengeName;
    private ControlSocket controlSocket;
    private DataSocket dataSocket;
    private String answer;
    private Dialog streamDialog;
    private Boolean postSuccessful;
    private Toast info;
    private Context context;

    public PostChallengeLiveItem(User user, String challengeName, Dialog streamDialog, Context context) {
        this.user = user;
        this.challengeName = challengeName;
        this.streamDialog = streamDialog;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void[] voids) {

        challengeLiveItem = new ChallengeLiveItem(user, challengeName);
                        try {
                            ControlSocket controlSocket = new ControlSocket("localhost", 12000);
//                            1. Saljemo obavjestenje o namjeri slanja ChallengeLiveItemObjekta
                            controlSocket.sendLiveChallengeRequest(ControlSocket.ADD_LIVE_CHALLENGE_REQUEST);
//                            2. Primamo odgovor od servera
                            answer = controlSocket.recieveAnswer();
                            if (answer == "good") {
                                dataSocket = new DataSocket("localhost", 13000);
//                                3. Saljemo ChallengeLiveItem objekat
                                dataSocket.sendLiveChallenge(challengeLiveItem);
//                                4. Provjeravamo da li je server dobio dobar objekat
                                if(dataSocket.recieveAnswer()) {
                                    postSuccessful = dataSocket.recieveAnswer();
                                }
                            }

//                            srediti da se veze zatvore kada treb
                            controlSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
        return postSuccessful;
    }

    @Override
    protected void onPostExecute(Boolean postSuc) {
        streamDialog.cancel();

        if(postSuc) {
//        pokrenuti kamerastream

        }
    }

}
