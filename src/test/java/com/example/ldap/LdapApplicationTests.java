package com.example.ldap;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LdapApplicationTests {

    @Autowired
    private PersonRepository personRepository;

//    @Test
//    public void findAll() throws Exception {
//        personRepository.findAll().forEach(p -> {
//            System.out.println(p);
//        });
//    }

    @Test
    void contextLoads() {


    }

}
