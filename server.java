import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     FileInputStream fis = new FileInputStream("path/to/your/file.txt");
                     BufferedInputStream bis = new BufferedInputStream(fis);
                     OutputStream os = socket.getOutputStream()) {

                    System.out.println("Client connected");

                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = bis.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    os.flush();
                    System.out.println("File sent successfully");
                } catch (IOException ex) {
                    System.out.println("Server exception: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }
}