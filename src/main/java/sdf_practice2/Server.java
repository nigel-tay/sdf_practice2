package sdf_practice2;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
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

        String line = "";
        FileWriter fw;
        BufferedWriter bw;
        
        try {
            // write conditional to see if file and directory exists
            File serverDir = new File("serverdir");
            if (serverDir.exists()) {
                System.out.println("Transfering data to directory: " + serverDir + ".\n");
            }
            else {
                // if not create the file and directory
                serverDir.mkdir();
                System.out.printf("Directory '%s' created, beginning data transfer.\n", serverDir);
            }
            
            File serverFile = new File("serverfile.txt");
            if (serverFile.exists()) {
                System.out.println("Transfering data to file: " + serverFile + ".\n");
            }
            else {
                serverFile.createNewFile();
                System.out.printf("File '%s' created, beginning data transfer.\n", serverFile);
            }

            fw = new FileWriter("serverdir" + File.separator + serverFile.getName());
            bw = new BufferedWriter(fw);

            while(!((line = dis.readUTF()).equals(null))) {

                bw.write(line + "\n");
                bw.flush();
                // convert data to stream
                // filter out dirty data
                // write incoming data to file
            }
        }
        catch(Exception e) {
            System.out.println("Exiting not so gracefully...");
            System.out.println(e);
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
