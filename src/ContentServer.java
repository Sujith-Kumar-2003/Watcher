import java.io.*;
import java.net.*;

public class ContentServer {
    public static void main(String[] args) {
        final int PORT = 8080; // Choose a port for your server

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Create a thread to handle client requests
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

            // Replace "your_mp4_file_path" with the actual path of your MP4 file
            File file = new File("your_mp4_file_path");
            FileInputStream fileInputStream = new FileInputStream(file);

            // Send content to the client
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            // Close streams and socket
            fileInputStream.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
