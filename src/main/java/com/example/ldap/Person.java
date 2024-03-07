package com.example.ldap;
import lombok.Data;
import lombok.ToString;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;


import javax.naming.Name;
import java.util.jar.Attributes;


@Data
@ToString
@Entry(objectClasses = {"posixAccount"}, base = "ou=People,dc=wanstech,dc=com")

 public class Person {
 @Id
 private Name id;

@Attribute(name = "uidNumber")
private String personId;

@Attribute(name = "cn")
private String personName;

@Attribute(name = "gidNumber")
private String orgId;


}
