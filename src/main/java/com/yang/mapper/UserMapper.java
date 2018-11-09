package com.yang.mapper;

import com.yang.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectById(@Param("userId") Long id);
}
