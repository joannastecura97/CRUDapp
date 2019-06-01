package com.example.query;

import com.example.query.domain.Patient;
import com.example.query.domain.Security;
import com.example.query.domain.repository.PatientRepository;
import com.example.query.domain.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoaderSecurity {


    private static SecurityRepository securityRepository;

    public DataLoaderSecurity(SecurityRepository securityRepository) {
        DataLoaderSecurity.securityRepository = securityRepository;
    }

    @PostConstruct
    public static void init() {
        securityRepository.save(new Security("root", "12345"));
        securityRepository.save(new Security("joaste", "54321"));

    }

    public static String findUser(String login){
        //securityRepository.findByPassword(password);
        Security security = securityRepository.getSecurityByLogin(login);
        if(security!=null){
            return security.getPassword();
        }
        else return null;


    }


}
