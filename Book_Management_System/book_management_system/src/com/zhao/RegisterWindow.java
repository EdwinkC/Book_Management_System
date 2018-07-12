package com.zhao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zhao.DBHandle.isExistUser;
import static com.zhao.DBHandle.userRegister;
import static com.zhao.Redirect.toLoginWindow;
import static java.awt.Color.WHITE;

public class RegisterWindow extends JFrame implements ActionListener {

    private JLabel lbl_user;
    private JLabel lbl_name;
    private JLabel lbl_password;
    private JLabel lbl_password_again;
    private JTextField txt_user;
    private JTextField txt_name;
    private JPasswordField txt_password;
    private JPasswordField txt_password_again;
    private JButton btn_register;
    private JPanel jp;
    private JLabel label;

    public RegisterWindow(){
        jp = (JPanel)this.getContentPane();
        setTitle("注册");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, 650, 450);
        setResizable(false);
        jp.setBackground(WHITE);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        //用户名标签
        lbl_user = new JLabel("账号：");
        lbl_user.setFont(new Font("宋体", Font.BOLD, 20));
        lbl_user.setBounds(100, 105, 100, 24);
        jp.add(lbl_user);

        //姓名标签
        lbl_name = new JLabel("姓名：");
        lbl_name.setFont(new Font("宋体", Font.BOLD, 20));
        lbl_name.setBounds(100, 155, 100, 24);
        jp.add(lbl_name);

        //密码标签
        lbl_password = new JLabel("密码：");
        lbl_password.setFont(new Font("宋体", Font.BOLD, 20));
        lbl_password.setBounds(100, 205, 100, 24);
        jp.add(lbl_password);

        //二次密码标签
        lbl_password_again = new JLabel("确认密码：");
        lbl_password_again.setFont(new Font("宋体", Font.BOLD, 20));
        lbl_password_again.setBounds(100, 255, 150, 24);
        jp.add(lbl_password_again);

        //账号输入框
        txt_user = new JTextField();
        txt_user.setFont(new Font("宋体", Font.BOLD, 20));
        txt_user.setBounds(210, 100, 263, 38);
        txt_user.setColumns(10);
        jp.add(txt_user);

        //姓名输入框
        txt_name = new JTextField();
        txt_name.setFont(new Font("宋体", Font.BOLD, 20));
        txt_name.setBounds(210, 150, 263, 38);
        txt_name.setColumns(10);
        jp.add(txt_name);

        //密码输入框
        txt_password = new JPasswordField();
        txt_password.setFont(new Font("宋体", Font.BOLD, 20));
        txt_password.setColumns(10);
        txt_password.setBounds(210, 200, 263, 38);
        jp.add(txt_password);
        //二次密码输入框
        txt_password_again = new JPasswordField();
        txt_password_again.setFont(new Font("宋体", Font.BOLD, 20));
        txt_password_again.setColumns(10);
        txt_password_again.setBounds(210, 250, 263, 38);
        jp.add(txt_password_again);

        //注册按钮
        btn_register = new JButton("立即注册");
        btn_register.setFont(new Font("宋体", Font.BOLD, 20));
        btn_register.setBackground(Color.LIGHT_GRAY);
        btn_register.setBounds(230, 320, 150, 24);
        jp.add(btn_register);
        btn_register.addActionListener(this);

        label = new JLabel("新用户注册");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("宋体", Font.BOLD, 25));
        label.setBounds(210, 35, 180, 52);
        jp.add(label);

    }

    public void actionPerformed(ActionEvent event){
        String pwd = txt_password.getText();
        String pwd_again = txt_password_again.getText();
        String user = txt_user.getText();
        String name = txt_name.getText();
        if(user.equals("")||name.equals("")||pwd.equals("")){//输入不能为空
            JOptionPane.showMessageDialog(this, "输入不能为空！");
        }
        else{
            if(pwd.equals(pwd_again)){
                if(isExistUser(user)){//账户已被注册
                    JOptionPane.showMessageDialog(this, "该账户已被注册！");
                }
                else{
                    userRegister(user, name, pwd);//插表
                    JOptionPane.showMessageDialog(this, "注册成功！\n前往登录！");
                    toLoginWindow(this);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "两次密码不一致！");
            }
        }


    }
}
