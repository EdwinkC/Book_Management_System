package com.zhao;

import com.zhao.admin.*;
import com.zhao.user.*;

import java.awt.*;

/**
 * 页面跳转
 */
public class Redirect {
    /**
     * 跳转到登录页
     * @param from
     */
    public static void toLoginWindow(Frame from){
        from.dispose();
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
    }

    /**
     * 跳转到注册页
     * @param from
     */
    public static void toRegisterWindow(Frame from){
        from.dispose();
        RegisterWindow registerWindow = new RegisterWindow();
        registerWindow.setVisible(true);
    }

    /**
     * 跳转到密码更改页
     * @param from
     */
    public static void toUpdatePasswordWindow(Frame from){
        from.dispose();
        UpdatePassword updatePassword = new UpdatePassword();
        updatePassword.setVisible(true);
    }

    /**
     * 跳转到用户菜单
     * @param from
     */
    public static void toUserMenuWindow(Frame from){
        from.dispose();
        UserMenuWindow userMenuWindow = new UserMenuWindow();
        userMenuWindow.setVisible(true);
    }

    /**
     * 跳转到管理员菜单
     * @param from
     */
    public static void toAdminMenuWindow(Frame from){
        from.dispose();
        AdminMenuWindow adminMenuWindow = new AdminMenuWindow();
        adminMenuWindow.setVisible(true);
    }

    /**
     * 跳转到图书检索页面
     */
    public static void toBookSearch(){
        BookSearch bookSearch = new BookSearch();
        bookSearch.setVisible(true);
    }

    /**
     * 跳转到图书借阅页面
     */
    public static void toBookBorrow(){
        BookBorrow bookBorrow = new BookBorrow();
        bookBorrow.setVisible(true);
    }

    /**
     * 跳转到图书归还页面
     */
    public static void toBookReturn(){
        BookReturn bookReturn = new BookReturn();
        bookReturn.setVisible(true);
    }

    /**
     * 跳转到图书预约页面
     */
    public static void toBookOrder(){
        BookOrder bookOrder = new BookOrder();
        bookOrder.setVisible(true);

    }

    /**
     * 跳转到图书入库页面
     */
    public static void toBookIn(){
        BookIn bookIn = new BookIn();
        bookIn.setVisible(true);
    }

    /**
     * 跳转到图书出库页面
     */
    public static void toBookOut(){
        BookOut bookOut = new BookOut();
        bookOut.setVisible(true);
    }

    /**
     * 跳转到图书更新页面
     */
    public static void toBookUpdate(){
        BookUpdate bookUpdate = new BookUpdate();
        bookUpdate.setVisible(true);
    }

    /**
     * 跳转到预约管理页面
     */
    public static void toOrderManagement(){
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.setVisible(true);
    }

    /**
     * 跳转到用户管理页面
     */
    public static void toUserManagement(){
        UserManagement userManagement = new UserManagement();
        userManagement.setVisible(true);
    }

}
