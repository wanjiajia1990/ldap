package com.example.ldap;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component
public class LdapRepo {

    @Resource
    private LdapTemplate ldapTemplate;

    public List<Person> getAllPerson(){
        return ldapTemplate.search(query().where("objectclass").is("posixAccount"), new AttributesMapper<Person>() {
            @Override
            public Person mapFromAttributes(Attributes attributes)  throws NamingException {
                Person person= new Person();
                person.setPersonId((String)attributes.get("uidNumber").get());
                person.setPersonName((String)attributes.get("cn").get());
                person.setOrgId((String)attributes.get("gidnumber").get());
                return person;
            }
        });
    }

    public List<String> getAllPersonNames() {
        return ldapTemplate.search(
                query().where("objectclass").is("posixAccount"), (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
    }

    public List<String> getPersonNamesByOrgId(String orgId) {
        LdapQuery query = query()
                .where("objectclass").is("posixAccount")
                .and("gidnumber").is(orgId);
        return ldapTemplate.search(query, (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
    }

    public List<String> getAllPersonNamesWithTraditionalWay() {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://rhel7s02:389/ou=People,dc=wanstech,dc=com");
        env.put(Context.SECURITY_PRINCIPAL, "cn=Manager,dc=wanstech,dc=com");
        env.put(Context.SECURITY_CREDENTIALS, "wjj345199");
        List<String> list = new LinkedList<String>();
        try{
            DirContext ctx;
        ctx = new InitialDirContext(env);

        NamingEnumeration results = null;

        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        results = ctx.search("", "(objectclass=posixAccount)", controls);
        while (results.hasMore()) {
            SearchResult searchResult = (SearchResult) results.next();
            Attributes attributes = searchResult.getAttributes();
            Attribute attr = attributes.get("cn");
            String cn = attr.get().toString();
            list.add(cn);
        }

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
