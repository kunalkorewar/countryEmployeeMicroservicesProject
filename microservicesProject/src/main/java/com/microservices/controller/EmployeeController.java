package com.microservices.controller;

import com.microservices.JPArepo.EmployeeRepository;
import com.microservices.cachemaintain.EmployeeCacheMaintain;
import com.microservices.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    //save employee operation
    @PostMapping("/addEmployee")
    public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {

        Date createdDate = new Date();
        employee.setCreateddtm(createdDate);
        employee.setUpdateddtm(createdDate);

        employeeRepository.save(employee);
        EmployeeCacheMaintain.employeeCache.put(employee.getId(), employee);
        return new ResponseEntity<String>("New Employee Save With EmployeeId " + employee.getId() + " Successfully..", HttpStatus.CREATED);
    }

    //update employee operation
    @PutMapping("/updateEmployee")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {

        Optional<Employee> oldEmployee = Optional.ofNullable(EmployeeCacheMaintain.employeeCache.get(employee.getId()));

        if (oldEmployee.isPresent()) {
            Date updatedDate = new Date();
            employee.setCreateddtm(oldEmployee.get().getCreateddtm());//this is for cache DB me vaise bhi old jo h vahi rhenga bcz it is not updatable
            employee.setUpdateddtm(updatedDate);

            employeeRepository.save(employee);
            EmployeeCacheMaintain.employeeCache.put(employee.getId(), employee);
            return new ResponseEntity<String>("EmployeeId " + employee.getId() + " Update Successfully..", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("EmployeeId " + employee.getId() + " Not Present..", HttpStatus.OK);
    }

    //get all employee
    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return new ResponseEntity<List<Employee>>(EmployeeCacheMaintain.employeeCache.values().stream().collect(Collectors.toList()), HttpStatus.OK);
    }

    //get  employeeById
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = Optional.ofNullable(EmployeeCacheMaintain.employeeCache.get(id));
        if (employee.isPresent()) {
            return new ResponseEntity<Optional<Employee>>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<String>("EmployeeId " + id + " Not Present..", HttpStatus.OK);
    }

    //delete employeeById
    @DeleteMapping("/deleteEmployeeById/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = Optional.ofNullable(EmployeeCacheMaintain.employeeCache.get(id));
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            EmployeeCacheMaintain.employeeCache.remove(id);
            return new ResponseEntity<String>("EmployeeId " + id + " Deleted Successfully..", HttpStatus.OK);
        }
        return new ResponseEntity<String>("EmployeeId " + id + " Not Present..", HttpStatus.OK);

    }
}
