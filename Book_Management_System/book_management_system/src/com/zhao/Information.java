package com.zhao;

import com.zhao.Table.Book;
import com.zhao.Table.Borrow;
import com.zhao.Table.Order;
import com.zhao.Table.User;

import java.util.ArrayList;

/**
 * 定义数据库连接字
 * 定义全局静态变量
 */

public class Information {

    //数据库连接字
    final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://localhost:3306/book_management";
    final static String USER_NAME = "root";
    final static String DB_PASSWD = "123456";

    //状态字
    public static boolean customer_flag = false;
    public static boolean admin_flag = false;

    //用户
    public static String user_no;
    public static String user_name;
    public static String user_pwd;
    public static int user_total;

    //书
    public static int book_count;

    //arraylist
    public static ArrayList<Book> book_arraylist = new ArrayList<>();
    public static ArrayList<User> user_arraylist = new ArrayList<>();
    public static ArrayList<Order> order_arraylist = new ArrayList<>();
    public static ArrayList<Borrow> borrows_arraylist = new ArrayList<>();



}
