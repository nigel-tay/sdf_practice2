package sdf_practice2;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(3000);
        Socket socket = server.accept();

        InputStream is;
        BufferedInputStream bis;
        DataInputStream dis;
        String command = "";
        is = socket.getInputStream();
        bis = new BufferedInputStream(is);
        dis = new DataInputStream(bis);
        // write conditional to see if file and directory exists
            // if not create the file and directory
        // convert data to stream
        // filter out dirty data
        // write incoming data to file
                
        try {

            while(!(command.equals("quit"))) {
                command = dis.readUTF();
                System.out.println(command);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            dis.close();
            bis.close();
            is.close();
            socket.close();
            server.close();
        }
        finally {
            dis.close();
            bis.close();
            is.close();
            socket.close();
            server.close();
        }
    }
}
