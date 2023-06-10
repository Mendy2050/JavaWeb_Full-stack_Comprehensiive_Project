package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author Mendy
 * @create 2023-06-09 12:34
 */

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. call selectAll service for search
        List<Brand> brands = brandService.selectAll();

        //2. transfer the data into JSON format
        String jsonString = JSON.toJSONString(brands);

        //3. write out data
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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


    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1. accept data
        //1.1 accept brand data from front-end, JSON format
        BufferedReader br = request.getReader();
        String params = br.readLine(); // json string

        //1.2. transfer json string into int array
        int[] ids = JSON.parseObject(params, int[].class);

        // 2. call service for adding
        brandService.deleteByIds(ids);

        //3. response the flag of success
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. accept parameters from frontend, currentPage and pageSize  url?currentPage=1&pageSize=5   GET
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2. call service for paginating search
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //3. transfer the data into JSON format
        String jsonString = JSON.toJSONString(pageBean);

        //4. write out and send back data to the frontend
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. accept parameters from frontend, currentPage and pageSize  url?currentPage=1&pageSize=5   GET
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //1.1 accept brand object - which is the search condition - from front-end, JSON format
        BufferedReader br = request.getReader();
        String params = br.readLine(); // json string

        //1.2. transfer json string into brand object
        Brand brand = JSON.parseObject(params, Brand.class);


        //2. call service for paginating and condition search
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        //3. transfer the data into JSON format
        String jsonString = JSON.toJSONString(pageBean);

        //4. write out and send back data to the frontend
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Handling character encoding issues
        request.setCharacterEncoding("utf-8");
        //1. acquire input stream (POST), to accept brand data - JSON string
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //2. transfer JSON string to brand object
        Brand brand = JSON.parseObject(params, Brand.class);
        /*
        问：为什么不直接在【brandService.update(brand); 】中传入这个【brandBrand brand = JSON.parseObject(params, Brand.class); 】
        而是先取出属性、再把属性装入一个新的brand对象呢？

        答：根据给出的代码片段，
        确实没有看到直接使用Brand brand = JSON.parseObject(params, Brand.class);
        然后把brand传入brandService.update(brand);的明显问题。
        这样做实际上是更加清晰和简洁的。

        此段代码可能是出于某种原因（可能是程序员的个人编程风格或者之前的某种特殊需求）将对象的属性单独取出再重新封装。
        但是，这种方式有时候在某些情况下是有用的。
        比如，当你只想更新某些特定的属性，而不是整个对象，这种方式就很有用。
        这种方式也可能是为了清理或格式化数据。例如，你可能想清理字符串数据，或者转换日期和时间格式等。

        但是，在你的这个例子中，没有看到上述的这些特殊需求，所以直接使用brand对象是更好的选择。*/

        //3. get each attribute of the brand object
        Integer id = brand.getId();
        String brandName = brand.getBrandName();
        String companyName = brand.getCompanyName();
        String description = brand.getDescription();
        Integer ordered = brand.getOrdered();
        Integer status = brand.getStatus();

        //3. create a new encapsulate
        Brand b = new Brand();
        b.setId(id);
        b.setBrandName(brandName);
        b.setCompanyName(companyName);
        b.setDescription(description);
        b.setOrdered(ordered);
        b.setStatus(status);

        //2. 调用service更新
        brandService.update(brand);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }



}
