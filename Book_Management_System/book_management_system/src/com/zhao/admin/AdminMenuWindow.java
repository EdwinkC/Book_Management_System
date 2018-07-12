package com.zhao.admin;

import com.zhao.Information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zhao.Redirect.*;
import static java.awt.Color.WHITE;

public class AdminMenuWindow extends JFrame implements ActionListener {
    private JButton btn_in;
    private JButton btn_out;
    private JButton btn_update;
    private JButton btn_order_management;
    private JButton btn_user_management;
    private JButton btn_back;
    private JPanel jp;

    public AdminMenuWindow(){
        jp = (JPanel)this.getContentPane();
        setTitle("用户(管理员)：" + Information.user_no);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 150, 990, 450);
        setResizable(false);
        jp.setBackground(WHITE);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        //入库按钮
        btn_in = new JButton("图书入库");
        btn_in.setBackground(Color.LIGHT_GRAY);
        btn_in.setFont(new Font("宋体", Font.BOLD, 25));
        btn_in.setBounds(35, 100, 150, 100);
        jp.add(btn_in);
        btn_in.addActionListener(this);

        //出库按钮
        btn_out = new JButton("图书出库");
        btn_out.setBackground(Color.LIGHT_GRAY);
        btn_out.setFont(new Font("宋体", Font.BOLD, 25));
        btn_out.setBounds(230,100,150,100);
        jp.add(btn_out);
        btn_out.addActionListener(this);

        //更新按钮
        btn_update = new JButton("图书更新");
        btn_update.setBackground(Color.LIGHT_GRAY);
        btn_update.setFont(new Font("宋体", Font.BOLD, 25));
        btn_update.setBounds(420, 100, 150, 100);
        jp.add(btn_update);
        btn_update.addActionListener(this);

        //预约管理按钮
        btn_order_management = new JButton("预约管理");
        btn_order_management.setBackground(Color.LIGHT_GRAY);
        btn_order_management.setFont(new Font("宋体", Font.BOLD, 25));
        btn_order_management.setBounds(610,100,150,100);
        jp.add(btn_order_management);
        btn_order_management.addActionListener(this);

        //用户管理按钮
        btn_user_management = new JButton("用户管理");
        btn_user_management.setBackground(Color.LIGHT_GRAY);
        btn_user_management.setFont(new Font("宋体", Font.BOLD, 25));
        btn_user_management.setBounds(800,100,150,100);
        jp.add(btn_user_management);
        btn_user_management.addActionListener(this);

        //返回按钮
        btn_back = new JButton(">>注销<<");
        btn_back.setBackground(Color.LIGHT_GRAY);
        btn_back.setFont(new Font("宋体", Font.BOLD, 25));
        btn_back.setBounds(400,300,190,80);
        jp.add(btn_back);
        btn_back.addActionListener(this);

    }

    public void actionPerformed(ActionEvent event){

        if(event.getSource() == btn_in){
            toBookIn();
        }
        else if(event.getSource() == btn_out){
            toBookOut();
        }
        else if(event.getSource() == btn_update){
            toBookUpdate();
        }
        else if(event.getSource() == btn_order_management){
            toOrderManagement();
        }
        else if(event.getSource() == btn_user_management){
            toUserManagement();
        }
        else{
            toLoginWindow(this);
        }
    }
}
