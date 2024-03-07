package com.example.ldap;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class LdapApplication {

    @Resource
    private  LdapRepo ldapRepo;


    public static void main(String[] args) {
        SpringApplication.run(LdapApplication.class, args);

    }

    @GetMapping("/person")
    public List<Person> person(){
        return ldapRepo.getAllPerson();
    }

@GetMapping("/hello")
    public String hello(){
        return "\nget all: "+ldapRepo.getAllPersonNames().toString()
                +"\nget name by id=0: "+ldapRepo.getPersonNamesByOrgId("0").toString()
                +"\nget by traditional: "+ldapRepo.getAllPersonNamesWithTraditionalWay().toString();
}



}
