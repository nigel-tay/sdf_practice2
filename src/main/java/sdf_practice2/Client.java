package sdf_practice2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 3000);

        OutputStream os;
        BufferedOutputStream bos;
        DataOutputStream dos;
        Console cons = System.console();
        String userInput = "";

        File fileObj = new File("data/myfile.txt");
        FileReader fr = new FileReader(fileObj);
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        os = socket.getOutputStream();
        bos = new BufferedOutputStream(os);
        dos = new DataOutputStream(bos);
        
        try {
            while (!userInput.equals("quit")) {
                userInput = cons.readLine();
                if (userInput.equals("transfer")) {
                    while(null != (line = br.readLine())) {
                        dos.writeUTF(line);
                        dos.flush();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            socket.close();
        }
        finally {
            br.close();
            dos.close();
            bos.close();
            os.close();
            socket.close();
        }
    }
}
