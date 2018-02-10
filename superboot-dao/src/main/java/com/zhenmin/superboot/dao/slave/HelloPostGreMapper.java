package com.zhenmin.superboot.dao.slave;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HelloPostGreMapper {

    @Select("select name from COMPANY limit 1")
    String select();
}
