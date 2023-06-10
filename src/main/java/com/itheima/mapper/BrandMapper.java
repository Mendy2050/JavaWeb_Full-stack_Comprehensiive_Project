package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    @Select("select * from db1.tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();


    @Insert("insert into db1.tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);


    void deleteByIds(@Param("ids") int[] ids);

    /**
     * Paginating Search
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from db1.tb_brand limit #{begin}, #{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);


    /**
     * search for total items
     * @return
     */
    @Select("select count(*) from db1.tb_brand")
    int selectTotalCount();



    /**
     * Paginating and Condition Search
     * @param begin
     * @param size
     * @return
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("brand") Brand brand);


    /**
     * search for total items based on condition
     * @return
     */
    int selectTotalCountByCondition(Brand brand);


    @Update("update db1.tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},`description`=#{description}," +
            "`status`=#{status} where id =#{id}")
    @ResultMap("brandResultMap")
    void update(Brand brand);


}
