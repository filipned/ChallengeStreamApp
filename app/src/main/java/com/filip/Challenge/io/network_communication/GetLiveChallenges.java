package com.filip.Challenge.io.network_communication;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.filip.Challenge.io.sockets.ControlSocket;
import com.filip.Challenge.io.sockets.DataSocket;
import com.filip.Challenge.tab_fragments.NowFragment;
import com.filip.Challenge.util.Convert;

import java.util.LinkedList;

import model.ChallengeLiveItem;
import model.User;

/**
 * Created by FILIP on 11-Aug-16.
 */

public class GetLiveChallenges extends AsyncTask<Object, Object, LinkedList<ChallengeLiveItem>> {

    private LinkedList<ChallengeLiveItem> liveChallenges = new LinkedList<>();
    private LinkedList<View> liveChallengesViews = new LinkedList<>();

    private Context context;
    private ControlSocket controlSocket;
    private DataSocket dataSocket;
    private String answer;

    private NowFragment nowFragment;

    public GetLiveChallenges(NowFragment nowFragment, Context context) {
        this.nowFragment = nowFragment;
        this.context = context;
    }

    @Override
    protected LinkedList<ChallengeLiveItem> doInBackground(Object... voids) {
//        try {
//            controlSocket = new ControlSocket("localhost", 12000);
////          1. Salje se zahtjev za listu live challenge-a preko kontrolne veze
//            controlSocket.sendRequest(ControlSocket.LIVE_CHALLENGES_REQUEST);
////          2. Prima se odgovor od servera preko kontrolne veze
//            answer = controlSocket.recieveAnswer();
////          3. Provjeravamo da li je odgovor dobar
//            if(answer == "good") {
////              4. Otvara se data soket i salje se odobrenje serveru za slanje podataka
//                dataSocket = new DataSocket("localhost", 13000);
//                dataSocket.sendSignal();
////              5. Primamo listu objekata ChallengeLiveItem od servera
//                liveChallenges = dataSocket.recieveLiveChallenges();
//            } else {
//                liveChallenges = null;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        try {
////            zatvaramo kontrolnu vezu i vezu podataka
//            dataSocket.closeObjectDataStreams();
//            controlSocket.closeControlStreams();
//            dataSocket.close();
//            controlSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        TESST OBJEKTI
        User u1 = new User("Filip", "Nedovic");
        User u2 = new User("Mitar", "Miric");

        ChallengeLiveItem c1 = new ChallengeLiveItem(u1, "Live 1");
        ChallengeLiveItem c2 = new ChallengeLiveItem(u2, "Live 2");

//      2.Primaju se live Challenges
        liveChallenges.add(c1);
        liveChallenges.add(c2);

        return liveChallenges;
    }

    @Override
    protected void onPostExecute(LinkedList<ChallengeLiveItem> challengeLiveItems) {

        liveChallengesViews = Convert.challengeLiveItemsToChallengeViews(challengeLiveItems, context);
        nowFragment.populateUI(liveChallengesViews);
    }
}
