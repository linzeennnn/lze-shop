package  com.lze_shop.lze_shop.service.user;

import com.lze_shop.lze_shop.model.user.User;
import com.lze_shop.lze_shop.repository.user.UserRepo;
import com.lze_shop.lze_shop.dto.user.user.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegService {
public RegSend register(RegRec rec){
    String token=rec.getPassword()+rec.getUsername()+rec.getPhone();
    RegSend send=new RegSend();
    send.setToken(token);
    return send;
}
}
