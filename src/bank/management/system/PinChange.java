package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener{

    JButton change,back;
    JPasswordField pin, repin;
    JLabel text, pintext, repintext;
    String pinnumber;
    PinChange(String pinnumber){
        this.pinnumber = pinnumber;
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(790, 790, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 790, 790);
        add(image);
        
        text = new JLabel("Change Your Pin");
        text.setBounds(225, 250, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        pintext = new JLabel("New Pin:");
        pintext.setBounds(155, 280, 700, 35);
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 16));
        pin.setBounds(310, 285, 140, 25);
        image.add(pin);
        
        repintext = new JLabel("Re-Enter New Pin:");
        repintext.setBounds(155, 330, 220, 25);
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(repintext);
        
        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 16));
        repin.setBounds(310, 330, 140, 25);
        image.add(repin);
        
        change = new JButton("CHANGE");
        change.setBounds(315, 415,140,30);
        change.addActionListener(this);
        image.add(change);
        
        back = new JButton("BACK");
        back.setBounds(315, 450,140,30);
        back.addActionListener(this);
        image.add(back);
        
        
        setSize(800, 800);
        setLocation(350,  0);
        setUndecorated(true);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
         if(ae.getSource()==change){
        
        try{        
            String npin = pin.getText();
            String rpin = repin.getText();
            
            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }
            if(npin.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter  new PIN");
                return;
            }
            if(rpin.equals("")){
                JOptionPane.showMessageDialog(null, "Please re-enter  new PIN");
                return;
            }
            
            Conn conn = new Conn();
                String query1 = "update bank set pinnumber = '"+rpin+"' where pinnumber = '"+pinnumber+"' ";
                String query2 = "update login set pinnumber = '"+rpin+"' where pinnumber = '"+pinnumber+"' ";
                String query3 = "update signupthree set pinnumber = '"+rpin+"' where pinnumber = '"+pinnumber+"' ";
                
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);
        }
        catch(Exception e){
            System.out.println(e);
        }
    } else{
             setVisible(false);
             new Transactions(pinnumber).setVisible(true);
         }
    }

    public static void main(String args[]) {

        new PinChange("").setVisible(true);
    }
}
