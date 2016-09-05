package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;
import org.springframework.stereotype.Repository;

/**
 * Created by zfx on 2016/8/29.
 */

public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param seckilledId
     * @param userPhone
     * @return   插入的行数，若为0，失败
     */
    int insertSuccessKilled(@Param("seckilledId") long seckilledId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdwithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
