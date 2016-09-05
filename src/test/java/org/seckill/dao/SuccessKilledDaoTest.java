package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by zfx on 2016/8/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest{

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        Long id = 1001L;
        Long userPhone = 18713575585L;
        int insertCount = successKilledDao.insertSuccessKilled(id,userPhone);
        System.out.println(insertCount);
    }

    @Test
    public void queryByIdwithSeckill() throws Exception {
        Long id = 1001L;
        Long userPhone = 18713575585L;
        SuccessKilled successKilled = successKilledDao.queryByIdwithSeckill(id,userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}