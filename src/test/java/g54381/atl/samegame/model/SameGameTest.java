package g54381.atl.samegame.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SameGameTest {
    private static Board board;
    private static SameGame game;


    @BeforeAll
    public static void init() {
        board = new Board(10, 10);
        game = new SameGame(board);


    }

    @Test
    public void isGameOverTestWhenTrue() {
        Bubble bubble = new Bubble(Color.BLUE);
        board.addBubbleAt(bubble, new Position(0, 0));
        Assertions.assertTrue(game.isGameOver());
    }

    @Test
    public void isGameOverTestWhenFalse() {
        Bubble bubble = new Bubble(Color.BLUE);
        board.addBubbleAt(bubble, new Position(0, 0));
        board.addBubbleAt(bubble, new Position(0, 1));
        Assertions.assertFalse(game.isGameOver());

    }
}
