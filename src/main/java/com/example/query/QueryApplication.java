package com.example.query;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class QueryApplication extends Application {


    private static Stage primaryStage;
    private ConfigurableApplicationContext springContext;

    public static class Bounds {
        public static final int WIDTH = 550;
        public static final int HEIGHT = 480;
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(QueryApplication.class);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        QueryApplication.primaryStage = primaryStage;

        primaryStage.setTitle("Baza pacjentów 2.0");
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(root, QueryApplication.Bounds.WIDTH, QueryApplication.Bounds.HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    @Override
    public void stop() {
        springContext.stop();
    }

    public static Stage getStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(QueryApplication.class, args);
    }

}
