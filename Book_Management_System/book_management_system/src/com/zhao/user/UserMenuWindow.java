package com.zhao.user;

import com.zhao.Information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zhao.Redirect.*;
import static java.awt.Color.WHITE;

public class UserMenuWindow extends JFrame implements ActionListener {
    private JButton btn_search;
    private JButton btn_borrow;
    private JButton btn_return;
    private JButton btn_order;
    private JButton btn_back;
    private JPanel jp;

    public UserMenuWindow(){
        jp = (JPanel)this.getContentPane();
        setTitle("用户：" + Information.user_no);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 150, 800, 450);
        setResizable(false);
        jp.setBackground(WHITE);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        //检索按钮
        btn_search = new JButton("图书检索");
        btn_search.setBackground(Color.LIGHT_GRAY);
        btn_search.setFont(new Font("宋体", Font.BOLD, 25));
        btn_search.setBounds(35, 100, 150, 100);
        jp.add(btn_search);
        btn_search.addActionListener(this);

        //借阅按钮
        btn_borrow = new JButton("图书借阅");
        btn_borrow.setBackground(Color.LIGHT_GRAY);
        btn_borrow.setFont(new Font("宋体", Font.BOLD, 25));
        btn_borrow.setBounds(230,100,150,100);
        jp.add(btn_borrow);
        btn_borrow.addActionListener(this);

        //归还按钮
        btn_return = new JButton("图书归还");
        btn_return.setBackground(Color.LIGHT_GRAY);
        btn_return.setFont(new Font("宋体", Font.BOLD, 25));
        btn_return.setBounds(420, 100, 150, 100);
        jp.add(btn_return);
        btn_return.addActionListener(this);

        //预约按钮
        btn_order = new JButton("图书预约");
        btn_order.setBackground(Color.LIGHT_GRAY);
        btn_order.setFont(new Font("宋体", Font.BOLD, 25));
        btn_order.setBounds(610,100,150,100);
        jp.add(btn_order);
        btn_order.addActionListener(this);

        //返回按钮
        btn_back = new JButton(">>注销<<");
        btn_back.setBackground(Color.LIGHT_GRAY);
        btn_back.setFont(new Font("宋体", Font.BOLD, 25));
        btn_back.setBounds(305,300,190,80);
        jp.add(btn_back);
        btn_back.addActionListener(this);

    }

    public void actionPerformed(ActionEvent event){

        if(event.getSource() == btn_search){
            toBookSearch();
        }
        else if(event.getSource() == btn_borrow){
            toBookBorrow();
        }
        else if (event.getSource() == btn_return){
            toBookReturn();
        }
        else if(event.getSource() == btn_order){
            toBookOrder();
        }
        else{
            toLoginWindow(this);
        }
    }

}
