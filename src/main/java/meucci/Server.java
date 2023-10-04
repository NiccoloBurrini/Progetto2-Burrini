package meucci;

import java.io.*;
import java.net.*;

public class Server {

    int port;
    ServerSocket server;
    Socket client;
    BufferedReader stdIn, in;
    PrintWriter out;

    public Server(int port) {

        this.port = port;
    }

    public void connect() {

        try {
            server = new ServerSocket(port);

            client = server.accept();

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            System.out.println("Client connesso sulla porta: " + port);

        } catch (IOException e) {
        }
    }

    public void communicate() {

        try {
            String stringaRicevuta = in.readLine();

            if (stringaRicevuta.equalsIgnoreCase("BYE") || stringaRicevuta == null) {
                out.println("Connessione al server chiusa");
                System.out.println("Connessione chiusa");
                client.close();
                in.close();
                out.close();

            }

            System.out.println("Client: " + stringaRicevuta);
            out.println(stringaRicevuta.toUpperCase());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERRORE");
            System.exit(1);
        }
    }
}
