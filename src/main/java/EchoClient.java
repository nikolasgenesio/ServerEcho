import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        String screenName = args[0];
        String host = args[1];
        int port = 4444;

        // conecta ao servidor e abre os streams
        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.err.println("Connected to " + host + " on port " + port);

        // lê da entrada padrão stdin, envia, escreve resposta

        String s;
        while ((s = stdin.readLine()) != null) {
            // Se o comando for "quit", encerra o cliente
            if (s.equalsIgnoreCase("quit")) {
                break;
            }
            // Comando "echo" para responder com a mensagem
            else if (s.startsWith("echo ")) {
                // envio pelo socket
                out.println("[" + screenName + "]:" + s.replace("echo", ""));
            } else {
                out.println("Unknown command");
            }

            // pega resposta
            System.out.println(in.readLine());
        }

        // encerra os sockets
        System.err.println("Closing connection to " + host);
        out.close();
        in.close();
        socket.close();
    }
}
