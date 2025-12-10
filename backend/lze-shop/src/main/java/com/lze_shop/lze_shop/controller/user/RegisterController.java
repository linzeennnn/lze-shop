package com.lze_shop.lze_shop.controller.user;
import com.lze_shop.lze_shop.common.classes.response;
import com.lze_shop.lze_shop.dto.user.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.lze_shop.lze_shop.service.user.*;
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor 
public class RegisterController {

    private final RegService regService;

    @PostMapping("/register")
    public response<RegSend> Register(@RequestBody RegRec request) {
        return regService.register(request);
    }
}
