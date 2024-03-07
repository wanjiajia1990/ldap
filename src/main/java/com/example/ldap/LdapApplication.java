package com.example.ldap;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.LdapName;
import java.util.List;

@SpringBootApplication
@EnableLdapRepositories
@RestController
public class LdapApplication {

    @Autowired
    private  LdapRepo ldapRepo;


    public static void main(String[] args) {
        SpringApplication.run(LdapApplication.class, args);

    }

    @GetMapping("/person")
    public List<Person> person(){
        return ldapRepo.getAllPerson();
    }

    @PostMapping("/personbydn")
    public Object personbydn(@RequestParam String dn){
        return ldapRepo.findPerson(dn);

    }

    @GetMapping("/search")
    public String hello(){
        return "\nget all: "+ldapRepo.getAllPersonNames().toString()
                +"\nget name by id=0: "+ldapRepo.getPersonNamesByOrgId("0").toString()
                +"\nget by traditional: "+ldapRepo.getAllPersonNamesWithTraditionalWay().toString();
//                +"\n get by ldapClient: "+personRepoimpl.getAllPersonNames().toString();
}
    @PostMapping("/auth")
    public boolean auth(@RequestParam String username, @RequestParam String passwd){

        return ldapRepo.auth(username,passwd);
    }

    @PostMapping("/add")
    public LdapName add(@RequestBody Person person){
        return ldapRepo.buildDn(person);
    }




}
