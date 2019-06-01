package com.example.query.domain.repository;

import com.example.query.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {

    Patient getByPesel(String pesel);

    Patient getByLastName(String lastname);
}
