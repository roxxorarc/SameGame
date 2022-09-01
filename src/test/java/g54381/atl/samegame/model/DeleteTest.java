package g54381.atl.samegame.model;

import g54381.atl.samegame.model.commands.DeleteCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DeleteTest {

    private static SameGame game;


    @BeforeAll
    public static void init() {
        game = new SameGame();
    }

    @Test
    public void deleteSuccess() {
        // Board is empty
        game.getBoard().addBubbleAt(new Bubble(Color.BLUE), new Position(0, 0));
        game.getBoard().addBubbleAt(new Bubble(Color.BLUE), new Position(0, 1));
        new DeleteCommand(game, 0, 0).execute(); // Board is empty again
        Assertions.assertTrue(game.isGameOver());

    }

    @Test
    public void deleteFail() {
        // Board is empty
        game.getBoard().addBubbleAt(new Bubble(Color.BLUE), new Position(0, 1));
        game.getBoard().addBubbleAt(new Bubble(Color.RED), new Position(0, 2));
        game.getBoard().addBubbleAt(new Bubble(Color.RED), new Position(0, 3));
        new DeleteCommand(game, 0, 0).execute(); // Board should not be empty because delete is called on a single bubble
        Assertions.assertFalse(game.isGameOver());

    }


}
