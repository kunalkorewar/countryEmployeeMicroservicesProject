package com.microservices.cachemaintain;

import com.microservices.JPArepo.EmployeeRepository;
import com.microservices.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class EmployeeCacheMaintain {

    @Autowired
    EmployeeRepository employeeRepository;
    public static HashMap<Integer, Employee> employeeCache = new HashMap<>();
    public List<Employee> employeeList;

    @PostConstruct //initially data loading into cache
    @Scheduled(cron = "0 0 */12 ? * *")//once in every 12hr
//    @Scheduled(cron = "0 */2 * ? * *")//once in 2 min
//    @Scheduled(cron = "* * * ? * *")//once in every sec
    public void loadCache() {
        System.out.println("employeeCacheStart");
        employeeList = employeeRepository.findAll();
        if (!employeeList.isEmpty()) {
            employeeList.forEach(employee -> employeeCache.put(employee.getId(), employee));
        }
        System.out.println("employeeCacheEnd");
    }
}
