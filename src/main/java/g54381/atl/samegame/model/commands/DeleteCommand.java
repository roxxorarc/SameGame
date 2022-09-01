package g54381.atl.samegame.model.commands;

import g54381.atl.samegame.model.GameState;
import g54381.atl.samegame.model.Position;
import g54381.atl.samegame.model.SameGame;
import g54381.atl.util.Command;

public class DeleteCommand implements Command {

    private final SameGame game;
    private final int x;
    private final int y;
    private GameState state;

    public DeleteCommand(SameGame game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        state = new GameState(game.getBoard(), game.getScore(), game.getLastScored(), game.getRemainingBubbles());
        game.play(new Position(x, y));
    }

    @Override
    public void unexecute() {
        game.setState(state);
    }
}
