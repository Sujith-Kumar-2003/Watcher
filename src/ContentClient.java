import java.io.*;
import java.net.*;

public class ContentClient {
    public static void main(String[] args) {
        final String SERVER_IP = "192.168.0.181"; // Replace with your server's IP address
        final int SERVER_PORT = 8080; // Use the same port as the server

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            InputStream in = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("received_content.mp4");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            fileOutputStream.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
