import java.io.*;
import java.net.*;

public class EchoServer {

    public static void main(String[] args) throws Exception {
        int port = 4444;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);

        while (true) {
            // espera blocante até alguma requisição de conexão
            Socket clientSocket = serverSocket.accept();
            System.err.println("Accepted connection from client " + clientSocket.getInetAddress().getHostName() +
                    " (" + clientSocket.getInetAddress().getHostAddress() + ")");

            // Cria uma nova thread para lidar com o cliente
            Thread echoClientThread = new ClientHandler(clientSocket);
            echoClientThread.start();
        }
    }
}
