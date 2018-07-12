package com.zhao.admin;

import com.sun.deploy.panel.JSmartTextArea;
import com.zhao.Information;
import com.zhao.Table.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.zhao.DBHandle.*;
import static com.zhao.Information.book_arraylist;
import static com.zhao.Redirect.toBookIn;
import static com.zhao.Redirect.toBookReturn;

public class BookIn extends JFrame implements ActionListener {
    private JLabel lbl_book_no;
    private JLabel lbl_book_name;
    private JLabel lbl_book_autor;
    private JLabel lbl_book_type;
    private JLabel lbl_book_content;
    private JLabel lbl_book_total;
    private JTextField txt_book_no;
    private JTextField txt_book_name;
    private JTextField txt_book_autor;
    private JTextField txt_book_type;
    private JTextArea txt_book_content;
    private JTextField txt_book_total;
    private JButton btn_bookin;
    private JPanel jp;
    private JScrollPane jsp_txt;
    private JScrollPane jsp_table;
    private JTable table;
    public BookIn(){
        jp = (JPanel)getContentPane();
        setTitle("图书入库");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,100,550,500);
        setResizable(false);
        jp.setBackground(Color.lightGray);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        lbl_book_no = new JLabel("书号：");
        lbl_book_no.setFont(new Font("宋体", Font.BOLD, 15));
        lbl_book_no.setBounds(10, 20, 50, 20);
        jp.add(lbl_book_no);

        txt_book_no = new JTextField();
        txt_book_no.setFont(new Font("宋体", Font.BOLD, 15));
        txt_book_no.setBounds(70, 20, 100, 20);
        jp.add(txt_book_no);

        lbl_book_name = new JLabel("书名：");
        lbl_book_name.setFont(new Font("宋体", Font.BOLD, 15));
        lbl_book_name.setBounds(200, 20, 50, 20);
        jp.add(lbl_book_name);

        txt_book_name = new JTextField();
        txt_book_name.setFont(new Font("宋体", Font.BOLD, 15));
        txt_book_name.setBounds(260, 20, 100, 20);
        jp.add(txt_book_name);

        lbl_book_autor = new JLabel("作者：");
        lbl_book_autor.setFont(new Font("宋体", Font.BOLD, 15));
        lbl_book_autor.setBounds(10, 60, 50, 20);
        jp.add(lbl_book_autor);

        txt_book_autor = new JTextField();
        txt_book_autor.setFont(new Font("宋体", Font.BOLD, 15));
        txt_book_autor.setBounds(70, 60, 100, 20);
        jp.add(txt_book_autor);

        lbl_book_type = new JLabel("类型：");
        lbl_book_type.setFont(new Font("宋体", Font.BOLD, 15));
        lbl_book_type.setBounds(200, 60, 50, 20);
        jp.add(lbl_book_type);

        txt_book_type = new JTextField();
        txt_book_type.setFont(new Font("宋体", Font.BOLD, 15));
        txt_book_type.setBounds(260, 60, 100, 20);
        jp.add(txt_book_type);

        lbl_book_content = new JLabel("描述：");
        lbl_book_content.setFont(new Font("宋体", Font.BOLD, 15));
        lbl_book_content.setBounds(10, 100, 50, 20);
        jp.add(lbl_book_content);

        txt_book_content = new JTextArea();
        txt_book_content.setFont(new Font("宋体", Font.BOLD, 15));
        txt_book_content.setBounds(70, 100, 300, 80);
        txt_book_content.setLineWrap(true);
        txt_book_content.setWrapStyleWord(true);
        jsp_txt = new JScrollPane(txt_book_content);
        jsp_txt.setBounds(70,100,300,80);
        jp.add(jsp_txt);

        lbl_book_total = new JLabel("数量：");
        lbl_book_total.setFont(new Font("宋体", Font.BOLD, 15));
        lbl_book_total.setBounds(390, 20, 50, 20);
        jp.add(lbl_book_total);

        txt_book_total = new JTextField();
        txt_book_total.setFont(new Font("宋体", Font.BOLD, 15));
        txt_book_total.setBounds(450, 20, 70, 20);
        jp.add(txt_book_total);

        btn_bookin = new JButton("入库");
        btn_bookin.setFont(new Font("宋体", Font.BOLD, 20));
        btn_bookin.setBounds(420, 100, 100, 25);
        jp.add(btn_bookin);
        btn_bookin.addActionListener(this);

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

        table = new JTable(obj, column_name);
        table.setEnabled(false);
        jsp_table = new JScrollPane(table);
        jsp_table.setBounds(10,200,520,250);
        jp.add(jsp_table);

    }

    public void actionPerformed (ActionEvent event){
        String book_no = txt_book_no.getText().trim();
        String book_name = txt_book_name.getText().trim();
        String book_autor = txt_book_autor.getText().trim();
        String book_type = txt_book_type.getText().trim();
        String book_content = txt_book_content.getText().trim();
        String book_total = txt_book_total.getText().trim();
        if(book_no.equals("")||book_name.equals("")||book_autor.equals("")||book_type.equals("")||book_content.equals("")||book_total.equals("")){
            JOptionPane.showMessageDialog(this, "编辑框不能为空！");
        }
        else{
            if(isExistBook(book_no)){
                JOptionPane.showMessageDialog(this, "已存在该编号的书！");
            }
            else{
                Book new_book = new Book();
                new_book.no = book_no;
                new_book.name = book_name;
                new_book.autor = book_autor;
                new_book.type = book_type;
                new_book.content = book_content;
                new_book.total = Integer.valueOf(book_total);

                if(newBookIn(new_book)){
                    JOptionPane.showMessageDialog(this, "新书入库成功！");
                    this.dispose();
                    toBookIn();
                }
                else{
                    JOptionPane.showMessageDialog(this, "新书入库失败！");
                }
            }
        }

    }
}
