package com.mycompany.client;

import java.net.*;
import java.io.*;

public class ChatClient {

    private String hostname;
    private int port;
    private String userName;
    
    /**
     * Create a new chatClient.
     * @param hostname hostname
     * @param port Listening port
     */
    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
    
    /**
     * Starts the chat.
     */
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);

            System.out.println("Conectado al servidor");

            
            // Set the read thread (screen)
            
            new ReadThread(socket, this).start();

            
            // Set the write thread
            
            new WriteThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Servidor no encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            
            // Exception
            
            System.out.println("I/O Error: " + ex.getMessage());
        }

    }
    
    
    // Set the UserName
    
    void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    // Get the UserName
    
    String getUserName() {
        return this.userName;
    }

    public static void main(String[] args) {
        
        
        
        // We define the IP direction and the port
        
        String hostname = "192.168.3.152";
        int port = 3337;

        
        // We launch the client
        
        ChatClient client = new ChatClient(hostname, port);
        client.execute();
    }
}
