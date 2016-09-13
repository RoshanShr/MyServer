/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.myserver;

import com.leapfrog.myserver.listener.Client;
import com.leapfrog.myserver.listener.ClientListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sagar
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 9000;
        try {
            ServerSocket server = new ServerSocket(port);
            List<Client> clients = new ArrayList<>();
            System.out.println("Server is running at " + port);
            while (true) {
                Socket client = server.accept();
                System.out.println("Conection Request from " + client.getInetAddress().getHostAddress());
                ClientListener listener = new ClientListener(client, clients);
                listener.start();
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

}
