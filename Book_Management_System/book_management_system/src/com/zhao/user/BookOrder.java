package com.zhao.user;

import com.zhao.Information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zhao.DBHandle.bookOrder;
import static com.zhao.DBHandle.borrowBook;
import static com.zhao.DBHandle.isExistBook;

public class BookOrder extends JFrame implements ActionListener {

    private JLabel lbl_order;
    private JTextField txt_order;
    private JButton btn_order;
    private JPanel jp;

    public BookOrder(){

        jp = (JPanel)getContentPane();
        setTitle("图书预约");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,200,550,350);
        setResizable(false);
        jp.setBackground(Color.lightGray);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        lbl_order = new JLabel("预约书名：");
        lbl_order.setFont(new Font("宋体", Font.BOLD, 25));
        lbl_order.setBounds(20, 100, 150, 30);
        jp.add(lbl_order);

        txt_order = new JTextField();
        txt_order.setFont(new Font("宋体", Font.BOLD, 25));
        txt_order.setBounds(170, 100, 230, 30);
        jp.add(txt_order);

        btn_order = new JButton("预约");
        btn_order.setFont(new Font("宋体", Font.BOLD, 25));
        btn_order.setBounds(410, 100, 100, 30);
        jp.add(btn_order);
        btn_order.addActionListener(this);

    }

    public void actionPerformed (ActionEvent event){
        String book_name = txt_order.getText();
        if(book_name.equals("")){
            JOptionPane.showMessageDialog(this, "编辑框为空！");
        }
        else{
            if(bookOrder(book_name, Information.user_no)){
                JOptionPane.showMessageDialog(this, "预约成功！");
            }
            else{
                JOptionPane.showMessageDialog(this, "预约失败！");
            }

        }

    }
}
