package com.microservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fname;
    private String lname;

    @Column(unique = true)
    private String username;
    private String mobilenum;

    @Column(unique = true)
    private String email;
    private String password;
    private String gender;
    private Date createdate;

}
