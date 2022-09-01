package g54381.atl.samegame.view;

import g54381.atl.samegame.model.Bubble;
import g54381.atl.samegame.model.Position;
import g54381.atl.samegame.model.SameGame;
import g54381.atl.util.Observer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View implements Observer {

    private static final Scanner s = new Scanner(System.in);
    private SameGame game;

    public View(SameGame game) {
        this.game = game;
    }

    /**
     * Shows the board of the given game.
     *
     * @param game
     */
    public void showBoard(SameGame game) {
        StringBuilder str = new StringBuilder("Remaining bubbles : ").append(game.getRemainingBubbles()).append("\n");
        str.append("  |");
        str.append("- ".repeat(game.getBoard().getWidth() + 1));
        str.append("|\n");
        for (int x = game.getBoard().getHeight() - 1; x >= 0; x--) {
            str.append(String.format("%-2d|", x));
            for (int y = 0; y < game.getBoard().getWidth(); y++) {
                Bubble bubble = game.getBoard().getBubbleAt(new Position(y, x));
                str.append(bubble != null ? bubble.getColor().color : " ");
            }
            str.append("|\n");
        }
        str.append("  |");
        str.append("- ".repeat(game.getBoard().getWidth() + 1));
        str.append("|");
        str.append("\n" + "    ");
        for (int i = 0; i < game.getBoard().getWidth(); i++) {
            str.append(i).append(" ");

        }
        str.append("\n").append("Score : ").append(game.getScore());
        str.append("    ").append("Last scored : ").append(game.getLastScored());


        System.out.println(str);
    }


    /**
     * Prints the given string
     *
     * @param s the string to print.
     */
    public void print(String s) {
        System.out.println(s);
    }

    public void printErr(String s) {
        System.err.println(s);
    }

    /**
     * Asks for an input and split it.
     *
     * @return a string array containing the split input.
     */
    public String[] askCommand() {
        return s.nextLine().split(" ", 0);
    }


    public int askInt(String string, int min, int max) {
        int result = 0;
        do {
            System.out.println("Please enter an index number between " + min + " and " + max);
            try {
                System.out.println("Enter the " + string);
                result = s.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
                s.nextLine();
            }
        } while ((result <= min - 1 || result >= max + 1));

        return result;


    }


    @Override
    public void update() {
        showBoard(game);

    }

    public void setGame(SameGame game) {
        this.game = game;
    }
}





