package com.filip.Challenge.io.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by FILIP on 08-Aug-16.
 */

public class ControlSocket extends Socket {

    public final static String ADD_CHALLENGE_REQUEST = "add_challenge_request";
    public final static String LIST_CHALLENGES_REQUEST = "list_challenges_request";
    public final static String ADD_LIVE_CHALLENGE_REQUEST = "add_live_challenge_request";
    public final static String LIVE_CHALLENGES_REQUEST = "live_challenges_request";

//    kada se ugasi full screen camera activity
    public final static String REMOVE_LIVE_CHALLENGE_REQUEST = "remove_live_challenge";

    public final static String WATCH_CHALLENGE_REQUEST = "watch_challenge";


    private PrintStream outputStream;
    private BufferedReader inputStream;
//    private ObjectOutputStream objectOutputStream;
//    private ObjectInputStream objectInputStream;

    public ControlSocket(String serverIP, int port) throws IOException {
        super(serverIP, port);
    }

    public PrintStream openOutputStream() throws IOException {
        outputStream = new PrintStream(this.getOutputStream());
        return outputStream;
    }

    public BufferedReader openInputStream() throws IOException {
        inputStream = new BufferedReader(new InputStreamReader(this.getInputStream()));
        return inputStream;
    }



//    public ObjectOutputStream openObjectOutputStream() throws IOException {
//        objectOutputStream = new ObjectOutputStream(this.getOutputStream());
//        return objectOutputStream;
//    }
//
//    public ObjectInputStream openObjectInputStream() throws IOException {
//        objectInputStream = new ObjectInputStream(this.getInputStream());
//        return objectInputStream;
//    }
//
//    public void closeObjectInputStream() throws IOException {
//        objectInputStream.close();
//    }
//
//    public void closeObjectOutputStream() throws IOException {
//        objectOutputStream.close();
//    }

    public void closeOutputStream() throws IOException {
        outputStream.close();
    }

    public void closeInputStream() throws IOException {
        inputStream.close();
    }

    public void sendRequest(String request) throws IOException {
        this.openOutputStream();
        this.openInputStream();
        outputStream.println(request);
    }

    public String recieveAnswer() throws IOException {
        return inputStream.readLine();
    }

//    srediti da se salje i request
    public void sendChallengeRequest(String request) throws IOException {
        this.openOutputStream();
        this.openInputStream();

        outputStream.println(request);
    }


    public void sendLiveChallengeRequest(String request) throws IOException {
        this.openOutputStream();
        this.openInputStream();
        outputStream.println(request);
    }

    public void closeControlStreams() throws IOException {
        this.closeInputStream();
        this.closeOutputStream();
    }

}
