package com.yunqing.demomybatismbg.mapper;

import com.yunqing.demomybatismbg.entity.User;
import com.yunqing.demomybatismbg.entity.UserExample;
import java.util.List;

import com.yunqing.demomybatismbg.vo.UserVo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<UserVo> pageHelperSelect();
}