package org.edupro.webapi.mapper;

import org.apache.ibatis.annotations.*;
import org.edupro.webapi.lookup.model.LookupRes;

import java.util.List;

@Mapper
public interface LookupMapper {
    @Select("SELECT * FROM t_lookup WHERE LKID = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "group", column = "groups"),
            @Result(property = "code", column = "code"),
            @Result(property = "name", column = "name"),
            @Result(property = "position", column = "position"),
            @Result(property = "status", column = "status",javaType = String.class),
    })
    LookupRes getById(@Param("id") String id);

    @Select("SELECT * FROM t_lookup WHERE LKGRP = #{group}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "group", column = "groups"),
            @Result(property = "code", column = "code"),
            @Result(property = "name", column = "name"),
            @Result(property = "position", column = "position"),
    })
    List<LookupRes> getByGroup(@Param("group") String group);
}
