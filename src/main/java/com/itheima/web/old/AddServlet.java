package com.itheima.web.old; /**
 * @author Mendy
 * @create 2023-06-09 9:33
 */

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. accept data
        //1.1 accept brand data from front-end, JSON format
        BufferedReader br = request.getReader();
        String params = br.readLine(); // json string

        //1.2. transfer json string into Brand object
        Brand brand = JSON.parseObject(params, Brand.class);

        // 2. call service for adding
        brandService.add(brand);

        //3. response the flag of success
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
