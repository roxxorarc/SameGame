package g54381.atl.samegame.model;

public class GameState {

    private final Board board;
    private final int score, addedScore, remainingBubbles;

    public GameState(Board board, int score, int addedScore, int remainingBubbles) {
        this.board = new Board(board);
        this.score = score;
        this.addedScore = addedScore;
        this.remainingBubbles = remainingBubbles;
    }

    public Board getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public int getAddedScore() {
        return addedScore;
    }

    public int getRemainingBubbles() {
        return remainingBubbles;
    }
}
