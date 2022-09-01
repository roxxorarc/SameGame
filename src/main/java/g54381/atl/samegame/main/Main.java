package g54381.atl.samegame.main;

import g54381.atl.samegame.controller.ControllerFX;
import g54381.atl.samegame.model.SameGame;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        var game = new SameGame();
        new ControllerFX(game, primaryStage);
    }
}
