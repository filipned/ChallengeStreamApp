package com.filip.Challenge.io.sockets;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import model.ChallengeListItem;
import model.ChallengeLiveItem;

/**
 * Created by FILIP on 08-Aug-16.
 */

public class DataSocket extends Socket {

    public static final Boolean START_SENDING = true;

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;


    public DataSocket(String ip, int port) throws IOException {
        super(ip, port);
    }

    public ObjectOutputStream openObjectOutputStream() throws IOException {
        objectOutputStream = new ObjectOutputStream(this.getOutputStream());
        return objectOutputStream;
    }

    public ObjectInputStream openObjectInputStream() throws IOException {
        objectInputStream = new ObjectInputStream(this.getInputStream());
        return objectInputStream;
    }

    public void closeObjectOutputStream() throws IOException {
        objectOutputStream.close();
    }

    public void closeObjectInputStream() throws IOException {
        objectInputStream.close();
    }

    public void sendSignal() throws IOException {
        this.openObjectOutputStream();

        objectOutputStream.writeObject(START_SENDING);
    }

    public boolean recieveAnswer() throws IOException {
        this.openObjectOutputStream();
        this.openObjectInputStream();
        return objectInputStream.readBoolean();
    }

    public LinkedList<ChallengeLiveItem> recieveLiveChallenges() throws IOException, ClassNotFoundException {
        this.openObjectInputStream();
        LinkedList<ChallengeLiveItem> liveChallenges = new LinkedList<>();
        liveChallenges = (LinkedList<ChallengeLiveItem>) objectInputStream.readObject();
        return  liveChallenges;
    }

    public LinkedList<ChallengeListItem> recieveListChallenges() throws IOException, ClassNotFoundException {
        this.openObjectInputStream();
        LinkedList<ChallengeListItem> listChallenges = new LinkedList<>();
        listChallenges = (LinkedList< ChallengeListItem>) objectInputStream.readObject();
        return  listChallenges;
    }

    public void closeObjectDataStreams() throws IOException {
        this.closeObjectOutputStream();
        this.closeObjectInputStream();
    }

    public void sendChallenge(ChallengeListItem challengeListItem) throws IOException {
        if(challengeListItem == null) return;
        Object o = challengeListItem;
        this.openObjectOutputStream();
//        objectOutputStream.writeObject(challengeListItem);
        objectOutputStream.writeObject(o);
    }

    public void sendLiveChallenge(ChallengeLiveItem challengeLiveItem) throws IOException {
        if(challengeLiveItem == null) return;
        this.openObjectOutputStream();
        objectOutputStream.writeObject(challengeLiveItem);
    }
}
