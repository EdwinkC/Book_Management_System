package com.zhao.user;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 图书检索显示数据表
 */
public class BookShowDialog extends JFrame {
    private JPanel jp;
    private JScrollPane jsp;
    private JTable jtable;

    public BookShowDialog(Object [][] obj, Object[] col){
        jp = (JPanel)getContentPane();
        jp.setBackground(Color.lightGray);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        jtable = new JTable(obj, col);
        jtable.setEnabled(false);
        jsp = new JScrollPane(jtable);
        jsp.setBounds(20,20,500,280);
        //分别设置水平和垂直滚动条自动出现
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jp.add(jsp);

        setTitle("图书显示");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(450,250,550,350);
        setResizable(false);
        setVisible(true);
    }
}
