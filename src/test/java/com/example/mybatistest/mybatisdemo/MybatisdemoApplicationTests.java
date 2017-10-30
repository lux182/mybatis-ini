package com.example.mybatistest.mybatisdemo;

import com.example.mybatistest.mybatisdemo.bean.MybatisTest;
import com.example.mybatistest.mybatisdemo.mapper.MybatisTestMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisdemoApplicationTests {

    @Autowired
    private MybatisTestMapper mybatisTestMapper;

    @Autowired
    private SqlSession sqlSession;

    @Test
    public void contextLoads() {
    }

    @Test
    @Transactional
    @Rollback
    public void findByName() throws Exception {
        mybatisTestMapper.insert("AAA", 20);
        MybatisTest u = mybatisTestMapper.findByName("AAA");
        Assert.assertEquals(20, u.getAge().intValue());
    }

    @Test
    @Transactional
    @Rollback
    public void testUserMapper() throws Exception {
        // insert一条数据，并select出来验证
        mybatisTestMapper.insert("AAA", 20);
        MybatisTest u = mybatisTestMapper.findByName("AAA");
        Assert.assertEquals(20, u.getAge().intValue());
        // update一条数据，并select出来验证
        u.setAge(30);
        mybatisTestMapper.update(u);
        u = mybatisTestMapper.findByName("AAA");
        Assert.assertEquals(30, u.getAge().intValue());
        // 删除这条数据，并select验证
        mybatisTestMapper.delete(u.getId());
        u = mybatisTestMapper.findByName("AAA");
        Assert.assertEquals(null, u);
    }

}
