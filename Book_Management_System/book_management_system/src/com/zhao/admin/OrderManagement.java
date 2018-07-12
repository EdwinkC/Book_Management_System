package com.zhao.admin;

import com.zhao.Table.Order;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.zhao.DBHandle.oldBookOut;
import static com.zhao.DBHandle.orderAll;
import static com.zhao.Information.order_arraylist;
import static com.zhao.Redirect.toOrderManagement;

public class OrderManagement extends JFrame implements ActionListener {

    private JButton btn_orderDelete;
    private JPanel jp;
    private JScrollPane jsp_table;
    private JTable table;
    private ArrayList<Order> selected_order = new ArrayList<>();

    public OrderManagement(){
        jp = (JPanel)getContentPane();
        setTitle("图书预约管理");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,100,550,420);
        setResizable(false);
        jp.setBackground(Color.lightGray);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        btn_orderDelete = new JButton("受理");
        btn_orderDelete.setFont(new Font("宋体", Font.BOLD, 20));
        btn_orderDelete.setBounds(220, 350, 100, 25);
        jp.add(btn_orderDelete);
        btn_orderDelete.addActionListener(this);

        class MyTableModel extends AbstractTableModel {

            String[] column_name = {"用户名", "姓名", "预约书名", "预约时间", "选择"};;
            Object[][] obj;

            public MyTableModel(){
                orderAll();

                obj = new Object[order_arraylist.size()][column_name.length];

                for(int i=0;i<order_arraylist.size();i++){
                    obj[i][0] = order_arraylist.get(i).user_no;
                    obj[i][1] = order_arraylist.get(i).user_name;
                    obj[i][2] = order_arraylist.get(i).book_name;
                    obj[i][3] = order_arraylist.get(i).date;
                    obj[i][4] = new Boolean(false);
                }
            }

            //定义每列的数据类型
            Class[] typeArray = { Object.class, Object.class, Object.class, Object.class, Boolean.class };

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

                if(columnIndex == 4)
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

                selected_order.clear();
                for(int i=0;i<getRowCount();i++){
                    String user_no = (String) getValueAt(i, 0);
                    String book_name = (String) getValueAt(i, 2);
                    Order order_tmp = new Order();
                    order_tmp.user_no = user_no;
                    order_tmp.book_name = book_name;
                    if((Boolean)getValueAt(i, 4)){

                        selected_order.add(order_tmp);
                    }
                    else{
                        for(int j=0;j<selected_order.size();j++){
                            if(selected_order.get(j).user_no == user_no && selected_order.get(j).book_name == book_name){
                                selected_order.remove(i);
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
        if(selected_order.size() == 0){
            JOptionPane.showMessageDialog(this, "请选择至少一个预约记录！");
            return;
        }

        for(int i=0;i<selected_order.size();i++){
            if(oldBookOut(selected_order.get(i).user_no, selected_order.get(i).book_name)){
                JOptionPane.showMessageDialog(this,selected_order.get(i).user_no + "预约的《" +
                        selected_order.get(i).book_name + "》\n受理成功！");

            }
            else{
                JOptionPane.showMessageDialog(this,selected_order.get(i).user_no + "预约的《" +
                        selected_order.get(i).book_name + "》\n受理失败！");
            }


        }
        this.dispose();
        toOrderManagement();

    }
}
