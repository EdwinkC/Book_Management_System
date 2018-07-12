package com.zhao.user;

import com.zhao.Information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zhao.DBHandle.*;
import static com.zhao.Information.borrows_arraylist;
import static com.zhao.Redirect.toBookReturn;

public class BookReturn extends JFrame implements ActionListener {
    private JLabel lbl_return;
    private JTextField txt_return;
    private JButton btn_return;
    private JPanel jp;
    private JScrollPane jsp;
    private JTable table;

    public BookReturn(){
        jp = (JPanel)getContentPane();
        setTitle("图书归还");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,200,550,350);
        setResizable(false);
        jp.setBackground(Color.lightGray);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        lbl_return = new JLabel("书号：");
        lbl_return.setFont(new Font("宋体", Font.BOLD, 20));
        lbl_return.setBounds(20, 20, 120, 25);
        jp.add(lbl_return);

        txt_return = new JTextField();
        txt_return.setFont(new Font("宋体", Font.BOLD, 20));
        txt_return.setBounds(150, 20, 230, 25);
        jp.add(txt_return);

        btn_return = new JButton("归还");
        btn_return.setFont(new Font("宋体", Font.BOLD, 20));
        btn_return.setBounds(410, 20, 100, 25);
        jp.add(btn_return);
        btn_return.addActionListener(this);

        personReturnBook(Information.user_no);

        Object[] column_name = {"书号", "书名", "借书日期"};
        Object[][] obj = new Object[borrows_arraylist.size()][3];
        for(int i=0;i<borrows_arraylist.size();i++){
            obj[i][0] = borrows_arraylist.get(i).book_no;
            obj[i][1] = borrows_arraylist.get(i).book_name;
            obj[i][2] = borrows_arraylist.get(i).date;


        }

        table = new JTable(obj,column_name);
        table.setEnabled(false);
        jsp = new JScrollPane(table);
        jsp.setBounds(20,80,500,200);

        jp.add(jsp);

    }

    public void actionPerformed (ActionEvent event){
        String book_no = txt_return.getText();
        if(book_no.equals("")){
            JOptionPane.showMessageDialog(this, "编辑框为空！");
        }
        else{
            if(isExistPersonBorrowBook(book_no, Information.user_no)){
                if(returnBook(book_no, Information.user_no)){
                    JOptionPane.showMessageDialog(this, "还书成功！");
                    this.dispose();
                    toBookReturn();
                }
                else{
                    JOptionPane.showMessageDialog(this, "还书失败！");
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "不存在该编号的已借阅书籍！");
            }
        }

    }
}
