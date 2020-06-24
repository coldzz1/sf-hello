package com.kele.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kele.mybatisplus.databaseO.UserDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 查询年龄大于20 的用户信息，并以每页容量为两条分页的形式返回。
     */
    @Select("select id id ,name name,age age,email email,manager_id managerId,create_time createdTime " +
            "from user  where age >#{age} limit ${pageNum},${pageSize}")
    List<UserDO> selectUserByAge(int age,int pageNum,int pageSize);

    Page selectUserByPage(Page userDOPage, @Param("age") Integer age);

}
