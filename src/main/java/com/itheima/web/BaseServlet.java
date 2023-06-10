package com.itheima.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * replace HTTPServlet
 * Dispatch methods based on the last segment of the request URL
 * @author Mendy
 * @create 2023-06-09 11:15
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. acquire request's URI
        String uri = req.getRequestURI();

        //2. acquire the last segment of the request's URI, which is also the method name
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index+1);

        //3. execute method
        //3.1 acquire BrandService/UserService Class object
        /*【this】 means
        "who calls me (the method that "this" locates, which is the method of service()), I(this) will represent who"*/
        Class<? extends BaseServlet> cls = this.getClass();


        //3.2 acquire method's Method object
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //3.3 execute method
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } {

        }







    }
}
