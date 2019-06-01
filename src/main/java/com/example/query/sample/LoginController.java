package com.example.query.sample;

import com.example.query.DataLoaderSecurity;
import com.example.query.QueryApplication;
import com.example.query.domain.repository.SecurityRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginController {


        @FXML
        private BorderPane background;

        @FXML
        private TextField loginTextField;

        @FXML
        private TextField passwordTextField;

        @Autowired
        SecurityRepository securityRepository;


        public void initialize() {
                DataLoaderSecurity.init();
        }

        @FXML
        void loginButtonPressed(ActionEvent event) throws IOException {


                String password = DataLoaderSecurity.findUser(loginTextField.getText());

                System.out.println(password);
                if(password!=null)
                        if (password.equals(passwordTextField.getText())) {
                                Parent root = FXMLLoader.load(getClass().getResource("/Data.fxml"));
                                Scene scene = new Scene(root, QueryApplication.Bounds.WIDTH, QueryApplication.Bounds.HEIGHT);
                                scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
                                QueryApplication.getStage().setScene(scene);
                        } else {
                                loginTextField.setText("");
                                passwordTextField.setText("");
                                alert(Alert.AlertType.INFORMATION, "Logowanie zakończone niepowodzeniem",
                                        "Błędny login lub hasło");
                        }
                 else{
                        alert(Alert.AlertType.INFORMATION, "Logowanie zakończone niepowodzeniem",
                                "Błędny login lub hasło");
                }
        }

        private void alert(Alert.AlertType type, String title, String message) {
                Alert alert = new Alert(type);
                alert.setTitle(title);
                alert.setContentText(message);
                alert.showAndWait();
        }
}
