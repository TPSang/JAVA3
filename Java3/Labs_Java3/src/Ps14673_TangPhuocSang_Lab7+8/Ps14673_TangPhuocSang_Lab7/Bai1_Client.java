/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author TuanDuc
 */
public class Bai1_Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client is Connecting....");
            Socket socket = new Socket("localhost", 8888);
            System.out.println("Client is Connected");
            DataOutputStream opStrem = new DataOutputStream(socket.getOutputStream());
            DataInputStream ipStrem = new DataInputStream(socket.getInputStream());
            while(true)
            {
                System.out.print("Nhập số thứ 1: ");
                opStrem.writeDouble(new Scanner(System.in).nextDouble());
                opStrem.flush();
                System.out.print("Nhập số thứ 2: ");
                opStrem.writeDouble(new Scanner(System.in).nextDouble());
                opStrem.flush();
                //System.out.println("Tồng của 2 số: " + ipStrem.readDouble());
                System.out.print("Tiếp tục? (y/n?): ");
                String traloi = new Scanner(System.in).nextLine();
                if (traloi.equalsIgnoreCase("n"))
                    break;
            }
        } catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}
