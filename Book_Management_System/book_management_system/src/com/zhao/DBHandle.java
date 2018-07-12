package com.zhao;

import com.zhao.Table.Book;
import com.zhao.Table.Borrow;
import com.zhao.Table.Order;
import com.zhao.Table.User;

import java.sql.*;
import java.text.SimpleDateFormat;

import static com.zhao.Information.*;
public class DBHandle {

    /**
     * 用户登录验证
     *
     * @param no
     * @param pwd
     * @return
     */
    public static boolean userLogIn(String no, String pwd) {
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "SELECT * FROM user WHERE Uno = '" + no + "' AND Upwd = '" + pwd + "'";
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {//用户登录成功
                rs.close();
                sta.close();
                con.close();
                System.out.println("数据库已关闭...");
                return true;
            }
            rs.close();
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    /**
     * 账户是否已被注册
     *
     * @param no
     * @return
     */
    public static boolean isExistUser(String no) {
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "SELECT * FROM user WHERE Uno = '" + no + "'";
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {//存在用户
                rs.close();
                sta.close();
                con.close();
                System.out.println("数据库已安全关闭...");
                return true;
            }
            rs.close();
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    /**
     * 创建新用户插表
     * @param no
     * @param name
     * @param pwd
     */
    public static void userRegister(String no, String name, String pwd){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "INSERT INTO user VALUES('" + no + "','" + pwd + "','" + name + "', 0)";
            int c = sta.executeUpdate(sql);
            if(c == 1){
                System.out.println("New user create success!");
                sta.close();
                con.close();
            }
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * 图书关键字查询，存入book_array中
     * @param keyword
     * @return 查询的结果数目
     */
    public static int bookSearch(String keyword){
        int count = 0;
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "SELECT * FROM book WHERE Bname like '%" + keyword + "%'";
            ResultSet rs = sta.executeQuery(sql);

            book_arraylist.clear();//清空

            while(rs.next()){
                Book book = new Book();
                book.no = rs.getString("Bno");
                book.name = rs.getString("Bname");
                book.autor = rs.getString("Bautor");
                book.type = rs.getString("Btype");
                book.content = rs.getString("Bcontent");
                if(book.content.length()>15)
                    book.content = book.content.substring(0, 15) + "...";
                book.total = rs.getInt("Bcount");
                book_arraylist.add(book);//添加
                count++;

                //System.out.println(book.no+ " "+book.name + " " + book.total + " " + book.content);
                //System.out.println(count);
            }
            rs.close();
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return count;
    }

    /**
     * 全部图书，存入book_array中
     */
    public static void bookAll(){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "SELECT * FROM book";
            ResultSet rs = sta.executeQuery(sql);

            book_arraylist.clear();//清空

            while(rs.next()){
                Book book = new Book();
                book.no = rs.getString("Bno");
                book.name = rs.getString("Bname");
                book.autor = rs.getString("Bautor");
                book.type = rs.getString("Btype");
                book.content = rs.getString("Bcontent");
                book.total = rs.getInt("Bcount");
                book_arraylist.add(book);//添加

                //System.out.println(book.no+ " "+book.name + " " + book.total + " " + book.content);
                //System.out.println(count);
            }
            rs.close();
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * 查询数据库是否存在编号为no的书籍
     * @param no
     * @return
     */
    public static boolean isExistBook(String no) {
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "SELECT * FROM book WHERE Bno = '" + no + "'";
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {//存在此书

                Information.book_count = rs.getInt("Bcount");

                rs.close();
                sta.close();
                con.close();
                System.out.println("数据库已安全关闭...");
                return true;
            }
            rs.close();
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    /**
     * 指定用户借阅指定书籍
     * @param book_no
     * @param user_no
     * @return
     */
    public static boolean borrowBook(String book_no, String user_no) {
        Connection con = null;
        Statement sta = null;
        java.util.Date date = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String borrow_date = df.format(date);

        try {

            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            con.setAutoCommit(false);
            sta = con.createStatement();
            String sql0 = "update book set Bcount = Bcount - 1 where Bno = '"+ book_no +"'";
            String sql1 = "update user set Nborrow = Nborrow + 1 where Uno = '" + user_no + "'";
            String sql2 = "insert into borrow (Uno, Bno, BorrowDate) values('" + user_no + "','"+ book_no +"','"+ borrow_date +"')";
            sta.addBatch(sql0);
            sta.addBatch(sql1);
            sta.addBatch(sql2);
            sta.executeBatch();
            con.commit();//提交事务
            con.setAutoCommit(true);
            return  true;
        } catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
            try{
                if(con != null){
                    con.rollback();
                    con.setAutoCommit(true);
                }
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }finally {
                try{
                    sta.close();
                    con.close();
                    System.out.println("数据库已安全关闭...");
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        }

        return false;
    }


    /**
     * 指定用户归还指定书籍
     * @param book_no
     * @param user_no
     * @return
     */
    public static boolean returnBook(String book_no, String user_no) {
        Connection con = null;
        Statement sta = null;
        try {

            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            con.setAutoCommit(false);
            sta = con.createStatement();
            String sql0 = "update book set Bcount = Bcount + 1 where Bno = '"+ book_no +"'";
            String sql1 = "update user set Nborrow = Nborrow - 1 where Uno = '" + user_no + "'";
            String sql2 = "delete from borrow where Uno ='" + user_no + "' and Bno = '" + book_no + "'";
            sta.addBatch(sql0);
            sta.addBatch(sql1);
            sta.addBatch(sql2);
            sta.executeBatch();
            con.commit();//提交事务
            con.setAutoCommit(true);
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
            return  true;
        } catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
            try{
                if(con != null){
                    con.rollback();
                    con.setAutoCommit(true);
                }
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }finally {
                try{
                    sta.close();
                    con.close();
                    System.out.println("回滚异常，数据库已安全关闭...");
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        }

        return false;
    }

    /**
     * 指定用户的可以归还的书籍
     * @param user_no
     */
    public static void personReturnBook(String user_no){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "SELECT * FROM v_borrow where Uno='" + user_no + "'";
            ResultSet rs = sta.executeQuery(sql);

            borrows_arraylist.clear();//清空

            while(rs.next()){
                Borrow borrow = new Borrow();
                borrow.book_no = rs.getString("Bno");
                borrow.book_name = rs.getString("Bname");
                borrow.date = rs.getDate("BorrowDate");
                borrows_arraylist.add(borrow);//添加
            }

            rs.close();
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }


    /**
     * 判断该用户是否借阅过指定ID的书籍
     * @param book_no
     * @param user_no
     * @return
     */
    public static boolean isExistPersonBorrowBook(String book_no, String user_no) {
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "SELECT * FROM borrow WHERE Bno = '" + book_no + "' AND Uno = '" + user_no + "'";
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {//存在此书
                rs.close();
                sta.close();
                con.close();
                System.out.println("数据库已安全关闭...");
                return true;
            }
            rs.close();
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    public static boolean bookOrder(String book_name, String user_no){
        java.util.Date date = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String order_date = df.format(date);
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "INSERT INTO t_order VALUES('" + user_no + "','" + book_name + "','" + order_date + "')";
            int c = sta.executeUpdate(sql);
            if(c == 1){
                System.out.println("New order message insert success!");
                sta.close();
                con.close();
            }
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
            return true;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    /**
     * 插入一条新书的记录
     * @param new_book
     * @return
     */
    public static boolean newBookIn(Book new_book){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "INSERT INTO book VALUES('" + new_book.no + "','" + new_book.name +
                    "','" + new_book.autor + "','" + new_book.type + "','" + new_book.content + "'," + new_book.total + ")";
            int c = sta.executeUpdate(sql);
            if(c == 1){
                System.out.println("New book insert success!");
                sta.close();
                con.close();
            }
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
            return true;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    /**
     * 删除指定书籍的记录
     * @param book_no
     * @return
     */
    public static boolean oldBookOut(String book_no){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "DELETE FROM book WHERE Bno = '" + book_no + "'";
            int c = sta.executeUpdate(sql);
            if(c == 1){
                System.out.println("Old book delete success!");
                sta.close();
                con.close();
            }
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
            return true;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    /**
     * 全部预约记录，存入order_arraylist中
     */
    public static void orderAll(){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "SELECT * FROM v_order";
            ResultSet rs = sta.executeQuery(sql);

            order_arraylist.clear();//清空

            while(rs.next()){
                Order order = new Order();
                order.user_no = rs.getString("Uno");
                order.user_name = rs.getString("Uname");
                order.book_name = rs.getString("Bname");
                order.date = rs.getDate("OrderDate");
                order_arraylist.add(order);//添加

            }
            rs.close();
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * 删除指定的预约记录
     * @param user_no
     * @param book_name
     * @return
     */
    public static boolean oldBookOut(String user_no, String book_name){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "DELETE FROM t_order WHERE Uno = '" + user_no + "' AND Bname = '" + book_name + "'";
            int c = sta.executeUpdate(sql);
            if(c == 1){
                System.out.println("order message delete success!");
                sta.close();
                con.close();
            }
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
            return true;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    /**
     * 更新指定用户ID的密码
     * @param user_no
     * @param new_pwd
     * @return
     */
    public static boolean updatePassword(String user_no, String new_pwd){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "UPDATE user SET Upwd = '" + new_pwd + "' WHERE Uno = '" + user_no + "'";
            int c = sta.executeUpdate(sql);
            if(c == 1){
                System.out.println("new password update success!");
                sta.close();
                con.close();
                System.out.println("数据库已安全关闭...");
                return true;
            }
            else {
                sta.close();
                con.close();
                System.out.println("数据库已安全关闭...");
                return false;
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    /**
     * 读库全部用户信息
     */
    public static void userAll(){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "SELECT * FROM user";
            ResultSet rs = sta.executeQuery(sql);

            user_arraylist.clear();//清空

            while(rs.next()){
                User user = new User();
                user.no = rs.getString("Uno");
                user.name = rs.getString("Uname");
                user.pwd = rs.getString("Upwd");
                user.total = rs.getInt("NBorrow");
                user_arraylist.add(user);//添加

            }
            rs.close();
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * 删除指定ID的用户
     * @param no
     * @return
     */
    public static boolean deleteUser(String no){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();
            String sql = "DELETE FROM user WHERE Uno = '" + no + "'";
            int c = sta.executeUpdate(sql);
            if(c == 1){
                System.out.println("user delete success!");
                sta.close();
                con.close();
            }
            sta.close();
            con.close();
            System.out.println("数据库已安全关闭...");
            return true;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    public static boolean bookUpdate(Book new_book_inf){
        try {
            Class.forName(DRIVER_NAME);
            System.out.println("正在连接数据库...");
            Connection con = DriverManager.getConnection(DB_URL, USER_NAME, DB_PASSWD);
            Statement sta = con.createStatement();

            String sql = "UPDATE book SET Bname = '" + new_book_inf.name + "', Bautor ='" + new_book_inf.autor +
                    "', Btype = '" + new_book_inf.type + "', Bcontent = '" + new_book_inf.content +
                    "', Bcount = " + new_book_inf.total + " WHERE Bno = '" + new_book_inf.no + "'";

            int c = sta.executeUpdate(sql);
            if(c == 1){
                System.out.println("update book success!");
                sta.close();
                con.close();
                System.out.println("数据库已安全关闭...");
                return true;
            }
            else {
                sta.close();
                con.close();
                System.out.println("数据库已安全关闭...");
                return false;
            }

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

}

