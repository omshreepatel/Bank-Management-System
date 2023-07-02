package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {
    
   JTextField amount;
   JButton deposit, back;
    JLabel image,text;
    String pinnumber;
    Deposit(String pinnumber){
        this.pinnumber = pinnumber;
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(790, 790, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        image.setBounds(0, 0, 790, 790);
        add(image);
        
        text = new JLabel("Enter the amount you want to deosit");
        text.setBounds(155, 250, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 25));
        amount.setBounds(150, 300, 295, 20);
        image.add(amount);
        
        deposit = new JButton("Deposit");
        deposit.setBounds(300, 400, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);
        
        back = new JButton("Back");
        back.setBounds(300, 440, 150, 30);
        back.addActionListener(this);
        image.add(back);
        
        
        setSize(800, 800);
        setLocation(350,  0);
        //setUndecorated(true);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== deposit){ 
            //setVisible(false);
            try{
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter the amount to you want to Deposit");
            } else{
                
                    Conn conn = new Conn();
                    String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Deposit', '"+number+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs. "+number+" Deposited Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                
                }
            }catch(Exception e){
            System.out.println(e);
        }
        }else if(ae.getSource()== back){ 
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
        
    public static void main(String[] args){
        new Deposit("");
    }
}
