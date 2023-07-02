package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {

    //JLabel l1;
    JButton deposit, withdrowl, ministatement, pinchange, fastcash, balanceenquiry, exit;
    String pinnumber;

    Transactions(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(790, 790, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 790, 790);
        add(image);

        JLabel text = new JLabel("Please Select Your Transaction");
        text.setBounds(175, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(140, 355, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrowl = new JButton("Cash Withdrowl");
        withdrowl.setBounds(300, 355, 150, 30);
        withdrowl.addActionListener(this);
        image.add(withdrowl);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(140, 390, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(300, 390, 150, 30);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Pin Change");
        pinchange.setBounds(140, 425, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenquiry = new JButton("Balance Enquiry");
        balanceenquiry.setBounds(300, 425, 150, 30);
        balanceenquiry.addActionListener(this);
        image.add(balanceenquiry);

        exit = new JButton("Exit");
        exit.setBounds(300, 457, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

//        setSize(900,900);
//        setLocation(300,0);
        setSize(800, 800);
        setLocation(350, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            setVisible(false);
            //new Deposit(pinnumber).setVisible(true);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);
        } else if (ae.getSource() == withdrowl) {
            setVisible(false);
            new Withdrowl(pinnumber).setVisible(true);
        } else if (ae.getSource() == fastcash) {
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);
        }  else if (ae.getSource() == pinchange) {
            setVisible(false);
            new PinChange(pinnumber).setVisible(true);
        } else if (ae.getSource() == balanceenquiry) {
            this.setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);
        }else if (ae.getSource() == ministatement) {

            new MiniStatement(pinnumber).setVisible(true);
        }
//        else if(ae.getSource()==exit){ 
//            System.exit(0);
//        }
    }

    public static void main(String args[]) {
        new Transactions("");
    }
}
