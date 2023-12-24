package com.yyc.bunnyroom.adminBusiness.controller;

import com.yyc.bunnyroom.adminBusiness.model.dto.AdminBusinessDTO;
import com.yyc.bunnyroom.adminBusiness.service.AdminBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/business")
@RequiredArgsConstructor
public class AdminBusinessController {

    private final AdminBusinessService adminBusinessService;

    /**
     * 관리자 페이지 중 업체 관리 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping(value = {"","/"})
    public String business(){
        // 관리자 업체 관리 페이지로 이동
        return "admin/business";
    }

    @GetMapping("/search")
    public String businessSearcher(@RequestParam("mode")String mode,
                                   @RequestParam("target")String target, Model model){

        List<AdminBusinessDTO> BusinessList;

//        if(mode.equals("businessName")){
//            // 업체명 검색
//            BusinessList = adminBusinessService.nameSearcher(target);
//        }else if(mode.equals("category")){
//            // 카테고리 검색
//            BusinessList = adminBusinessService.categorySearcher(target);
//        }else if(mode.equals("RegistNo")) {
//            // 등록증 번호 검색
//            BusinessList = adminBusinessService.RegistNoSearcher(target);
//        } else if(mode.equals("email")) {
//            // 이메일 검색
//            BusinessList = adminBusinessService.emailSearcher(target);
//        }else if(mode.equals("nickname")) {
//            // 닉네임 검색
//            BusinessList = adminBusinessService.nicknameSearcher(target);
//        }else if(mode.equals("address")){
//            // 주소 검색
//            BusinessList = adminBusinessService.addressSearcher(target);
//        }else if(mode.equals("phone")) {
//            // 연락처 검색
//            BusinessList = adminBusinessService.phoneSearcher(target);
//        }else if(mode.equals("inactive")){
//            // 삭제된 내용 검색
//            BusinessList = adminBusinessService.inactiveSearcher();
//        }else {
            // 전체 검색
            BusinessList = adminBusinessService.allSearcher();
//            }

        model.addAttribute("BusinessList", BusinessList);

        return "admin/business";
    }
}
