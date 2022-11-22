package com.example.assignment4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainUI uiRoot = new MainUI();
        Scene scene = new Scene(uiRoot);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SHIFT) uiRoot.controller.isShiftDown = true;
            if (keyEvent.getCode() == KeyCode.DELETE) uiRoot.controller.handleDeleteKeyPressed();

        });
        scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SHIFT) uiRoot.controller.isShiftDown = false;
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}