package com.example.query.sample;

import com.example.query.DataLoaderPatient;
import com.example.query.QueryApplication;
import com.example.query.domain.Patient;
import com.example.query.domain.repository.PatientRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class PatientController {

    @FXML
    private ListView<Patient> listView;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField peselTextField;

    @FXML
    private TextField hospitalWardTextField;

    @FXML
    private TextField addedTextField;

    @FXML
    private TextField findByLastNameTextField;

    @FXML
    private Button addPatientButton;

    @FXML
    private Button deletePatientButton;

    @FXML
    private Button saveChangesButton;

    @FXML
    private Label addedLabel;

    @FXML
    private Button cancelButton;


    private final ObservableList<Patient> patientsList = FXCollections.observableArrayList();

    @Autowired
    PatientRepository patientRepository;

    public void initialize() {

        DataLoaderPatient.init();
        listView.setItems(patientsList);
        getAllPatients();
        setInformation(listView.getSelectionModel().getSelectedItem());


        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                    setInformation(newValue);
                }
        );
    }

    private void getAllPatients() {
        patientsList.setAll(DataLoaderPatient.getAllPatients());
        selectFirst();
    }

    private void selectFirst() {
        listView.getSelectionModel().selectFirst();
    }

    private void setInformation(Patient patient) {
        if (patient != null) {
            firstNameTextField.setText(patient.getFirstName());
            lastNameTextField.setText(patient.getLastName());
            peselTextField.setText(String.valueOf(patient.getPesel()));
            addedTextField.setText(String.valueOf(patient.getAdded()));
            hospitalWardTextField.setText(patient.getHospitalWard());
        } else {
            clearTextFields();
        }
    }

    @FXML
    void findButtonPressed(ActionEvent event) {
        Patient patient = DataLoaderPatient.getByLastName(findByLastNameTextField.getText());

        patientsList.setAll(patient);
        selectFirst();
    }


    @FXML
    void showAllButtonPressed(ActionEvent event) {
        getAllPatients();

    }

    @FXML
    void addPatientPressed(ActionEvent event) {

        System.out.println(addPatientButton.getText());

        if (addPatientButton.getText().equals("Dodaj pacjenta")) {
            clearTextFields();

            firstNameTextField.setEditable(true);
            lastNameTextField.setEditable(true);
            peselTextField.setEditable(true);
            hospitalWardTextField.setEditable(true);
            String style = " -fx-background-color: #CCFFFF; -fx-border-color: #006699;-fx-border-radius: 25px;-fx-background-radius: 25px";
            firstNameTextField.setStyle(style);
            lastNameTextField.setStyle(style);
            peselTextField.setStyle(style);
            hospitalWardTextField.setStyle(style);
            addedLabel.setOpacity(0);
            saveChangesButton.setOpacity(0);
            deletePatientButton.setOpacity(0);
            cancelButton.setOpacity(1);
            cancelButton.setText("Anuluj dodawanie pacjenta");
            addPatientButton.setText("Zapisz zmiany");


        } else {

            Patient patient = new Patient(firstNameTextField.getText(), lastNameTextField.getText(), hospitalWardTextField.getText(), peselTextField.getText(), LocalDate.now().toString());
            DataLoaderPatient.add(patient);

            alert(Alert.AlertType.INFORMATION, "Dodano nowego pacjenta",
                    "Próba dodania nowego pacjenta zakończona powodzeniem");


            firstNameTextField.setEditable(false);
            lastNameTextField.setEditable(false);
            peselTextField.setEditable(false);
            hospitalWardTextField.setEditable(false);
            firstNameTextField.setStyle("-fx-background-color: transparent");
            lastNameTextField.setStyle("-fx-background-color: transparent");
            peselTextField.setStyle("-fx-background-color: transparent");
            hospitalWardTextField.setStyle("-fx-background-color: transparent");
            addPatientButton.setText("Dodaj pacjenta");
            addedLabel.setOpacity(1);
            saveChangesButton.setOpacity(1);
            deletePatientButton.setOpacity(1);
            cancelButton.setOpacity(0);

            getAllPatients();
        }


    }

    @FXML
    void deletePatientButtonPressed(ActionEvent event) {


        DataLoaderPatient.remove(peselTextField.getText());
        alert(Alert.AlertType.INFORMATION, "Usunięto pacjenta",
                "Pomyślnie zakończona próba usunięcia pacjenta");
        getAllPatients();

    }

    @FXML
    void saveChangesButtonPressed(ActionEvent event) {

        String pesel = peselTextField.getText();

        if (saveChangesButton.getText().equals("Edytuj dane pacjenta")) {

            firstNameTextField.setEditable(true);
            lastNameTextField.setEditable(true);
            peselTextField.setEditable(false);
            hospitalWardTextField.setEditable(true);

            String style = " -fx-background-color: #CCFFFF; -fx-border-color: #006699;-fx-border-radius: 25px;-fx-background-radius: 25px";
            firstNameTextField.setStyle(style);
            lastNameTextField.setStyle(style);
            //peselTextField.setStyle(style);
            hospitalWardTextField.setStyle(style);
            addPatientButton.setOpacity(0);
            deletePatientButton.setOpacity(0);
            cancelButton.setOpacity(1);
            cancelButton.setText("Anuluj edycję");
            saveChangesButton.setText("Zapisz zmiany");


        } else {

            DataLoaderPatient.update(pesel, firstNameTextField.getText(), lastNameTextField.getText(), hospitalWardTextField.getText());
            alert(Alert.AlertType.INFORMATION, "Zmodyfikowano dane pacjenta",
                    "Pomyślnie zakończona modyfikacja danych pacjenta");


            firstNameTextField.setEditable(false);
            lastNameTextField.setEditable(false);
            peselTextField.setEditable(false);
            hospitalWardTextField.setEditable(false);
            firstNameTextField.setStyle("-fx-background-color: transparent");
            lastNameTextField.setStyle("-fx-background-color: transparent");
            peselTextField.setStyle("-fx-background-color: transparent");
            hospitalWardTextField.setStyle("-fx-background-color: transparent");
            saveChangesButton.setText("Edytuj dane pacjenta");
            addPatientButton.setOpacity(1);
            deletePatientButton.setOpacity(1);
            cancelButton.setOpacity(0);

            getAllPatients();
        }

    }

    @FXML
    void cancelButtonPressed(ActionEvent event) {

        if (cancelButton.getText().equals("Anuluj dodawanie pacjenta")) {

            firstNameTextField.setEditable(false);
            lastNameTextField.setEditable(false);
            peselTextField.setEditable(false);
            hospitalWardTextField.setEditable(false);
            firstNameTextField.setStyle("-fx-background-color: transparent");
            lastNameTextField.setStyle("-fx-background-color: transparent");
            peselTextField.setStyle("-fx-background-color: transparent");
            hospitalWardTextField.setStyle("-fx-background-color: transparent");
            addPatientButton.setText("Dodaj pacjenta");
            addedLabel.setOpacity(1);
            saveChangesButton.setOpacity(1);
            deletePatientButton.setOpacity(1);
            cancelButton.setOpacity(0);

            getAllPatients();
        } else {

            firstNameTextField.setEditable(false);
            lastNameTextField.setEditable(false);
            peselTextField.setEditable(false);
            hospitalWardTextField.setEditable(false);
            firstNameTextField.setStyle("-fx-background-color: transparent");
            lastNameTextField.setStyle("-fx-background-color: transparent");
            peselTextField.setStyle("-fx-background-color: transparent");
            hospitalWardTextField.setStyle("-fx-background-color: transparent");
            saveChangesButton.setText("Edytuj dane pacjenta");
            addedLabel.setOpacity(1);
            addPatientButton.setOpacity(1);
            deletePatientButton.setOpacity(1);
            cancelButton.setOpacity(0);

            getAllPatients();

        }

    }

    private void alert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearTextFields() {

        firstNameTextField.clear();
        lastNameTextField.clear();
        peselTextField.clear();
        addedTextField.clear();
        hospitalWardTextField.clear();
    }

    @FXML
    void logoutButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(root, QueryApplication.Bounds.WIDTH, QueryApplication.Bounds.HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        QueryApplication.getStage().setScene(scene);
    }

}

