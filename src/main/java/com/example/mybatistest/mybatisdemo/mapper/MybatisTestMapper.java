package com.example.mybatistest.mybatisdemo.mapper;

import com.example.mybatistest.mybatisdemo.bean.MybatisTest;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface MybatisTestMapper {
    @Select("SELECT * FROM mybatis_test WHERE NAME = #{name}")
    MybatisTest findByName(@Param("name") String name);

    @Insert("INSERT INTO mybatis_test(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Insert("INSERT INTO mybatis_test(NAME, AGE) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("INSERT INTO mybatis_test(NAME, AGE) VALUES(#{name}, #{age})")
    int insertByUser(MybatisTest mybatisTest);

    @Update("UPDATE mybatis_test SET age=#{age} WHERE name=#{name}")
    void update(MybatisTest mybatisTest);

    @Delete("DELETE FROM mybatis_test WHERE id =#{id}")
    void delete(Long id);

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT name, age FROM mybatis_test")
    List<MybatisTest> findAll();
}