package com.zhao.user;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zhao.DBHandle.bookSearch;
import static com.zhao.Information.book_arraylist;

public class BookSearch extends JFrame implements ActionListener {
    private JLabel lbl_keyword;
    private JTextField txt_keyword;
    private JButton btn_search;
    private JPanel jp;


    public BookSearch(){

        jp = (JPanel)getContentPane();
        setTitle("图书检索");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,200,550,350);
        setResizable(false);
        jp.setBackground(Color.lightGray);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        lbl_keyword = new JLabel("关键字：");
        lbl_keyword.setFont(new Font("宋体", Font.BOLD, 25));
        lbl_keyword.setBounds(20, 124, 120, 30);
        jp.add(lbl_keyword);

        txt_keyword = new JTextField();
        txt_keyword.setFont(new Font("宋体", Font.BOLD, 25));
        txt_keyword.setBounds(150, 124, 230, 30);
        jp.add(txt_keyword);

        btn_search = new JButton("检索");
        btn_search.setFont(new Font("宋体", Font.BOLD, 25));
        btn_search.setBounds(410, 124, 100, 30);
        jp.add(btn_search);
        btn_search.addActionListener(this);

    }

    public void actionPerformed (ActionEvent event){
        String keyword = txt_keyword.getText().trim();
        if(keyword.equals("")){
            JOptionPane.showMessageDialog(this, "编辑框为空！");
        }
        else{
            if(bookSearch(keyword) == 0){//没有检索到
                JOptionPane.showMessageDialog(this, "没有找到相关的书籍！");
            }
            else{
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

        }

    }

}
