package com.lze_shop.lze_shop.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lze_shop.lze_shop.common.classes.response;
import com.lze_shop.lze_shop.dto.user.user.LoginRec;
import com.lze_shop.lze_shop.dto.user.user.LoginSend;
import com.lze_shop.lze_shop.model.user.User;
import com.lze_shop.lze_shop.repository.user.UserRepo;
import com.lze_shop.lze_shop.common.util.*;
@Service
public class LoginService {
    @Autowired
    private UserRepo userRepo;
    public response<LoginSend> login(LoginRec rec){
        response<LoginSend> res;
        String phone=rec.getPhone();
        String password=rec.getPassword();
        if(!userRepo.existsByPhone(phone)){
            res=new response<>(400,"手机号不存在",null);
            return res;
        }
        User user=userRepo.findByPhone(phone);
        if(!user.getPassword().equals(password)){
            res=new response<>(400,"密码错误",null);
            return res;
        }
        String token=Auth.genJwt(user.getId(), user.getJti(), user.getExp());
        LoginSend send=new LoginSend();
        send.setToken(token);
        send.setUsername(user.getUsername());
        res=new response<>(200,"登录成功",send);
        return res;
    }
    
}
