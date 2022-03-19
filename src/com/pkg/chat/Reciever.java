
package com.pkg.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Reciever {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    
    static String a;
    static int extensions;
    
    Reciever(){
        this.a=recieve();
        System.out.printf("enter ur Files extension type:\n1-> img \t 2-> txt \t3-> pdf\n");
        Scanner in = new Scanner(System.in);
        int type = in.nextInt();
        this.extensions=type; 
        test();
    }
    
    public static void test(){
        
        try(ServerSocket serverSocket = new ServerSocket(5000)){
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket+" connected.");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            
            
            switch(extensions){
                case 1:
                    receiveFile(""+a+"\\a.jpg");
                    break;
                case 2:
                    receiveFile(""+a+"\\a.txt");
                    break;
                case 3:
                    receiveFile(""+a+"\\a.pdf");
                    break;
                default:
                     receiveFile(""+a+"\\a.txt"); 
            }
            //receiveFile(""+a+"\\a.jpg");
            //receiveFile(""+b+"\\b.jpg");

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        
    }
    
    public static String recieve(){
        
        String a;
        
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);

        a=String.valueOf(f.getSelectedFile());
        
        return a;
    }

    private static void receiveFile(String fileName) throws Exception{
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        
        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4*1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }
}