package com.yyc.bunnyroom.signup.dao;

import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import com.yyc.bunnyroom.signup.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int registUser(SignupDTO signupDTO);

    LoginUserDTO findByUserEmail(String userEmail);
}
