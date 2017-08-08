/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.websocket;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Antonella
 */
@ServerEndpoint(value="/whiteboardendpoint", encoders = {FigureEncoder.class}, decoders = {FigureDecoder.class})
public class MyWhiteboard {
    
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnMessage
public void broadcastSnapshot(ByteBuffer data, Session session) throws IOException {
    System.out.println("broadcastBinary: " + data);
    for (Session peer : peers) {
        if (!peer.equals(session)) {
            peer.getBasicRemote().sendBinary(data);
        }
    }
}
    
    @OnOpen
    public void onOpen (Session peer) {
        System.out.println(peer.toString());
        peers.add(peer);
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
    }
}
