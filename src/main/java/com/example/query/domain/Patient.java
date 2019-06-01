package com.example.query.domain;

import javax.persistence.*;

@Entity
@Table(name="basicdata")
public class Patient {

    @Id
    @Column(name="pesel")
    private String pesel;

    @Column(name="name")
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column(name="hospital_ward")
    private String hospitalWard;

    @Column(name="added")
    private String added;

    private Patient(){

    }



    public Patient(String name, String lastName, String hospitalWard,String pesel, String added) {
        this.name = name;
        this.lastName = lastName;
        this.hospitalWard = hospitalWard;
        this.pesel = pesel;
        this.added=added;
    }


    public String getFirstName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.name = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHospitalWard() {
        return hospitalWard;
    }

    public void setHospitalWard(String hospitalWard) {
        this.hospitalWard = hospitalWard;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String bornYear) {
        this.added = bornYear;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }


    @Override
    public String toString() {
        return lastName + " " + name;
    }
}
