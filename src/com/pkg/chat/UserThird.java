
package com.pkg.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;

public class UserThird extends JFrame implements ActionListener, Runnable{
    
    JPanel p1;
    JPanel p2;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    static JTextField[] msg=new JTextField[99];    
    BufferedWriter writer;
    BufferedReader reader;
    static int j=135;
    
    static int count=0; 
    
    UserThird(){
        
       initcom();
       try{
           
           Socket socketClient = new Socket("localhost", 8080);
           writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
           reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
           
           
       }catch(Exception e){}
    
    }
    
    private void initcom(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(98,79,158));
        p1.setBounds(0, 0, 450, 105);
        add(p1);
        
        ImageIcon bIco = new ImageIcon(ClassLoader.getSystemResource("com/pkg/chat/back.png"));
        Image bIcon = bIco.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon baIcon = new ImageIcon(bIcon);
        JLabel backIcon = new JLabel(baIcon);
        backIcon.setBounds(25, 35, 32, 32);
        p1.add(backIcon);
        backIcon.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("com/pkg/chat/work.png"));
        Image i2 = i1.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(202, 10, 48, 48);
        p1.add(l1);
        
        JLabel l3 = new JLabel("Flat Earthers' Society");
        l3.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(135, 57, 200, 18);
        p1.add(l3);
        
        JLabel l4 = new JLabel("Faha, Tanjum, Nujhat, Mabia");
        l4.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        l4.setForeground(Color.LIGHT_GRAY);
        l4.setBounds(150, 75, 160, 20);
        p1.add(l4);
        
        ImageIcon moIco = new ImageIcon(ClassLoader.getSystemResource("com/pkg/chat/more.png"));
        Image moIcon = moIco.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon morIcon = new ImageIcon(moIcon);
        JLabel moreIcon = new JLabel(morIcon);
        moreIcon.setBounds(400, 37, 32, 32);
        p1.add(moreIcon);
        moreIcon.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        t1 = new JTextField();
        t1.setBounds(5, 655, 300, 40);
        t1.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        t1.setBackground(Color.LIGHT_GRAY);
        t1.setForeground(Color.BLACK);
        t1.setBorder(null);
        add(t1);
        
        ImageIcon seIco = new ImageIcon(ClassLoader.getSystemResource("com/pkg/chat/send.png"));
        Image seIcon = seIco.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon sendIco = new ImageIcon(seIcon);
       
        
        b1 = new JButton();
        b1.setBounds(310, 660, 32, 32);
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.WHITE);
        b1.setIcon(sendIco);
        b1.setBorder(null);
        b1.addActionListener(this);
        add(b1);
        
        ImageIcon upIco = new ImageIcon(ClassLoader.getSystemResource("com/pkg/chat/upload.png"));
        Image upIcon = upIco.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon updIcon = new ImageIcon(upIcon);
        JLabel upldIcon = new JLabel(updIcon);
        upldIcon.setBounds(360, 660, 32, 32);
        add(upldIcon);
        upldIcon.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                setVisible(false);
                Sender se=new Sender();
            }
        });
        
        ImageIcon doIco = new ImageIcon(ClassLoader.getSystemResource("com/pkg/chat/download.png"));
        Image doIcon = doIco.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon dowIcon = new ImageIcon(doIcon);
        JLabel downIcon = new JLabel(dowIcon);
        downIcon.setBounds(408, 660, 32, 32);
        add(downIcon);
        downIcon.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                setVisible(false);
                Reciever re= new Reciever();
                
            }
        });
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(455, 730);
        setResizable(false);
        setLocation(100, 150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String str = "FAHA - "+t1.getText();
        try{
            writer.write(str);
            writer.write("\r\n");
            writer.flush();
        }catch(Exception e){}
        t1.setText("");

    }

    @Override
    public void run() {
        
        try{
            String msg = "";
            String b="";
            char[] a= new char[99];
            int j=0;
            while((msg = reader.readLine()) != null){
                //a1.append(msg + "\n");
                if(msg.charAt(0)=='F'){
                    for(int i=7; i<msg.length(); i++){
                        b+=""+msg.charAt(i);
                    }
                    b+=" ";
                    this.msg[count]=new JTextField();
                    this.msg[count].setBounds(192, this.j, 250, 40);
                    this.msg[count].setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
                    this.msg[count].setBackground(new Color(62, 70, 177));
                    this.msg[count].setForeground(Color.WHITE);
                    this.msg[count].setBorder(null);
                    this.msg[count].setEditable(false);
                    add(this.msg[count]);
                    this.j+=50;
                    this.msg[count].setHorizontalAlignment(SwingConstants.RIGHT);
                    this.msg[count].setText(b);
                    this.count++;
                }else{
                    this.msg[count]=new JTextField();
                    this.msg[count].setBounds(5, this.j, 250, 40);
                    this.msg[count].setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
                    this.msg[count].setBackground(new Color(165, 108, 62));
                    this.msg[count].setForeground(Color.WHITE);
                    this.msg[count].setBorder(null);
                    this.msg[count].setEditable(false);
                    add(this.msg[count]);
                    this.j+=50;
                    this.msg[count].setText(msg);
                    this.count++;
                }
                if(this.count==10){
                    
                    Thread.sleep(2000);
                    
                    for(int i=0;i<10;i++){
                       this.msg[i].setVisible(false);
                    }
                    this.count=0;
                    this.j=135;
                    
                }
                b="";
            }
        }catch(Exception e){}
        
        
    }
    
    public static void main(String[] args){
        UserThird one = new UserThird();
        Thread t1 = new Thread(one);
        t1.start();
    }
    
}
