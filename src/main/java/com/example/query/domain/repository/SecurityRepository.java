package com.example.query.domain.repository;

import com.example.query.domain.Security;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepository extends JpaRepository<Security, String> {


    Security getSecurityByLogin(String login);
}
