package com.yyc.bunnyroom.black.model.dao;

import com.yyc.bunnyroom.black.model.dto.BlackDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlackDAO {

    int toBlacklist(String email);

    List<BlackDTO> showAll();

    int addBlacklist(int userNo, String auth, String reason, String registDate, String sentenceTime);

    int modifyBlacklist(int userNo, String auth, String reason, String updateDate, String sentenceTime);

    Object searchBlackByEmail(String email);

    List<BlackDTO> showBlacklistByEmail(String param);

    List<BlackDTO> showBlacklistByNickname(String param);

    List<BlackDTO> showBlacklistByPhone(String param);

    int restoreAuth(String email, String auth);

    int disableBlack(int userNo, String updateDate);
}
