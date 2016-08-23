package com.filip.Challenge.io.network_communication;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.filip.Challenge.model.ChallengeListItem;
import com.filip.Challenge.util.Convert;
import com.filip.Challenge.io.sockets.ControlSocket;
import com.filip.Challenge.io.sockets.DataSocket;
import com.filip.Challenge.tab_fragments.ChallengesFragment;

import java.util.LinkedList;

/**
 * Created by FILIP on 14-Aug-16.
 */

public class GetListChallenges extends AsyncTask<Object, Object, LinkedList<ChallengeListItem>> {

    private LinkedList<ChallengeListItem> listChallenges = new LinkedList<>();
    private LinkedList<View> listChallengesViews = new LinkedList<>();

    private ChallengesFragment challengesFragment;
    private Context context;

    private ControlSocket controlSocket;
    private DataSocket dataSocket;
    private String answer;

    public GetListChallenges(ChallengesFragment challengesFragment, Context context) {
        this.challengesFragment = challengesFragment;
        this.context = context;
    }

    @Override
    protected LinkedList<ChallengeListItem> doInBackground(Object... voids) {

//        try {
//            controlSocket = new ControlSocket("localhost", 12000);
////          1. Salje se zahtjev za listu objekata ChallengeListItem preko kontrolne veze
//            controlSocket.sendRequest(ControlSocket.LIST_CHALLENGES_REQUEST);
////          2. Prima se odgovor od servera preko kontrolne veze
//            answer = controlSocket.recieveAnswer();
////          3. Provjeravamo da li je odgovor dobar
//            if(answer == "good") {
////              4. Otvara se data soket i salje se odobrenje serveru za slanje podataka
//                dataSocket = new DataSocket("localhost", 13000);
//                dataSocket.sendSignal();
////              5. Primamo listu objekata ChallengeListItem od servera
//                listChallenges = dataSocket.recieveListChallenges();
//            } else {
////              listChallenges = null;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        try {
////          zatvaramo kontrolnu vezu i vezu podataka
//            dataSocket.closeObjectDataStreams();
//            controlSocket.closeControlStreams();
//            dataSocket.close();
//            controlSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        TEST OBJEKTI
        ChallengeListItem c1 = new ChallengeListItem("First Challenge");
        ChallengeListItem c2 = new ChallengeListItem("Second challenge");
        ChallengeListItem c3 = new ChallengeListItem("Third challenge");

//      2.Primaju se list Challenges
        listChallenges.add(c1);
        listChallenges.add(c2);
        listChallenges.add(c3);

        return listChallenges;
    }

    @Override
    protected void onPostExecute(LinkedList<ChallengeListItem> challengeListItem) {

        listChallengesViews = Convert.challengeListItemsToChallengeViews(challengeListItem, context);
        challengesFragment.populateUI(listChallengesViews);
    }
}
