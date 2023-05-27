package com.sparta.ourportfolio.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.ourportfolio.common.dto.ResponseDto;
import com.sparta.ourportfolio.user.dto.LoginRequestDto;
import com.sparta.ourportfolio.user.dto.SignupRequestDto;
import com.sparta.ourportfolio.user.dto.UpdateUserRequestDto;
import com.sparta.ourportfolio.user.dto.UserDto;
import com.sparta.ourportfolio.user.service.KakaoService;
import com.sparta.ourportfolio.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", exposedHeaders = "Authorization")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final KakaoService kakaoService;


    // 회원가입
    @PostMapping("/signup")
    public ResponseDto<HttpStatus> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseDto<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }

    // 회원 조회
    @GetMapping ("/{id}")
    public ResponseDto<UserDto> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // 회원 정보 수정

    // 카카오 로그인
    @GetMapping("/kakao")
    public ResponseDto<String> kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        return kakaoService.kakaoLogin(code, response);
    }
}
