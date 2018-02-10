package com.zhenmin.superboot.dao.master.repository;

import com.zhenmin.superboot.dao.master.entity.SuperBootEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xuzhenmin
 * @ClassName: SuperBootMapper
 */
@Mapper
public interface SuperBootMapper {

    @Select("SELECT * FROM XXX WHERE order_id=#{orderId} LIMIT 1")
    SuperBootEntity selectByOrderId(@Param("orderId") Long orderId);

}