package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;

import java.util.List;

/**
 * @author Mendy
 * @create 2023-06-08 16:50
 */
public interface BrandService {

    List<Brand> selectAll();

    void add(Brand brand);

    void deleteByIds(int[] ids);


    /**
     * Paginating search
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    /**
     * Paginating and condition search
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);

    /**
     * update data
     * @param brand
     */
    void update(Brand brand);

}
