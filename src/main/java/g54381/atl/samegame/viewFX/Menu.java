package g54381.atl.samegame.viewFX;

import g54381.atl.samegame.model.Board;
import g54381.atl.samegame.model.GameState;
import g54381.atl.samegame.model.SameGame;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Menu extends VBox {

    private final SameGame game;
    private ComboBox<Integer> colors, width, heigth;
    private Text difficultyText, boardSizeText, scoredText;
    private HBox difficultyBox, startBox, sizeBox, scoredBox;
    private Button start;


    public Menu(SameGame game) {
        this.game = game;
        setup();
    }


    private void setup() {

        colors = new ComboBox<>();
        heigth = new ComboBox<>();
        width = new ComboBox<>();


        scoredText = new Text("");
        difficultyText = new Text("Difficulty :   ");
        boardSizeText = new Text("Board size : ");


        difficultyBox = new HBox();
        startBox = new HBox();
        sizeBox = new HBox();
        scoredBox = new HBox();

        start = new Button("Start");

        // IDs
        start.setId("start");
        scoredText.setId("scored");
        difficultyBox.setId("difficulty");
        sizeBox.setId("size");
        setId("menu");

        // Add values
        colors.getItems().addAll(2, 3, 4, 5);
        width.getItems().addAll(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        heigth.getItems().addAll(5, 6, 7, 8, 9, 10, 11, 12, 13);

        // Set values
        colors.setValue(3);
        width.setValue(14);
        heigth.setValue(10);

        // Spacers
        Pane vSpacer = new Pane();
        VBox.setVgrow(vSpacer, Priority.ALWAYS);
        Pane vSpacer2 = new Pane();
        VBox.setVgrow(vSpacer2, Priority.ALWAYS);
        Pane vSpacer3 = new Pane();
        VBox.setVgrow(vSpacer3, Priority.ALWAYS);
        Pane hSpacer = new Pane();
        HBox.setHgrow(hSpacer, Priority.ALWAYS);
        Pane hSpacer2 = new Pane();
        HBox.setHgrow(hSpacer2, Priority.ALWAYS);
        Pane hSpacer3 = new Pane();
        HBox.setHgrow(hSpacer3, Priority.ALWAYS);
        Pane hSpacer4 = new Pane();
        HBox.setHgrow(hSpacer4, Priority.ALWAYS);


        // Setup
        difficultyBox.getChildren().addAll(difficultyText, colors);
        sizeBox.getChildren().addAll(boardSizeText, width, heigth);
        scoredBox.getChildren().addAll(hSpacer3, scoredText, hSpacer4);
        startBox.getChildren().addAll(hSpacer, start, hSpacer2);
        getChildren().addAll(difficultyBox, vSpacer, sizeBox, vSpacer2, scoredBox, vSpacer3, startBox);


        start.setOnMouseClicked(e -> {
            game.setState(new GameState(new Board(width.getValue(), heigth.getValue(), colors.getValue()), 0, 0, game.getRemainingBubbles()));
            game.start();

        });
    }

    public Text getScoredText() {
        return scoredText;
    }
}
