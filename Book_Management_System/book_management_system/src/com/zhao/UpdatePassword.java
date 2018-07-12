package com.zhao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zhao.DBHandle.*;
import static com.zhao.Redirect.toLoginWindow;
import static java.awt.Color.WHITE;

public class UpdatePassword extends JFrame implements ActionListener {

    private JLabel lbl_user;
    private JLabel getLbl_password_old;
    private JLabel lbl_password;
    private JLabel lbl_password_again;
    private JTextField txt_user;
    private JPasswordField txt_password_old;
    private JPasswordField txt_password;
    private JPasswordField txt_password_again;
    private JButton btn_update;
    private JPanel jp;
    private JLabel label;

    public UpdatePassword(){
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

        //旧密码标签
        getLbl_password_old = new JLabel("旧密码：");
        getLbl_password_old.setFont(new Font("宋体", Font.BOLD, 20));
        getLbl_password_old.setBounds(100, 155, 120, 24);
        jp.add(getLbl_password_old);

        //密码标签
        lbl_password = new JLabel("新密码：");
        lbl_password.setFont(new Font("宋体", Font.BOLD, 20));
        lbl_password.setBounds(100, 205, 120, 24);
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

        //旧密码输入框
        txt_password_old = new JPasswordField();
        txt_password_old.setFont(new Font("宋体", Font.BOLD, 20));
        txt_password_old.setBounds(210, 150, 263, 38);
        txt_password_old.setColumns(10);
        jp.add(txt_password_old);

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

        //更新按钮
        btn_update = new JButton("提交更新");
        btn_update.setFont(new Font("宋体", Font.BOLD, 20));
        btn_update.setBackground(Color.LIGHT_GRAY);
        btn_update.setBounds(230, 320, 150, 24);
        jp.add(btn_update);
        btn_update.addActionListener(this);

        label = new JLabel("密码更换");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("宋体", Font.BOLD, 25));
        label.setBounds(210, 35, 180, 52);
        jp.add(label);

    }

    public void actionPerformed(ActionEvent event){
        String pwd_old = txt_password_old.getText().trim();
        String pwd = txt_password.getText().trim();
        String pwd_again = txt_password_again.getText().trim();
        String user = txt_user.getText().trim();

        if(user.equals("")||pwd_old.equals("")||pwd.equals("")){//输入不能为空
            JOptionPane.showMessageDialog(this, "输入不能为空！");
        }
        else{
            if(pwd.equals(pwd_again)){
                if(userLogIn(user, pwd_old)){//旧密码登录成功
                    if(updatePassword(user, pwd)){//更新表
                        JOptionPane.showMessageDialog(this, "密码修改成功！\n前往登录！");
                        toLoginWindow(this);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "密码修改失败！");
                    }

                }
                else{
                    JOptionPane.showMessageDialog(this, "用户名或旧密码不正确！");
                }

            }
            else{
                JOptionPane.showMessageDialog(this, "两次新密码不一致！");
            }
        }


    }

}
