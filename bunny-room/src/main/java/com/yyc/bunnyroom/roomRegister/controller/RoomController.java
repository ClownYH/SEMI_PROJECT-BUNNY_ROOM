package com.yyc.bunnyroom.roomRegister.controller;

import com.yyc.bunnyroom.roomRegister.model.AppliedOptionDTO;
import com.yyc.bunnyroom.roomRegister.model.BusinessDTO;
import com.yyc.bunnyroom.roomRegister.model.RoomDTO;
import com.yyc.bunnyroom.roomRegister.model.RoomOptionDTO;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/roomRegister/room")
public class RoomController {

    @Autowired
    RoomRegisterService roomRegisterService;



    /* 모든 방을 가져오는 메소드 */
    @GetMapping("/getAllRooms/{businessNo}")
    public List<RoomDTO> getAllRooms(@PathVariable("businessNo") int businessNo) {

        System.out.println(businessNo);

        return null;
//        return roomRegisterService.getAllRooms(businessNo);
    }



//    /* 사업체 상세 페이지로 이동 */
//    @GetMapping("/businessDetail/{businessNo}")
//    public ModelAndView getBusinessDetails(@PathVariable("businessNo") int businessNo) {
//        ModelAndView modelAndView = new ModelAndView();
//
//        System.out.println("이동할 상세페이지의 사업체번호: "+businessNo);
//        BusinessDTO businessDetails =roomRegisterService.getBusinessDetails(businessNo);
//
//
//        modelAndView.setViewName("/roomRegister/detail/businessDetail");
//        modelAndView.addObject("businessDetails",businessDetails);
//        return modelAndView;
//    }














    @PostMapping("/register")
    public ResponseEntity<Integer> roomRegister(@RequestBody RoomDTO newRoom ){

        /* newRoom 에 roomRegistDate 입력*/
        ZonedDateTime currentTime = ZonedDateTime.now();
        newRoom.setRoomRegistDate(currentTime);

        /* newRoom 의 roomAvailableStatus 를 Y로 설정*/
        newRoom.setRoomAvailableStatus("Y");

        /* newRoom 상태 active*/
       newRoom.setRoomStatus("active");


        /* 사업체를 등록시킨다 */
        Integer result = roomRegisterService.roomRegister(newRoom);

        if (result != null && result > 0) {
            // 성공적으로 등록됨
            System.out.println("마지막으로 등록된 방번호: "+result);

            /*등록 성공했다면 방옵션을 등록*/

            List<AppliedOptionDTO> appliedOptions = new ArrayList<>();
            int[] optionList = newRoom.getAppliedOptions();

            for (int i = 0; i < optionList.length; i++) {
                AppliedOptionDTO appliedOption = new AppliedOptionDTO();
                appliedOption.setAppliedOption(optionList[i]);
                appliedOption.setRoomNo(result);
                appliedOptions.add(appliedOption);
            }

            System.out.println("적용되는 appliedOptions: "+appliedOptions);

            Integer result2 = roomRegisterService.appliedOptionsRegister(appliedOptions);

            if (result2 != null && result2 > 0){
                return ResponseEntity.ok(1);
            }
            else {
                // 등록 실패를 나타내는 0 반환
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
            }
        } else {
            // 등록 실패를 나타내는 0 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }

    }



}
