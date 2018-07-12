package com.zhao.user;

import com.zhao.Information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zhao.DBHandle.bookAll;
import static com.zhao.DBHandle.borrowBook;
import static com.zhao.DBHandle.isExistBook;
import static com.zhao.Information.book_arraylist;

public class BookBorrow extends JFrame implements ActionListener {
    private JLabel lbl_borrow;
    private JTextField txt_borrow;
    private JButton btn_borrow;
    private JButton btn_showall;
    private JPanel jp;

    public BookBorrow(){

        jp = (JPanel)getContentPane();
        setTitle("图书借阅");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,200,550,350);
        setResizable(false);
        jp.setBackground(Color.lightGray);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        lbl_borrow = new JLabel("书号：");
        lbl_borrow.setFont(new Font("宋体", Font.BOLD, 25));
        lbl_borrow.setBounds(20, 100, 120, 30);
        jp.add(lbl_borrow);

        txt_borrow = new JTextField();
        txt_borrow.setFont(new Font("宋体", Font.BOLD, 25));
        txt_borrow.setBounds(150, 100, 230, 30);
        jp.add(txt_borrow);

        btn_borrow = new JButton("借阅");
        btn_borrow.setFont(new Font("宋体", Font.BOLD, 25));
        btn_borrow.setBounds(410, 100, 100, 30);
        jp.add(btn_borrow);
        btn_borrow.addActionListener(this);

        btn_showall = new JButton("显示全部图书");
        btn_showall.setFont(new Font("宋体", Font.BOLD,25));
        btn_showall.setBounds(165,200,200,30);
        jp.add(btn_showall);
        btn_showall.addActionListener(this);

    }

    public void actionPerformed (ActionEvent event){

        if(event.getSource() == btn_showall){
            bookAll();
            Object[] column_name = {"书号", "书名", "作者", "类型", "描述", "库存"};
            Object[][] obj = new Object[book_arraylist.size()][6];
            for(int i=0;i<book_arraylist.size();i++){
                obj[i][0] = book_arraylist.get(i).no;
                obj[i][1] = book_arraylist.get(i).name;
                obj[i][2] = book_arraylist.get(i).autor;
                obj[i][3] = book_arraylist.get(i).type;
                obj[i][4] = book_arraylist.get(i).content;
                obj[i][5] = book_arraylist.get(i).total;
            }

            new BookShowDialog(obj, column_name);//显示检索数据窗口
        }
        else{//图书借阅
            String no = txt_borrow.getText();
            if(no.equals("")){
                JOptionPane.showMessageDialog(this, "编辑框为空！");
            }
            else{
                if(isExistBook(no)&&Information.book_count>0){//存在此书籍
                    if(borrowBook(no, Information.user_no)){
                        JOptionPane.showMessageDialog(this, "借阅成功！");
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "借阅失败！");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "输入的书号不存在！");
                }
            }
        }
    }
}
