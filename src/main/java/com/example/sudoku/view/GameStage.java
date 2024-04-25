package com.example.sudoku.view;

import com.example.sudoku.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {

    private GameController gameController;
    public GameStage() throws IOException {
        String url = "/com/example/Sudoku/game-view.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        gameController = loader.getController();
        setTitle("Sudoku");
        Scene scene = new Scene(root);
        setScene(scene);
        getIcons().add(new Image(
                String.valueOf(getClass().getResource("/com/example/sudoku/images/icon.png"))));


        setResizable(false);
        show();
    }

    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }

    public static GameStage getInstance() throws IOException {
        return GameStage.GameStageHolder.INSTANCE = new GameStage();
    }

    public static void deleteInstance() {
        GameStage.GameStageHolder.INSTANCE.close();
        GameStage.GameStageHolder.INSTANCE = null;
    }

    public GameController getGameController() {
        return gameController;
    }
}
