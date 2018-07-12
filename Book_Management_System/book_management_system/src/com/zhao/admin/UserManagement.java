package com.zhao.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.zhao.DBHandle.*;
import static com.zhao.Information.user_arraylist;
import static com.zhao.Redirect.toUserManagement;

public class UserManagement extends JFrame implements ActionListener {

    private JButton btn_userDelete;
    private JPanel jp;
    private JScrollPane jsp_table;
    private JTable table;
    private ArrayList<String> selected_user = new ArrayList<>();

    public UserManagement(){
        jp = (JPanel)getContentPane();
        setTitle("用户管理");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400,100,550,420);
        setResizable(false);
        jp.setBackground(Color.lightGray);
        jp.setBorder(new EmptyBorder(5, 5, 5, 5));
        jp.setLayout(null);

        btn_userDelete = new JButton("删除用户");
        btn_userDelete.setFont(new Font("宋体", Font.BOLD, 20));
        btn_userDelete.setBounds(220, 350, 150, 25);
        jp.add(btn_userDelete);
        btn_userDelete.addActionListener(this);

        class MyTableModel extends AbstractTableModel {

            String[] column_name = {"用户名", "姓名", "密码", "正在借阅的数量", "选择"};;
            Object[][] obj;

            public MyTableModel(){
                userAll();

                obj = new Object[user_arraylist.size()][column_name.length];

                for(int i=0;i<user_arraylist.size();i++){
                    obj[i][0] = user_arraylist.get(i).no;
                    obj[i][1] = user_arraylist.get(i).name;
                    obj[i][2] = user_arraylist.get(i).pwd.substring(0,3) + "***";
                    obj[i][3] = user_arraylist.get(i).total;
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

                selected_user.clear();
                for(int i=0;i<getRowCount();i++){
                    String user_no = (String) getValueAt(i, 0);

                    if((Boolean)getValueAt(i, 4)){

                        selected_user.add(user_no);
                    }
                    else{
                        for(int j=0;j<selected_user.size();j++){
                            if(selected_user.get(j) == user_no){
                                selected_user.remove(i);
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
        if(selected_user.size() == 0){
            JOptionPane.showMessageDialog(this, "请选择至少一个用户！");
            return;
        }

        for(int i=0;i<selected_user.size();i++){
            if(deleteUser(selected_user.get(i))){
                JOptionPane.showMessageDialog(this,"用户" + selected_user.get(i) +"删除成功");

            }
            else{
                JOptionPane.showMessageDialog(this,"用户" + selected_user.get(i) +"删除失败");
            }


        }
        this.dispose();
        toUserManagement();

    }
}
