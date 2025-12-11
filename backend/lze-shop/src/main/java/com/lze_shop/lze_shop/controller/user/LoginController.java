package com.lze_shop.lze_shop.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.lze_shop.lze_shop.common.classes.response;
import com.lze_shop.lze_shop.dto.user.user.LoginRec;
import com.lze_shop.lze_shop.dto.user.user.LoginSend;
import com.lze_shop.lze_shop.service.user.LoginService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class LoginController {
    
    private final LoginService loginService;

    @PostMapping("/login")
    public response<LoginSend> Register(@RequestBody LoginRec request) {
        return loginService.login(request);
    }
}
