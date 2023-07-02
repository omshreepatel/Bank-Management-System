package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener{

    String pinnumber;
    
    MiniStatement(String pinnumber){
        
        this.pinnumber = pinnumber;
        setTitle("Mini Statement");
        
        setLayout(null);
        
        JLabel mini = new JLabel();
        add(mini);
        
        JLabel bank = new JLabel("Indian Bank");
        bank.setBounds(150, 20, 300, 20);
        add(bank);
        
        JLabel card = new JLabel();
        card.setBounds(20, 60, 300, 20);
        add(card);
        
        JLabel balance = new JLabel();
        balance.setBounds(20, 590, 300, 20);
        add(balance);
        
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pinnumber = '3649'");
            while(rs.next()){
                card.setText("Card Number:    " + rs.getString("cardno").substring(0, 4) + "XXXXXXXX" + rs.getString("cardno").substring(12));
            }
        }catch(Exception e){
        System.out.println(e);
        }
        
        try{
            int bal = 0;
            Conn c1  = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pinnumber = '"+pinnumber+"'");
            while(rs.next()){
                mini.setText(mini.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if(rs.getString("type").equals("Deposit")){
                    bal += Integer.parseInt(rs.getString("amount"));
                }else{
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your total Balance is Rs. "+bal);
        }catch(Exception e){
            System.out.println(e);
        }
        
        mini.setBounds(20, 140, 400, 200);
        
        setSize(500, 700);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }

    public static void main(String args[]) {
        new MiniStatement("").setVisible(true);
    }
}
