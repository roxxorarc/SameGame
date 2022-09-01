package g54381.atl.samegame.model;

import g54381.atl.util.Observable;
import g54381.atl.util.Observer;

import java.util.ArrayList;
import java.util.List;


/**
 * Facade.
 */
public class SameGame implements Observable {

    private final List<Observer> observers = new ArrayList<>();
    private final List<Position> positions = new ArrayList<>();
    private Board board;
    private boolean isGameOver = false;
    private int score, lastScore, remainingBubbles;

    /**
     * Facade constructor.
     * Initializes a new board.
     */
    public SameGame() {
        this.board = new Board();
    }

    /**
     * Facade constructor.
     * Initializes a new board with the given width, height and difficulty.
     *
     * @param width
     * @param height
     * @param nbColors
     */
    public SameGame(int width, int height, int nbColors) {
        this.board = new Board(width, height, nbColors);
    }

    /**
     * Facade constructor.
     * Used for tests.
     *
     * @param board
     */
    public SameGame(Board board) { // used for tests
        this.board = board;
    }


    /**
     * Plays a turn.
     * Checks for the region at the given position then updates all the scores,
     * removes the bubbles from the board and updates it.
     *
     * @param pos
     */
    public void play(Position pos) {
        regionCheck(board, pos.getX(), pos.getY(), positions);
        setLastScore(positions.size() * (positions.size() - 1));
        addScore();
        removeBubbles(positions.size());
        deleteBubbles(positions);
        updateBoard();
        positions.clear();
        isGameOver();
        notifyObservers();
    }


    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) observer.update();

    }


    /**
     * Delete bubbles in the given list from the board.
     *
     * @param positions
     */
    private void deleteBubbles(List<Position> positions) {
        for (Position position : positions) board.removeBubbleAt(position);
    }


    // Getters and setters
    public int getScore() {
        return score;
    }

    public int getLastScored() {
        return lastScore;
    }

    public Board getBoard() {
        return board;
    }

    public int getRemainingBubbles() {
        return remainingBubbles;
    }

    public void regionCheck(Board board, int x, int y, List<Position> positions) {
        boolean[][] checked = new boolean[board.getWidth()][board.getHeight()];
        if (isNull(board.getBoard(), x, y)) return;
        Color color = board.getBubbleAt(new Position(x, y)).getColor();
        if (!(isInside(board.getBoard(), x, y))) return;
        positions.add(new Position(x, y));
        if (x - 1 > 0 && board.getBubbleAt(new Position(x - 1, y)) != null && board.getBubbleAt(new Position(x - 1, y)).isSameColor(color) ||
                x + 1 < board.getWidth() && board.getBubbleAt(new Position(x + 1, y)) != null && board.getBubbleAt(new Position(x + 1, y)).isSameColor(color) ||
                y + 1 < board.getHeight() && board.getBubbleAt(new Position(x, y + 1)) != null && board.getBubbleAt(new Position(x, y + 1)).isSameColor(color) ||
                y - 1 > 0 && board.getBubbleAt(new Position(x, y - 1)) != null && board.getBubbleAt(new Position(x, y - 1)).isSameColor(color)) {
            regionCheck(board.getBoard(), x, y - 1, Direction.UP, checked, color, positions);
            regionCheck(board.getBoard(), x, y + 1, Direction.DOWN, checked, color, positions);
            regionCheck(board.getBoard(), x + 1, y, Direction.LEFT, checked, color, positions);
            regionCheck(board.getBoard(), x - 1, y, Direction.RIGHT, checked, color, positions);
        }
    }

    public void regionCheck(Bubble[][] bubbles, int x, int y, Direction d, boolean[][] checked, Color c, List<Position> positions) {
        if (!(isInside(this.getBoard().getBoard(), x, y)) || checked[x][y] ||
                !(this.getBoard().getBubbleAt(new Position(x, y)).isSameColor(c))) return;

        checked[x][y] = true;
        if (!positions.contains(new Position(x, y))) positions.add(new Position(x, y));

        if (d != Direction.UP) regionCheck(bubbles, x, y + 1, Direction.DOWN, checked, c, positions);
        if (d != Direction.DOWN) regionCheck(bubbles, x, y - 1, Direction.UP, checked, c, positions);
        if (d != Direction.RIGHT) regionCheck(bubbles, x + 1, y, Direction.LEFT, checked, c, positions);
        if (d != Direction.LEFT) regionCheck(bubbles, x - 1, y, Direction.RIGHT, checked, c, positions);

    }

    private void addScore() {
        score += lastScore;
    }

    private void setLastScore(int i) {
        lastScore = i;
    }

    private void removeBubbles(int i) {
        remainingBubbles -= i;
    }

    private boolean isInside(Object[][] tab, int x, int y) {
        return isInside(tab, x) && isInside(tab[x], y) && !isNull(tab, x, y);
    }

    private boolean isInside(Object[] tab, int x) {
        return 0 <= x && x < tab.length;
    }

    private boolean isNull(Object[][] tab, int x, int y) {
        return tab[x][y] == null;
    }

    /**
     * Checks if the game is over.
     * Parses the board and returns true if no bubble is next to another one with the same color.
     *
     * @return true if the game is over.
     */
    public boolean isGameOver() {
        isGameOver = true;
        for (int col = 0; col < board.getWidth(); col++) {
            for (int row = 0; row < board.getHeight(); row++) {
                if (board.getBubbleAt(new Position(col, row)) != null) {
                    Color color = board.getBubbleAt(new Position(col, row)).getColor();
                    if (row + 1 < board.getHeight() && board.getBubbleAt(new Position(col, row + 1)) != null && board.getBubbleAt(new Position(col, row + 1)).isSameColor(color)) {
                        isGameOver = false;
                    } else if (col + 1 < board.getWidth() && board.getBubbleAt(new Position(col + 1, row)) != null && board.getBubbleAt(new Position(col + 1, row)).isSameColor(color)) {
                        isGameOver = false;
                    }
                }
            }
        }
        return isGameOver;
    }


    //

    /**
     * Resets the board.
     */
    public void reset() {
        board = new Board(board.getWidth(), board.getHeight()); //
        notifyObservers();
    }

    /**
     * Sets the game at the given state (board and scores).
     *
     * @param state
     */
    public void setState(GameState state) {
        this.board = state.getBoard();
        this.score = state.getScore();
        this.lastScore = state.getAddedScore();
        this.remainingBubbles = state.getRemainingBubbles();
        updateBoard();
        notifyObservers();
    }

    /**
     * Starts the game.
     */
    public void start() {
        remainingBubbles = board.getHeight() * board.getWidth();
        notifyObservers();
    }

    /**
     * Updates the board.
     * Makes all bubbles fall and if the column is empty, moves everything to the left.
     */
    private void updateBoard() {
        for (int k = 0; k < board.getHeight(); k++) {
            for (int i = 0; i < board.getWidth(); i++) {
                for (int j = 0; j < board.getHeight(); j++) {
                    if (j + 1 < board.getHeight() && board.getBubbleAt(new Position(i, j)) == null) {
                        board.addBubbleAt(board.getBubbleAt(new Position(i, j + 1)), new Position(i, j));
                        board.addBubbleAt(null, new Position(i, j + 1));
                    }
                }

            }
        }
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (j + 1 < board.getWidth() && board.getBubbleAt(new Position(j, 0)) == null) {
                    board.setColumnAt(j, board.getColumn(j + 1));
                    board.setColumnAt(j + 1, new Bubble[getBoard().getHeight()]);
                }
            }
        }
    }


}