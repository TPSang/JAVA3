/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author TuanDuc
 */
public class Bai1_Server {
    public static void main(String[] args) {
        try {
            ServerSocket svSocket = new ServerSocket(8888);
            System.out.println("Server is Connecting....");
            Socket socket = svSocket.accept();
            System.out.println("Server is Connected");
            DataOutputStream opStrem = new DataOutputStream(socket.getOutputStream());
            DataInputStream ipStrem = new DataInputStream(socket.getInputStream());
            while(true)
            {
                double a = ipStrem.readDouble();
                double b = ipStrem.readDouble();
                System.out.println("2 số nhận được từ Client là: " + a + " và " + b);
                double tong = a + b;
                opStrem.writeDouble(tong);
                opStrem.flush();
                System.out.println("Tổng 2 số là: " + tong);
            }
        } catch (Exception ex) 
        {
             ex.printStackTrace();
        }
    }
}
