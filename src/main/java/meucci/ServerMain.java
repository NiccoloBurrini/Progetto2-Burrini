package meucci;

public class ServerMain {
    public static void main(String[] args) {

        Server server = new Server(6789);
        server.connect();

        while (true)
            server.communicate();
    }

}