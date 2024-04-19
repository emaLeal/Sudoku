package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {
    public WelcomeStage() throws IOException {
        String URL = "/com/example/sudoku/welcome-view.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(URL));
        Parent root = loader.load();
        setTitle("Sudoku");
        Scene scene = new Scene(root);
        setScene(scene);
        setResizable(false);
        show();
    }

    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    public static WelcomeStage getInstance() throws IOException {
        return WelcomeStageHolder.INSTANCE = new WelcomeStage();
    }

    public static void deleteInstance() {
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }
}
