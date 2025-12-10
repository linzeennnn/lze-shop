package  com.lze_shop.lze_shop.service.user;

import com.lze_shop.lze_shop.dto.user.user.*;
import com.lze_shop.lze_shop.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lze_shop.lze_shop.repository.user.*;
import com.lze_shop.lze_shop.common.util.*;
import com.lze_shop.lze_shop.common.classes.response;
@Service
public class RegService {
    @Autowired
    private UserRepo userRepo;
public response<RegSend> register(RegRec rec){
    response<RegSend> res;
    String phone=rec.getPhone();
    if(userRepo.existsByPhone(phone)){
        res=new response<>(400,"手机号已被注册",null);
        return res;
    }
    User newUser=new User();
    long id=Auth.getUid();
    String jti=Auth.genJti();
    long exp=Auth.getExp();
    long now=System.currentTimeMillis()/1000 ;
    newUser.setId(id);
    newUser.setExp(exp);
    newUser.setUpdatedAt(now);
    newUser.setCreatedAt(now);
    newUser.setPhone(phone);
    newUser.setPassword(rec.getPassword());
    newUser.setUsername(rec.getUsername());
    newUser.setJti(jti);
    userRepo.save(newUser);
    String token=Auth.genJwt(id, jti, exp);
    RegSend send=new RegSend();
    send.setToken(token);
    send.setUsername(rec.getUsername());
    res=new response<>(200,"注册成功",send);
    return res;
}
}
