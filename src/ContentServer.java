import java.io.*;
import java.net.*;

public class ContentServer {
    public static void main(String[] args) {
        final int PORT = 8080;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // Output stream to send data to the client
            OutputStream out = clientSocket.getOutputStream();

            File file = new File("/Users/deBauch_Casanova/Downloads/Silicon.Valley.S01E01.HDTV.x264-KILLERS.mp4");
            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
