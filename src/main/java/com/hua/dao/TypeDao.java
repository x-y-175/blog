package com.hua.dao;

import com.hua.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {

    //  插入ID和name
    int saveType(Type type);

    //  通过ID查询Type
    Type getTypeById(Long id);

    //  通过name查询Type
    Type getTypeByName(String name);

    //  查询所有的Type包括Blog表里的id，title，type_id
    List<Type> getAllType();

    //  查询所有的Type
    List<Type> getAdminType();

    //  根据ID删除Type
    int deleteType(Long id);

    //  根据ID更新Type
    int updateType(Type type);
}
