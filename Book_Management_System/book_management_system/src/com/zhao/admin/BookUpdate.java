package com.zhao.admin;

import com.zhao.Table.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.zhao.DBHandle.*;
import static com.zhao.Information.book_arraylist;
import static com.zhao.Redirect.toBookUpdate;

public class BookUpdate extends JFrame implements ActionListener {

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
    private JButton btn_bookupdate;
    private JButton btn_obtain;
    private JPanel jp;
    private JScrollPane jsp_txt;
    private JScrollPane jsp_table;
    private JTable table;
    private ArrayList<Book> selected_book = new ArrayList<>();

    public BookUpdate(){
        jp = (JPanel)getContentPane();
        setTitle("图书更新");
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

        btn_bookupdate = new JButton("更新");
        btn_bookupdate.setFont(new Font("宋体", Font.BOLD, 20));
        btn_bookupdate.setBounds(420, 100, 100, 25);
        jp.add(btn_bookupdate);
        btn_bookupdate.addActionListener(this);

        btn_obtain = new JButton("获取");
        btn_obtain.setFont(new Font("宋体", Font.BOLD, 20));
        btn_obtain.setBounds(420, 150, 100, 25);
        jp.add(btn_obtain);
        btn_obtain.addActionListener(this);

        class MyTableModel extends AbstractTableModel {

            String[] column_name = {"书号", "书名", "作者", "类型", "描述", "库存", "选择"};;
            Object[][] obj;

            public MyTableModel(){
                bookAll();


                obj = new Object[book_arraylist.size()][column_name.length];

                for(int i=0;i<book_arraylist.size();i++){
                    obj[i][0] = book_arraylist.get(i).no;
                    obj[i][1] = book_arraylist.get(i).name;
                    obj[i][2] = book_arraylist.get(i).autor;
                    obj[i][3] = book_arraylist.get(i).type;
                    obj[i][4] = book_arraylist.get(i).content;
                    obj[i][5] = book_arraylist.get(i).total;
                    obj[i][6] = new Boolean(false);
                }
            }

            //定义每列的数据类型
            Class[] typeArray = { Object.class, Object.class, Object.class, Object.class,
                    Object.class, Object.class, Boolean.class };

            /**
             * 获得表格的列名称
             */
            @Override

            public String getColumnName(int column) {

                return column_name[column];

            }
            /**
             * 重写方法，得到表格列数
             */
            @Override
            public int getColumnCount()
            {
                return column_name.length;
            }

            public int getRowCount(){
                return obj.length;
            }
            /**
             * 得到数据所对应对象
             */
            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                return obj[rowIndex][columnIndex];
            }


            /**
             * 使表格具有可编辑性
             */

            @Override

            public boolean isCellEditable(int rowIndex, int columnIndex) {

                if(columnIndex == 6)
                    return true;
                else
                    return false;
            }

            /**
             * 替换单元格的值
             */
            @Override

            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

                obj[rowIndex][columnIndex] = aValue;

                fireTableCellUpdated(rowIndex, columnIndex);

                selected_book.clear();
                for(int i=0;i<getRowCount();i++){
                    Book book_tmp = new Book();
                    book_tmp.no = (String) getValueAt(i,0);
                    book_tmp.name = (String) getValueAt(i,1);
                    book_tmp.autor = (String) getValueAt(i,2);
                    book_tmp.type = (String) getValueAt(i,3);
                    book_tmp.content = (String) getValueAt(i,4);
                    book_tmp.total = (Integer) getValueAt(i,5);
                    if((Boolean)getValueAt(i, 6)){

                        selected_book.add(book_tmp);
                    }
                    else{
                        for(int j=0;j<selected_book.size();j++){
                            if(selected_book.get(j).no == book_tmp.no){
                                selected_book.remove(i);
                            }
                        }
                    }
                }

            }

            /**
             *返回列的数据类型
             */
            @Override
            public Class getColumnClass(int columnIndex){
                return typeArray[columnIndex];// 返回每一列的数据类型
            }

        }


        table = new JTable(new MyTableModel());
        jsp_table = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp_table.setBounds(10,200,520,250);
        jp.add(jsp_table);


    }


    public void actionPerformed (ActionEvent event){

        if(event.getSource() == btn_bookupdate){
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
                Book new_book_inf = new Book();
                new_book_inf.no = book_no;
                new_book_inf.name = book_name;
                new_book_inf.autor = book_autor;
                new_book_inf.type = book_type;
                new_book_inf.content = book_content;
                new_book_inf.total = Integer.valueOf(book_total);

                if(bookUpdate(new_book_inf)){
                    JOptionPane.showMessageDialog(this, "更新成功！");
                    this.dispose();
                    toBookUpdate();
                }
                else{
                    JOptionPane.showMessageDialog(this, "更新失败！");
                }
            }
        }
        else if(event.getSource() == btn_obtain){
            if(selected_book.size() == 0){
                JOptionPane.showMessageDialog(this, "请选择一本书！");
                return;
            }
            else if(selected_book.size() > 1){
                JOptionPane.showMessageDialog(this, "请选择不多于一本书！");
                return;
            }
            else{
                txt_book_no.setText(selected_book.get(0).no);
                txt_book_name.setText(selected_book.get(0).name);
                txt_book_autor.setText(selected_book.get(0).autor);
                txt_book_type.setText(selected_book.get(0).type);
                txt_book_content.setText(selected_book.get(0).content);
                txt_book_total.setText(String.valueOf(selected_book.get(0).total));
            }

        }
    }
}
