package com.yyc.bunnyroom.security.user.controller;


import com.yyc.bunnyroom.security.user.model.dto.SignupDTO;
import com.yyc.bunnyroom.security.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/security/user")
public class UserController {
    @Autowired
    private UserService userService;


    // signup 페이지로 이동하는 컨트롤러
    @GetMapping("/signup")
    public ModelAndView signupUser(ModelAndView modelAndView){
        modelAndView.setViewName("/security/user/signup");
        return modelAndView;
    }


    // 회원가입 방법 선택
    @PostMapping("/signupMethod")
    public ModelAndView signupMethod(ModelAndView modelAndView, @RequestParam("userAuth") String userAuth){
        modelAndView.setViewName("/security/user/signup");
        modelAndView.addObject("userAuth", userAuth);
        return modelAndView;
    }

    @GetMapping("/signupMethod")
    public ModelAndView signupMethod(ModelAndView modelAndView){
        modelAndView.setViewName("/security/user/signupMethod");
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView signupUser(ModelAndView modelAndView, @ModelAttribute SignupDTO signupDTO){

        //유효성 검사 로직

        // 등록하는 사용자의 상태를 활성화("active")로 설정
        signupDTO.setUserStatus("active");


        // 사용자의 등록시간을 현재 시간으로 설정
        LocalDateTime currentTime = LocalDateTime.now();
        signupDTO.setUserRegistDate(currentTime);


        int result = userService.registUser(signupDTO);

        String message;

        if (result > 0){
            message = "회원가입이 완료되었습니다";
            modelAndView.setViewName("/security/auth/login");
        } else {
            message = "회원가입이 실패했습니다.";
            modelAndView.setViewName("/security/user/signup");
        }
        modelAndView.addObject("message",message);
        return modelAndView;
    }


}
