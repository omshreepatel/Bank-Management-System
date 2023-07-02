package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    //JLabel l1;
    JButton deposit, withdrowl, ministatement, pinchange, fastcash, balanceenquiry, exit;
    String pinnumber;
    
    FastCash(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(790, 790, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 790, 790);
        add(image);
        
        JLabel text = new JLabel("Select Wthdrowl Amount");
        text.setBounds(190, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        deposit = new JButton("Rs. 100");
        deposit.setBounds(140, 355, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);
        
        withdrowl = new JButton("Rs. 500");
        withdrowl.setBounds(300, 355, 150, 30);
        withdrowl.addActionListener(this);
        image.add(withdrowl);
        
        fastcash = new JButton("Rs. 1000");
        fastcash.setBounds(140, 390, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);
        
        ministatement = new JButton("Rs. 2000");
        ministatement.setBounds(300, 390, 150, 30);
        ministatement.addActionListener(this);
        image.add(ministatement);
        
        pinchange = new JButton("Rs. 5000");
        pinchange.setBounds(140, 425, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);
        
        balanceenquiry = new JButton("Rs. 10000");
        balanceenquiry.setBounds(300, 425, 150, 30);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);
        
        exit = new JButton("BACK");
        exit.setBounds(300, 457, 150, 30);
        exit.addActionListener(this);
        image.add(exit);
        
        
        
//        setSize(900,900);
//        setLocation(300,0);
        
        setSize(800, 800);
        setLocation(350,  0);
        setUndecorated(true);
        setVisible(true);
         
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== exit){ 
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
       } else {
            String amount = ((JButton)ae.getSource()).getText().substring(4);
            Conn c = new Conn();
            try{
                ResultSet rs = c.s.executeQuery("select * from bank where pinnumber = '"+pinnumber+"'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
                
                if (ae.getSource() != exit && balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insuffient Balance");
                return;
            }
                
                if (ae.getSource() == exit) {
                this.setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }else{
                Date date = new Date();
                c.s.executeUpdate("insert into bank values('"+pinnumber+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
                    
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }
                
            }catch(Exception e){
                System.out.println(e);
            }
    }
    
}
    
    public static void main(String args[]) {
        new FastCash("");
    }
}
