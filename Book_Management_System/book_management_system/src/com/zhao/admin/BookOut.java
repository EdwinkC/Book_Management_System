package com.zhao.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.zhao.DBHandle.bookAll;
import static com.zhao.DBHandle.oldBookOut;
import static com.zhao.Information.book_arraylist;
import static com.zhao.Redirect.toBookOut;

public class BookOut extends JFrame implements ActionListener {
    private JButton btn_bookOut;
    private JPanel jp;
    private JScrollPane jsp_table;
    private JTable table;
    private ArrayList<String> selected_book = new ArrayList<>();

    public BookOut(){
        jp = (JPanel)getContentPane();
        setTitle("图书出库");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,100,550,420);
        setResizable(false);
        jp.setBackground(Color.lightGray);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        btn_bookOut = new JButton("出库");
        btn_bookOut.setFont(new Font("宋体", Font.BOLD, 20));
        btn_bookOut.setBounds(220, 350, 100, 25);
        jp.add(btn_bookOut);
        btn_bookOut.addActionListener(this);

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
                    String book_no = (String) getValueAt(i, 0);
                    if((Boolean)getValueAt(i, 6)){

                        selected_book.add(book_no);
                    }
                    else{
                        for(int j=0;j<selected_book.size();j++){
                            if(selected_book.get(j) == book_no){
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
        jsp_table.setBounds(10,20,520,300);
        jp.add(jsp_table);


    }


    public void actionPerformed (ActionEvent event){
        if(selected_book.size() == 0){
            JOptionPane.showMessageDialog(this, "请选择至少一本书！");
            return;
        }

        for(String s:selected_book){
            if(oldBookOut(s)){
                JOptionPane.showMessageDialog(this,s + "：出库成功！");

            }
            else{
                JOptionPane.showMessageDialog(this,s + "：出库失败！");
            }


        }
        this.dispose();
        toBookOut();

    }
}
