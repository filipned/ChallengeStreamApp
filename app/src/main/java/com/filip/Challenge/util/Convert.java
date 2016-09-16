package com.filip.Challenge.util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.filip.Challenge.MainActivity;

import java.util.LinkedList;

import model.ChallengeListItem;
import model.ChallengeLiveItem;

/**
 * Created by FILIP on 31-Jul-16.
 */

public class Convert {

//  Metoda konvertuje objekat ChallengeListItem u View koji ce se prikazivati na ekranu

    public static View challengeListItemToChallengeView (ChallengeListItem listItem, Context context) {

        TextView challengeName = new TextView(context);
        challengeName.setLayoutParams(new LinearLayout.LayoutParams(0, 70, 7));
        challengeName.setPadding(8, 8, 8, 8);
        challengeName.setText(listItem.getChallengeName());

        TextView numOfSubs = new TextView(context);
        numOfSubs.setLayoutParams(new LinearLayout.LayoutParams(0, 70, 1));
        numOfSubs.setPadding(8, 8, 8, 8);
        numOfSubs.setText(listItem.getNumOfSubscribers() + "");

        ImageView star = new ImageView(context);
        star.setLayoutParams(new LinearLayout.LayoutParams(0, 70, 1));
        star.setPadding(8, 8, 8, 8);
        star.setBackgroundColor(Color.BLUE);

        LinearLayout listItemView = new LinearLayout(context);
        listItemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80));
        listItemView.setPadding(16, 8, 16, 8);
        listItemView.setOrientation(LinearLayout.HORIZONTAL);

        listItemView.addView(challengeName);
        listItemView.addView(numOfSubs);
        listItemView.addView(star);

        listItemView.setClickable(true);

        listItemView.setId(View.generateViewId());

        View clickableView = listItemView.findViewById(listItemView.getId());
        clickableView.setOnClickListener(new View.OnClickListener() {
//            u mainActiivty napraviti metodu koja upravlja procesom slanja svih podataka serveru i proslijediti joj potrbne param.
//              AsyncTasku
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.openStreamDialog(view, context);
            }
        });
//        ??
//        listItemView.setId(View.generateViewId());
////
//        View clickableView = listItemView.findViewById(listItemView.getId());
//        clickableView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                Dialog streamDialog;
//
//                Button cancel = (Button) streamDialog.findViewById(R.id.streamDialogCancel);
//                Button streamNow = (Button) streamDialog.findViewById(R.id.streamDialogStreamNow);
//
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        streamDialog.cancel();
//                    }
//                });
//
//                streamNow.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String answer;
//                        User user = MainActivity.user;
//                        DataSocket dataSocket;
//                        ChallengeLiveItem challengeLiveItem = new ChallengeLiveItem(user, listItem.getChallengeName());
//                        try {
//                            ControlSocket controlSocket = new ControlSocket("localhost", 12000);
//                            1. Saljemo obavjestenje o namjeri slanja ChallengeLiveItemObjekta
//                            controlSocket.sendLiveChallengeRequest(ControlSocket.ADD_LIVE_CHALLENGE_REQUEST);
////                            2. Primamo odgovor od servera
//                            answer = controlSocket.recieveAnswer();
//                            if (answer == "good") {
//                                dataSocket = new DataSocket("localhost", 13000);
////                                3. Saljemo ChallengeLiveItem objekat
//                                dataSocket.sendLiveChallenge(challengeLiveItem);
////                                4. Provjeravamo da li je server dobio dobar objekat
//                                if(controlSocket.recieveAnswer() == "good") {
////                                    otpoceti strimovanje
//                                }
//                            }
//
////                            srediti da se veze zatvore kada treba
//                            controlSocket.closeObjectOutputStream();
//                            controlSocket.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//            }
//        });

        return listItemView;
    }


//    Metoda konvertuje objekat ChallengeLiveItem u View koji ce se prikazivati na ekranu
    public static View challengeLiveItemToChallengeView (ChallengeLiveItem liveItem, Context context) {

//        ImageView asterix = liveItem.getUser().getAsterix();
        ImageView asterix = new ImageView(context);
        asterix.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
        asterix.setPadding(8, 8, 16, 8);
        asterix.setBackgroundColor(Color.BLUE);

        TextView challengeName = new TextView(context);
        challengeName.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , 0, 1));
        challengeName.setPadding(0, 8, 8, 8);
        challengeName.setText(liveItem.getChallengeName());
        challengeName.setGravity(View.TEXT_ALIGNMENT_CENTER);

        TextView numOfViews = new TextView(context);
        numOfViews.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        numOfViews.setPadding(0, 8, 8, 8);
        numOfViews.setText(liveItem.getNumOfViewers() + " viewers");
        numOfViews.setGravity(View.TEXT_ALIGNMENT_CENTER);

        LinearLayout liveItemView = new LinearLayout(context);
        liveItemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 160));
        liveItemView.setPadding(16, 8, 16, 8);
        liveItemView.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout liveItemViewText = new LinearLayout(context);
        liveItemViewText.setOrientation(LinearLayout.VERTICAL);
        liveItemViewText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        liveItemView.addView(asterix);
        liveItemViewText.addView(challengeName);
        liveItemViewText.addView(numOfViews);

        liveItemView.addView(liveItemViewText);
        liveItemView.setClickable(true);

//        GradientDrawable border = new GradientDrawable();
//        border.setColor(0xFFFFFFFF); //white background
//        border.setStroke(1, 0xFF000000); //black bo
//
//        liveItemView.setBackground(border);

        return liveItemView;
    }

//    Metoda konvertuje listu objekata ChallengeListItem u listu View-ova koji ce se prikazivati na ekranu u tabu "Challenges"
    public static LinkedList<View> challengeListItemsToChallengeViews(LinkedList<ChallengeListItem> challengeListItems, Context context) {
        LinkedList<View> challengeListViews = new LinkedList<View>();
        View listView;

        for (int i = 0; i < challengeListItems.size(); i++ ) {
            listView = challengeListItemToChallengeView(challengeListItems.get(i), context);
            challengeListViews.add(listView);
        }

        return challengeListViews;
    }

//    Metoda konvertuje listu objekata ChallengeLiveItem u listu View-ova koji ce se prikazivati na ekranu u tabu "Now"
    public static LinkedList<View> challengeLiveItemsToChallengeViews(LinkedList<ChallengeLiveItem> challengeLiveItems, Context context) {
        LinkedList<View> challengeLiveViews = new LinkedList<View>();
        View liveView;

        for (int i = 0; i < challengeLiveItems.size(); i++ ) {
            liveView = challengeLiveItemToChallengeView(challengeLiveItems.get(i), context);
            challengeLiveViews.add(liveView);
        }

        return challengeLiveViews;
    }
}
