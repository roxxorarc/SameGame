package g54381.atl.samegame.viewFX;

import g54381.atl.samegame.controller.ControllerFX;
import g54381.atl.samegame.model.SameGame;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class UndoRedoReset extends HBox {

    private final ControllerFX controllerFX;
    private final SameGame game;
    private Button undo, redo, reset;


    public UndoRedoReset(SameGame game, ControllerFX controllerFX) {
        this.game = game;
        this.controllerFX = controllerFX;
        setup();


    }


    private void setup() {
        undo = new Button("Undo");
        redo = new Button("Redo");
        reset = new Button("Reset");

        // IDs
        undo.setId("undo");
        redo.setId("redo");
        reset.setId("reset");

        // Events
        undo.setOnMouseClicked(e -> controllerFX.undo());
        redo.setOnMouseClicked(e -> controllerFX.redo());
        reset.setOnMouseClicked(e -> {
            game.reset();
            controllerFX.reset();
        });

        var spacer = new Pane();
        setHgrow(spacer, Priority.ALWAYS);

        getChildren().addAll(undo, redo, spacer, reset);


    }
}
