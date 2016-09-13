/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.myserver.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Sagar
 */
public class ClientListener extends Thread {

    private Socket socket;
    private List<Client> clients;

    public ClientListener(Socket socket, List<Client> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("Welcome to the server");
            ps.println("Enter your name");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name = reader.readLine();
            Client client = new Client(name, socket);
            clients.add(client);

            ps.println("Hello " + name);
            while (true) {
                ps.println("Enter Message: ");
                String line = reader.readLine();
                broadcastMessage(name + "says> " + line);

            }
        } catch (IOException ioe) {

        }

    }

    private void broadcastMessage(String msg) throws IOException {
        for (Client c : clients) {
            PrintStream ps = new PrintStream(c.getSocket().getOutputStream());
            ps.println(msg);
        }
    }
}
