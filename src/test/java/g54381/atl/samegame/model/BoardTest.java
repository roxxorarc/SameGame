package g54381.atl.samegame.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class BoardTest {
    private static Board board;


    @BeforeAll
    public static void init() {
        board = new Board(10, 10);


    }

    @Test
    public void isInsideTestWhenOutside() {
        Position pos = new Position(15, 15);
        Assertions.assertFalse(board.isInside(pos));
    }

    @Test
    public void isInsideTestWhenInside() {
        Position pos = new Position(3, 3);
        Assertions.assertTrue(board.isInside(pos));
    }

    @Test
    public void isInsideTest0_0() {
        Position pos = new Position(0, 0);
        Assertions.assertTrue(board.isInside(pos));
    }

    @Test
    public void isInsideTestLimit() {
        Position pos = new Position(board.getWidth() - 1, board.getWidth() - 1);
        Assertions.assertTrue(board.isInside(pos));
    }


    @Test
    public void getBubbleAtTestTrue() {
        Position pos = new Position(3, 3);
        Bubble bubble = new Bubble(Color.BLUE);
        board.addBubbleAt(bubble, pos);
        Assertions.assertEquals(bubble, board.getBubbleAt(pos));
    }

    @Test
    public void getBubbleAtTestFalse() {
        Position pos = new Position(3, 3);
        Bubble bubble = new Bubble(Color.BLUE);
        Bubble bubble2 = new Bubble(Color.RED);
        board.addBubbleAt(bubble, pos);
        Assertions.assertNotEquals(bubble2, board.getBubbleAt(pos));
    }

    @Test
    public void getBubbleAtTestException() {
        Position pos = new Position(15, 15);
        Bubble bubble = new Bubble(Color.BLUE);
        Assertions.assertThrows(IllegalArgumentException.class, () -> board.getBubbleAt(pos));


    }

    @Test
    public void addBubbleAtTest() {
        Position pos = new Position(3, 3);
        Bubble bubble = new Bubble(Color.BLUE);
        board.addBubbleAt(bubble, pos);

        Assertions.assertEquals(bubble, board.getBubbleAt(pos));

    }


}
