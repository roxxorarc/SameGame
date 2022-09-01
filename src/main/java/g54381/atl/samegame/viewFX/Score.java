package g54381.atl.samegame.viewFX;

import g54381.atl.samegame.model.SameGame;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class Score extends HBox {
    private final SameGame game;
    private Label scoreNumber, lastScored, remainingBubbles;
    private Text scoreText;

    public Score(SameGame game) {
        this.game = game;
        setup();


    }

    private void setup() {
        scoreNumber = new Label("0");
        scoreText = new Text("Score :");
        scoreNumber.setId("scoreNumber");
        scoreText.setId("scoreText");
        lastScored = new Label("0");
        lastScored.setId("lastScored");
        remainingBubbles = new Label(String.valueOf(game.getRemainingBubbles()));
        remainingBubbles.setId("remainingBubbles");


        var spacer = new Pane();
        setHgrow(spacer, Priority.ALWAYS);

        var spacer2 = new Pane();
        setHgrow(spacer2, Priority.ALWAYS);


        getChildren().addAll(scoreText, scoreNumber, spacer, remainingBubbles, spacer2, lastScored);

    }


    public Label getScoreN() {
        return scoreNumber;
    }

    public Label getLastScored() {
        return lastScored;
    }

    public Label getRemainingBubbles() {
        return remainingBubbles;
    }


    public void update() {
        getScoreN().setText(String.valueOf(game.getScore()));
        getLastScored().setText(String.valueOf(game.getLastScored()));
        getRemainingBubbles().setText(String.valueOf(game.getRemainingBubbles()));

    }
}
