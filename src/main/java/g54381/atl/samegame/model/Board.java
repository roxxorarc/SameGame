package g54381.atl.samegame.model;

import java.util.Arrays;
import java.util.Random;

/**
 *
 */
public class Board {

    private final int height, width, DEFAULT_SIZE = 18;
    private final Bubble[][] board;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        board = new Bubble[width][height];
    }

    public Board() {
        this.width = DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
        board = new Bubble[DEFAULT_SIZE][DEFAULT_SIZE];
    }

    public Board(Board board) {
        this.height = board.getHeight();
        this.width = board.getWidth();
        this.board = deepCopy(board.getBoard());

    }

    public Board(int width, int height, int nbColors) {
        this.width = width;
        this.height = height;
        board = new Bubble[width][height];
        fillBoard(nbColors);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    protected Bubble[][] getBoard() { // non.
        return board;
    }

    protected Bubble[] getColumn(int i) {
        return Arrays.copyOf(board[i], board[i].length);
    }

    protected void setColumnAt(int i, Bubble[] column) {
        board[i] = column;
    }

    /**
     * Adds the given bubble at the given position.
     *
     * @param bubble
     * @param pos
     */
    protected void addBubbleAt(Bubble bubble, Position pos) {
        board[pos.getX()][pos.getY()] = bubble;

    }

    /**
     * Removes the bubble at the given position.
     *
     * @param pos
     */
    protected void removeBubbleAt(Position pos) { // private
        board[pos.getX()][pos.getY()] = null;
    }

    public Bubble getBubbleAt(Position pos) {
        if (!isInside(pos)) throw new IllegalArgumentException("Position is invalid" + pos);
        else return board[pos.getX()][pos.getY()];
    }


    public boolean isInside(Position pos) {
        return ((pos.getX() >= 0 && pos.getX() < width) && (pos.getY() >= 0 && pos.getY() < height));
    }

    private Bubble[][] deepCopy(Bubble[][] array) {
        Bubble[][] result = new Bubble[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return result;
    }

    /**
     * Fills the board with the given number of colors.
     *
     * @param nbColors the number of colors.
     */
    private void fillBoard(int nbColors) {
        if (nbColors > 5 || nbColors < 0)
            throw new IllegalArgumentException("Number of colors should be higher than 1 and smaller than 5");
        Random random = new Random();
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                int i = random.nextInt(nbColors);
                Position pos = new Position(x, y);
                switch (i) {
                    case 0 -> addBubbleAt(new Bubble(Color.BLUE), pos);
                    case 1 -> addBubbleAt(new Bubble(Color.RED), pos);
                    case 2 -> addBubbleAt(new Bubble(Color.YELLOW), pos);
                    case 3 -> addBubbleAt(new Bubble(Color.GREEN), pos);
                    case 4 -> addBubbleAt(new Bubble(Color.PURPLE), pos);
                }
            }
        }
    }


}
