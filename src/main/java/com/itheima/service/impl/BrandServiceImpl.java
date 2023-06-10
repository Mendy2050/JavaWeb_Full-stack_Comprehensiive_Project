package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author Mendy
 * @create 2023-06-08 16:51
 */
public class BrandServiceImpl implements BrandService {
    //1. create SqlSessionFactory object
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {

        //2. acquire SqlSession object
        SqlSession sqlSession = factory.openSession();

        //3. acquire BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. call method
        List<Brand> brands = mapper.selectAll();

        //5. release resource
        sqlSession.close();

        return brands;
    }

    @Override
    public void add(Brand brand) {
        //2. acquire SqlSession object
        SqlSession sqlSession = factory.openSession();

        //3. acquire BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. call method
        mapper.add(brand);

        //5. commit transaction
        sqlSession.commit();

        //6. release resource
        sqlSession.close();

    }

    @Override
    public void deleteByIds(int[] ids) {
        //2. acquire SqlSession object
        SqlSession sqlSession = factory.openSession();

        //3. acquire BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. call method
        mapper.deleteByIds(ids);

        //5. commit transaction
        sqlSession.commit();

        //6. release resource
        sqlSession.close();

    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2. acquire SqlSession object
        SqlSession sqlSession = factory.openSession();

        //3. acquire BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. calculate two parameters before call related methods
        // 4.1 calculate begin index
        int begin = (currentPage - 1) * pageSize;

        //4.2 calculate search items
        int size = pageSize;

        //5. call method for searching current page's data
        List<Brand> rows = mapper.selectByPage(begin, size);

        //6. call method for searching total count
        int totalCount = mapper.selectTotalCount();

        //7. encapsulate the above two data into a PageBean
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8. release resource
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2. acquire SqlSession object
        SqlSession sqlSession = factory.openSession();

        //3. acquire BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. calculate two parameters before call related methods
        // 4.1 calculate begin index
        int begin = (currentPage - 1) * pageSize;

        //4.2 calculate search items
        int size = pageSize;

        //4.3 solve brand conditions, and add the % mark showed in Wildcard Expression
        String brandName = brand.getBrandName();
        if(brandName != null && brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }

        String companyName = brand.getCompanyName();
        if(companyName != null && companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }

        //5. call method for searching current page's data
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);

        //6. call method for searching total count
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //7. encapsulate the above two data into a PageBean
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8. release resource
        sqlSession.close();

        return pageBean;
    }

    @Override
    public void update(Brand brand) {
        //2. acquire SqlSession object
        SqlSession sqlSession = factory.openSession();

        //3. acquire BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }
}
