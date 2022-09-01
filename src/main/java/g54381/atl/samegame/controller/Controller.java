package g54381.atl.samegame.controller;

import g54381.atl.samegame.model.Board;
import g54381.atl.samegame.model.GameState;
import g54381.atl.samegame.model.SameGame;
import g54381.atl.samegame.model.commands.DeleteCommand;
import g54381.atl.samegame.view.View;
import g54381.atl.util.UndoManager;

/**
 * Controller class.
 * Handles the main game loop.
 */
public class Controller {
    private View view;
    private SameGame game;
    private UndoManager manager = new UndoManager();

    /**
     * Controller constructor.
     * Initializes the view and game, then adds the view to game observers.
     */
    public Controller() {
        game = new SameGame();
        view = new View(game);
        game.addObserver(view);
    }

    /**
     * Main loop.
     * Asks for difficulty, width and height.
     */
    public void start() {
        String[] command;
        int nbColors = view.askInt("difficulty", 2, 5);
        int width = view.askInt("width", 5, 16);
        int height = view.askInt("height", 5, 16);
        game.setState(new GameState(new Board(width, height, nbColors), 0, 0, 0));
        game.start();
        loop:
        while (!game.isGameOver()) {
            command = view.askCommand();
            try {
                switch (command[0]) {
                    case "delete":
                        manager.addCommand(new DeleteCommand(game, Integer.parseInt(command[1]), Integer.parseInt(command[2])));
                        break;
                    case "undo":
                        manager.undo();
                        break;
                    case "redo":
                        manager.redo();
                        break;
                    case "stop":
                        break loop;
                    default:
                        view.printErr("Wrong command, try delete <x> <y>");
                        break;
                }
            } catch (Exception indexOutOfBoundException) {
                view.printErr("Elements are wrong or missing");

            }
        }
    }

}



