package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zfx on 2016/8/31.
 * 业务接口：站在“使用者”角度设计接口，三个方面：
 * 1.方法定义粒度（层次非常明确）。eg：秒杀接口肯定有一个接口执行秒杀，传入执行秒杀所需要的参数
 * 而不去关注怎么减库存，怎么去添加用户的购买行为，这些应该是实现
 * 2.参数：越简练，越直接越好
 * 3.返回类型：return类型一定要友好
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getSeckillById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口的地址
     * 否则输出系统时间和秒杀时间
     * 所以在dto是建一个类，暴露秒杀地址
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException,SeckillCloseException,RepeatKillException;

    /**
     * 执行秒杀操作 by 存储过程
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
}
