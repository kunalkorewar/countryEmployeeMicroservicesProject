package com.microservices.cachemaintain;


import com.microservices.JPArepo.RegisterRepository;
import com.microservices.entities.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class RegisterCacheMaintain {

    @Autowired
    RegisterRepository registerRepository;

    public static HashMap<String, Register> registerCache = new HashMap<>();

    public List<Register> registerList;

    @PostConstruct  //initially data loading into cache
    @Scheduled(cron = "0 0 */12 ? * *")//once in every 12hr
//    @Scheduled(cron = "0 */2 * ? * *")//once in 2 min
//    @Scheduled(cron = "* * * ? * *")//once in every sec
    public void loadCache() {
        System.out.println("registerCacheStart");
        registerList = registerRepository.findAll();
        if (!registerList.isEmpty()) {
            registerList.forEach(register -> registerCache.put(register.getUsername(), register));
            registerList.forEach(register -> registerCache.put(register.getEmail(), register));
        }
        System.out.println("registerCacheEnd");
    }
}
