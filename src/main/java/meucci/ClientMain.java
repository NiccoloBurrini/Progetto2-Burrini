package meucci;

public class ClientMain {
    public static void main(String[] args) {

        Client client = new Client("localhost", 6789);
        client.connect();

        client.communicate();
    }
}