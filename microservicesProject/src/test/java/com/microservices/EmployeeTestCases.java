package com.microservices;

import com.microservices.entities.Country;
import com.microservices.entities.Employee;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTestCases {
    private static StringBuilder output = new StringBuilder("");

    //post APITest
    @Order(1)
    @Test
    public void saveEmployeeTest() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String baseURL = "http://localhost:8080/addEmployee";
        URI uri = new URI(baseURL);
        Employee employee = new Employee();
        employee.setName("sam");
        employee.setPhoneno("789456");
        employee.setDepartmentit("it");
        employee.setStatus("inactive");
        employee.setCreatedby("kunal");
        employee.setUpdatedby("kunal");
        employee.setCountry(new Country(1, "india"));
        ResponseEntity<String> result = restTemplate.postForEntity(uri, employee, String.class);
        Assertions.assertEquals(201, result.getStatusCodeValue());
        output.append("1");
        System.out.println("first case..");
    }

    //put APITest
    @Order(2)
    @Test
    public void updateEmployeeTest() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String baseURL = "http://localhost:8080/updateEmployee";
        URI uri = new URI(baseURL);
        Employee employee = new Employee();
        employee.setId(6);
        employee.setName("ganesh");
        employee.setPhoneno("852369");
        employee.setDepartmentit("it");
        employee.setStatus("active");
        employee.setCreatedby("kunal");
        employee.setUpdatedby("kunal");
        employee.setCountry(new Country(1, "india"));
        restTemplate.put(uri, employee);
        Assertions.assertEquals(200 | 201, 200 | 201);
        output.append("2");
        System.out.println("second case..");
    }

    // getAllEmployee
    @Order(3)
    @Test
    public void getAllEmployeeTest() throws URISyntaxException {
        RestTemplate restTemplate=new RestTemplate();
        String baseURL="http://localhost:8080/getAllEmployee";
        URI uri=new URI(baseURL);
        ResponseEntity<String> result=restTemplate.getForEntity(uri,String.class);
        Assertions.assertEquals(200,result.getStatusCodeValue());
        output.append("3");
        System.out.println("third case..");
    }

    // getEmployeeById
    @Order(4)
    @Test
    public void getEmployeeByIdTest() throws URISyntaxException {
        RestTemplate restTemplate=new RestTemplate();
        String baseURL="http://localhost:8080/getEmployeeById/7";
        URI uri=new URI(baseURL);
        ResponseEntity<String> result=restTemplate.getForEntity(uri,String.class);
        Assertions.assertEquals(200,result.getStatusCodeValue());
        output.append("4");
        System.out.println("fourth case..");
    }

    // deleteEmployeeById
    @Order(5)
    @Test
    public void deleteEmployeeByIdTest() throws URISyntaxException {
        RestTemplate restTemplate=new RestTemplate();
        String baseURL="http://localhost:8080/deleteEmployeeById/12";
        URI uri=new URI(baseURL);
        restTemplate.delete(uri);
        Assertions.assertEquals(200,200);
        output.append("5");
        System.out.println("fifth case..");
    }
    @AfterAll
    public static void assertOutput() {
        System.out.println(output.toString());
        Assertions.assertEquals("12345", output.toString());
    }
}
//Alphanumeric Order => @TestMethodOrder(MethodOrderer.MethodName.class)
//@Order Annotation => @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//Random Order => @TestMethodOrder(MethodOrderer.Random.class)