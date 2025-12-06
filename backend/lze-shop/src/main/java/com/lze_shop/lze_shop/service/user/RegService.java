package  com.lze_shop.lze_shop.service.user;

import com.lze_shop.lze_shop.dto.user.user.*;
import com.lze_shop.lze_shop.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lze_shop.lze_shop.repository.user.*;
import com.lze_shop.lze_shop.common.util.*;
@Service
public class RegService {
    @Autowired
    private UserRepo userRepo;
public RegSend register(RegRec rec){
    User newUser=new User();
    long id=Auth.getUid();
    String jti=Auth.genJti();
    long exp=Auth.getExp();
    long now=System.currentTimeMillis()/1000 ;
    newUser.setId(id);
    newUser.setExp(exp);
    newUser.setUpdatedAt(now);
    newUser.setCreatedAt(now);
    newUser.setPhone(rec.getPhone());
    newUser.setPassword(rec.getPassword());
    newUser.setUsername(rec.getUsername());
    newUser.setJti(jti);
    userRepo.save(newUser);
    String token=Auth.genJwt(id, jti, exp);
    RegSend send=new RegSend();
    send.setToken(token);
    return send;
}
}
