package com.pkg.chat;

import java.io.*;
import java.net.Socket;
import javax.swing.JFileChooser;

public class Sender {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    static String a;
    
    
    Sender(){
        this.a=send();
        test();
    }
    
    public static void test(){
        try(Socket socket = new Socket("localhost",5000)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            
            sendFile(a);
            //sendFile(b);
            
            dataInputStream.close();
            dataInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        
        
    }
    public static String send(){
        
        String a;
        
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
        f.showSaveDialog(null);

        a=String.valueOf(f.getSelectedFile());
        return a;
    }

    private static void sendFile(String path) throws Exception{
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        
        // send file size
        dataOutputStream.writeLong(file.length());  
        // break file into chunks
        byte[] buffer = new byte[4*1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){
            dataOutputStream.write(buffer,0,bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }
}