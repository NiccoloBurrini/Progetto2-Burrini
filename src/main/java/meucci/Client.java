package meucci;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    int serverPort;
    String serverAddress;

    Socket client;
    BufferedReader in;
    Scanner stdIn;
    PrintWriter out;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public Socket connect() {

        try {
            client = new Socket(serverAddress, serverPort);

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            stdIn = new Scanner(System.in);

            System.out.println("Connesso al server con IP: " + serverAddress + ":" + serverPort);

        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERRORE");
            System.exit(1);
        }

        return client;
    }

    public void communicate() {

        String userInput;

        try {
            while (true) {
                userInput = stdIn.nextLine();
                out.println(userInput);
                if (userInput.equalsIgnoreCase("BYE")) {
                    break;
                }
                System.out.println("Server: " + in.readLine());
            }
            System.out.println("Connessione al server chiusa");
            in.close();
            out.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}