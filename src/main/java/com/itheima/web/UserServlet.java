package com.itheima.web;

import com.itheima.pojo.Brand;

import javax.servlet.annotation.WebServlet;

/**
 * @author Mendy
 * @create 2023-06-09 12:34
 */

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    public void selectAll(){
        System.out.println("user SelectAll.........");
    }

    public void add(){
        System.out.println("user add.........");
    }


}
