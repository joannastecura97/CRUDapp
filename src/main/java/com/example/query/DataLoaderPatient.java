package com.example.query;

import com.example.query.domain.Patient;
import com.example.query.domain.repository.PatientRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataLoaderPatient {


    private static PatientRepository patientRepository;

    public DataLoaderPatient(PatientRepository patientRepository) {
        DataLoaderPatient.patientRepository = patientRepository;
    }

    @PostConstruct
    public static void init() {
        patientRepository.save(new Patient("Joanna", "Stecura", "Oddzial Zakazny","97041002364","2019-05-20"));
        patientRepository.save(new Patient("Kamila", "Telega", "Oddzial Chorob Wewnetrznych","97073108766", "2019-05-15"));
        patientRepository.save(new Patient("Janusz", "Kubica", "Oddzial Dzieciecy","05021012161","2019-05-10"));
        patientRepository.save(new Patient("Kamil", "Nowak", "Oddzial Kardiologiczny","92093004567","2019-05-18"));
    }

    public static List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }
    private static  Patient getPatientByPesel(String pesel){
        return patientRepository.getByPesel(pesel);
    }
    public  static Patient getByLastName(String lastname){
        return patientRepository.getByLastName(lastname);
    }


    public static void add(Patient patient){
        patientRepository.save(patient);
    }
    public static void remove(String pesel){
        patientRepository.deleteById(pesel);
    }

    public static void update(String pesel, String name, String lastName, String hospitalWard){
        Patient patient = getPatientByPesel(pesel);
        patient.setFirstName(name);
        patient.setLastName(lastName);
        patient.setHospitalWard(hospitalWard);
        patientRepository.save(patient);

    }
}
