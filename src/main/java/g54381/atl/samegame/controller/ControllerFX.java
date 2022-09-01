package g54381.atl.samegame.controller;

import g54381.atl.samegame.model.SameGame;
import g54381.atl.samegame.model.commands.DeleteCommand;
import g54381.atl.samegame.viewFX.ViewFX;
import g54381.atl.util.UndoManager;
import javafx.stage.Stage;

public class ControllerFX {
    private final SameGame game;
    private final ViewFX view;
    private final UndoManager undoManager;


    public ControllerFX(SameGame game, Stage stage) {
        this.game = game;
        this.undoManager = new UndoManager();
        this.view = new ViewFX(this, game);
        game.addObserver(view);
        view.start(stage);
    }


    public void undo() {
        undoManager.undo();
    }

    public void redo() {
        undoManager.redo();
    }

    public void reset() {
        undoManager.reset();
    }

    public void addCommand(SameGame game, int x, int y) {
        undoManager.addCommand(new DeleteCommand(game, x, y));

    }
}
