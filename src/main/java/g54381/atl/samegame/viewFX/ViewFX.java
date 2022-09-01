package g54381.atl.samegame.viewFX;

import g54381.atl.samegame.controller.ControllerFX;
import g54381.atl.samegame.model.SameGame;
import g54381.atl.util.Observer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewFX implements Observer {

    private final ControllerFX controllerFX;
    private final SameGame game;
    private BorderPane root;
    private Menu menuPane;
    private Score scorePane;
    private UndoRedoReset undoRedoResetPane;
    private BubbleButtons board;
    private Scene scene;

    public ViewFX(ControllerFX controller, SameGame game) {
        this.controllerFX = controller;
        this.game = game;

    }


    /**
     * Updates the board and scores every time it is notified.
     * Also checks if the game is over or not to display starting menu instead of board.
     */
    @Override
    public void update() {
        board.update();
        scorePane.update();
        if (game.isGameOver()) {
            menuPane.getScoredText().setText("You scored : " + game.getScore());
            root.setCenter(menuPane);
        } else root.setCenter(board);
    }

    public void start(Stage stage) {
        root = new BorderPane();
        undoRedoResetPane = new UndoRedoReset(game, controllerFX);
        scorePane = new Score(game);
        board = new BubbleButtons(game, controllerFX);
        menuPane = new Menu(game);
        scene = new Scene(root, 800, 600);

        // IDs
        board.setId("board");
        undoRedoResetPane.setId("bot");
        scorePane.setId("top");
        root.setId("root");


        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setMaxWidth(1000);
        stage.setMaxHeight(750);


        root.setTop(scorePane);
        root.setBottom(undoRedoResetPane);
        root.setCenter(menuPane);


        stage.getIcons().add(new Image("/icon.png"));
        stage.setTitle("SameGame");
        stage.setScene(scene);
        stage.show();


        scene.getStylesheets().add("/style.css");
    }


}

