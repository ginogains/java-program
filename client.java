import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             InputStream is = socket.getInputStream();
             FileOutputStream fos = new FileOutputStream("path/to/save/file.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            System.out.println("File received successfully");
        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
        }
    }
}