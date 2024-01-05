package com.yyc.bunnyroom.roomRegister.service;

import com.yyc.bunnyroom.reservation.model.KeywordDTO;
import com.yyc.bunnyroom.roomRegister.dao.RoomRegisterMapper;
import com.yyc.bunnyroom.roomRegister.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoomRegisterService {

    @Autowired
    RoomRegisterMapper roomRegisterMapper;

    //Check
    public boolean checkBusinessRegistNo(int businessRegistNo) {
        return roomRegisterMapper.checkBusinessRegistNo(businessRegistNo);
    }




    //Register
    public Integer businessRegister(BusinessDTO businessDTO) {
        return roomRegisterMapper.businessRegister(businessDTO);
    }
    public int closedDaysRegister(List<ClosedDayDTO> list) {
        return roomRegisterMapper.closedDaysRegister(list);
    }

    public Integer roomRegister(RoomDTO newRoom) {
        Integer generatedRoomNo = roomRegisterMapper.roomRegister(newRoom);
        System.out.println("새로 생성된 방번호: "+generatedRoomNo);
        return generatedRoomNo;
    }

    public int holidaysRegister(List<HolidayDTO> holidayList) {
        return roomRegisterMapper.holidaysRegister(holidayList);
    }

    public Integer appliedOptionsRegister(List<AppliedOptionDTO> appliedOptions) {
        return roomRegisterMapper.appliedOptionsRegister(appliedOptions);
    }

    public Integer addTimeSchedule(List<TimeUnitScheduleDTO> timeUnitScheduleList) {
        return roomRegisterMapper.addTimeSchedule(timeUnitScheduleList);
    }




    //SelectAll
    public List<BusinessCategoryDTO> selectAllBusinessCategory() {
        return roomRegisterMapper.selectAllBusinessCategory();
    }

    public List<RoomOptionDTO> selectAllRoomOptions() {
        return roomRegisterMapper.selectAllRoomOptions();
    }

    public List<BusinessDTO> getAllBusiness(int ownerNo) {
        return roomRegisterMapper.getAllBusiness(ownerNo);
    }

    public List<AppliedOptionDTO> getAppliedOptions(int roomNo) {
        return roomRegisterMapper.getAppliedOptions(roomNo);
    }

    public List<RoomDTO> getRoomsByBusinessNo(int businessNo) {
        return roomRegisterMapper.getRoomsByBusinessNo(businessNo);
    }

    public List<ClosedDayDTO> getAllClosedDays(int businessNo) {
        return roomRegisterMapper.getAllClosedDays(businessNo);
    }

    public List<RoomDTO> getRoomsByCategory(String businessCategoryName) {
        return roomRegisterMapper.getRoomsByCategory(businessCategoryName);
    }

    public List<RoomDTO> getRoomsByKeyword(KeywordDTO keywordDTO) {
        return roomRegisterMapper.getRoomsByKeyword(keywordDTO);
    }

    public List<RoomDTO> getAllRooms() {
        return roomRegisterMapper.getAllRooms();
    }

    public List<HolidayDTO> getHolidaysByBusinessNo(int businessNo) {
        return roomRegisterMapper.getHolidaysByBusinessNo(businessNo);
    }

    public List<TimeUnitScheduleDTO> getTimeUnitsByBusinessNo(int businessNo) {
        return roomRegisterMapper.getTimeUnitsByBusinessNo(businessNo);
    }




    //Select
    public BusinessDTO getBusinessDetails(int businessNo) {
        return roomRegisterMapper.getBusinessDetails(businessNo);
    }

    public RoomDTO getRoomDetails(int roomNo) {
        return roomRegisterMapper.getRoomDetails(roomNo);
    }


    public BusinessDTO getBusinessInfoByRoomNo(int roomNo) {
        return roomRegisterMapper.getBusinessInfoByRoomNo(roomNo);
    }


    public List<RoomDTO> getLatestRoomsByLimit10() {
        return roomRegisterMapper.getLatestRoomsByLimit10();
    }

    public int updateImage(Integer roomNo, Integer imageNo) {


        Map<String, Object> data = new HashMap<>();
        data.put("roomNo", roomNo);
        data.put("imageNo", imageNo);

        return roomRegisterMapper.updateImage(data);
    }

    public int getImageByRoomNo(int roomNo) {
        return roomRegisterMapper.getImageByRoomNo(roomNo);
    }
}
