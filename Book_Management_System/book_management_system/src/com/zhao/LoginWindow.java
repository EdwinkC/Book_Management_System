package com.zhao;

import com.zhao.Information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zhao.DBHandle.userLogIn;
import static com.zhao.Redirect.*;
import static java.awt.Color.WHITE;

public class LoginWindow extends JFrame implements ActionListener {

    private JLabel lbl_user;
    private JLabel lbl_password;
    private JTextField txt_user;
    private JPasswordField txt_password;
    private JButton btn_login;
    private JButton btn_register;
    private JButton btn_changePassword;
    private JPanel jp;
    private JLabel label;
    public LoginWindow(){

        jp = (JPanel)this.getContentPane();
        setTitle("登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, 650, 400);
        setResizable(false);
        jp.setBackground(WHITE);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        //用户名标签
        lbl_user = new JLabel("账号：");
        lbl_user.setFont(new Font("宋体", Font.BOLD, 20));
        lbl_user.setBounds(100, 124, 100, 24);
        jp.add(lbl_user);

        //密码标签
        lbl_password = new JLabel("密码：");
        lbl_password.setFont(new Font("宋体", Font.BOLD, 20));
        lbl_password.setBounds(100, 200, 100, 24);
        jp.add(lbl_password);

        //账号输入框
        txt_user = new JTextField();
        txt_user.setFont(new Font("宋体", Font.BOLD, 20));
        txt_user.setBounds(170, 124, 263, 38);
        txt_user.setColumns(10);
        jp.add(txt_user);

        //密码输入框
        txt_password = new JPasswordField();
        txt_password.setFont(new Font("宋体", Font.BOLD, 20));
        txt_password.setColumns(10);
        txt_password.setBounds(170, 200, 263, 38);
        jp.add(txt_password);

        //登录按钮
        btn_login = new JButton("登录");
        btn_login.setBackground(Color.LIGHT_GRAY);
        btn_login.setFont(new Font("宋体", Font.BOLD, 20));
        btn_login.setBounds(170, 320, 85, 24);
        jp.add(btn_login);
        btn_login.addActionListener(this);

        //注册按钮
        btn_register = new JButton("注册");
        btn_register.setFont(new Font("宋体", Font.BOLD, 20));
        btn_register.setBackground(Color.LIGHT_GRAY);
        btn_register.setBounds(350, 320, 85, 24);
        jp.add(btn_register);
        btn_register.addActionListener(this);

        //修改密码按钮
        btn_changePassword = new JButton("更改密码");
        btn_changePassword.setFont(new Font("宋体", Font.BOLD, 20));
        btn_changePassword.setBackground(Color.LIGHT_GRAY);
        btn_changePassword.setBounds(490, 210, 120, 24);
        jp.add(btn_changePassword);
        btn_changePassword.addActionListener(this);

        label = new JLabel("图书管理系统");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("宋体", Font.BOLD, 25));
        label.setBounds(210, 35, 180, 52);
        jp.add(label);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == btn_login){
            String user = txt_user.getText();
            String password = txt_password.getText();
            if(user.equals("")||password.equals("")){
                JOptionPane.showMessageDialog(this, "用户名或密码为空！");
            }
            else{
                if(userLogIn(user, password)){//登录成功

                    //存储静态信息
                    Information.user_no = user;

                    if(user.equals("admin")){//管理员
                        toAdminMenuWindow(this);
                    }
                    else{//一般用户
                        toUserMenuWindow(this);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "登录失败！");
                }
            }
        }
        else if(event.getSource() == btn_register){
            toRegisterWindow(this);
        }
        else if(event.getSource() == btn_changePassword){
            toUpdatePasswordWindow(this);
        }
    }
}
