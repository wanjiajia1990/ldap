package com.example.ldap;
import lombok.Data;
import lombok.ToString;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;


@Data
@ToString
@Entry(objectClasses = {"posixAccount"}, base = "ou=People,dc=wanstech,dc=com")

 public class Person {

@Attribute(name = "uidNumber")
private String personId;

@Attribute(name = "cn")
private String personName;

@Attribute(name = "gidNumber")
private String orgId;


}
