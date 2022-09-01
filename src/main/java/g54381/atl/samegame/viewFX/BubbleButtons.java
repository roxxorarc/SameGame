package g54381.atl.samegame.viewFX;

import g54381.atl.samegame.controller.ControllerFX;
import g54381.atl.samegame.model.Position;
import g54381.atl.samegame.model.SameGame;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class BubbleButtons extends GridPane {

    private final ControllerFX controllerFX;
    private final SameGame game;
    private final List<Position> positions = new ArrayList<>();
    private final int HEIGHT;


    public BubbleButtons(SameGame game, ControllerFX controllerFX) {
        this.game = game;
        this.controllerFX = controllerFX;
        HEIGHT = game.getBoard().getHeight() - 1;

    }


    /**
     * Applies the given opacity to the hovered region.
     *
     * @param positions hovered region.
     * @param opacity   opacity.
     */
    private void hoverStyle(List<Position> positions, double opacity) {
        for (Position position : positions)
            for (Node node : getChildren())
                if (getColumnIndex(node) == position.getX() && HEIGHT - getRowIndex(node) == position.getY())
                    node.setOpacity(opacity);
    }


    /**
     * Updates the board.
     * Removes all the elements first then parses game.getBoard() to place every rectangle at the right position.
     */
    private void boardUpdate() {
        this.getChildren().remove(0, this.getChildren().size());
        for (int i = game.getBoard().getWidth() - 1; i >= 0; i--) {
            for (int j = HEIGHT; j >= 0; j--) {
                try {
                    switch (game.getBoard().getBubbleAt(new Position(i, j)).getColor()) {
                        case RED -> add(new javafx.scene.shape.Rectangle(35, 35, Color.RED), i, HEIGHT - j);
                        case BLUE -> add(new javafx.scene.shape.Rectangle(35, 35, Color.BLUE), i, HEIGHT - j);
                        case YELLOW -> add(new javafx.scene.shape.Rectangle(35, 35, Color.YELLOW), i, HEIGHT - j);
                        case GREEN -> add(new javafx.scene.shape.Rectangle(35, 35, Color.GREEN), i, HEIGHT - j);
                        case PURPLE -> add(new javafx.scene.shape.Rectangle(35, 35, Color.PURPLE), i, HEIGHT - j);
                    }
                } catch (Exception NullPointerException) {

                }
            }
        }
    }


    /**
     * Applies all the required events to the rectangles.
     */
    private void applyEvents() {
        for (Node rectangle : getChildren()) {
            rectangle.setOnMouseEntered(e -> {
                game.regionCheck(game.getBoard(), getColumnIndex(rectangle), HEIGHT - getRowIndex(rectangle), positions); // checks the hovering region
                if (positions.size() > 1) { // if region has more than 1 rectangle, apply style and the onClick event.
                    hoverStyle(positions, 1);
                    rectangle.setOnMouseClicked(ev -> controllerFX.addCommand(game, getColumnIndex(rectangle), HEIGHT - getRowIndex(rectangle)));
                }
            });
            rectangle.setOnMouseExited(e -> { // on exit, resets style and clears region.
                hoverStyle(positions, 0.6);
                positions.clear();
            });
        }
    }

    public void update() {
        boardUpdate();
        applyEvents();
    }
}
