package com.microservices.registerservice;

import com.microservices.JPArepo.RegisterRepository;
import com.microservices.cachemaintain.RegisterCacheMaintain;
import com.microservices.entities.Register;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class RegisterService {
    @Autowired
    RegisterRepository registerRepository;

    public HashMap<String,Object> loginValidation(Register register) {
        System.out.println(register);

        String username = register.getUsername();
        String email = register.getEmail();
        Optional<Register> registerEmployeeUsername = Optional.ofNullable(RegisterCacheMaintain.registerCache.get(username));
        System.out.println("hello : "+registerEmployeeUsername);
        Optional<Register> registerEmployeeEmail = Optional.ofNullable(RegisterCacheMaintain.registerCache.get(email));

//        JSONObject jsonObject = new JSONObject();
        HashMap<String,Object>map=new HashMap<>();

        if (registerEmployeeUsername.isPresent() && RegisterCacheMaintain.registerCache.get(username).getPassword().equals(register.getPassword())) {
//            jsonObject.put("message", "valid user");
//            jsonObject.put("user", registerEmployeeUsername);

            map.put("message", "valid user");
            map.put("user", registerEmployeeUsername);
            return map;

        } else if (registerEmployeeEmail.isPresent() && RegisterCacheMaintain.registerCache.get(email).getPassword().equals(register.getPassword())) {
//            jsonObject.put("message", "valid user");
//            jsonObject.put("user", registerEmployeeEmail);

            map.put("message", "valid user");
            map.put("user", registerEmployeeEmail);
            return  map;

        } else {
//            jsonObject.put("message", "invalid user");
//            jsonObject.put("user", registerEmployeeUsername);

            map.put("message", "invalid user");
            map.put("user", null);
            return map;
        }


    }
}
