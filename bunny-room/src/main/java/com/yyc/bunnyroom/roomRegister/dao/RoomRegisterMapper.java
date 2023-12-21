package com.yyc.bunnyroom.roomRegister.dao;

import com.yyc.bunnyroom.roomRegister.model.BusinessCategoryDTO;
import com.yyc.bunnyroom.roomRegister.model.BusinessDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomRegisterMapper {
    int businessRegister(BusinessDTO businessDTO);

    List<BusinessCategoryDTO> selectAllBusinessCategory();
}
