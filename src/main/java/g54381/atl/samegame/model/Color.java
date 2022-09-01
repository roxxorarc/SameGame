package g54381.atl.samegame.model;

public enum Color {
    /**
     * //  Les emojis + codes couleurs fonctionnent sur intelliJ
     * RED("\u001B[31m" + "\uD83D\uDFE5"),
     * GREEN("\033[32m" + "\uD83D\uDFE5"),
     * BLUE("\033[34m" + "\uD83D\uDFE5"),
     * YELLOW("\033[33m" + "\uD83D\uDFE5"),
     * PURPLE("\033[35m" + "\uD83D\uDFE5");
     **/


    RED("\u001B[31m" + "O"),
    GREEN("\033[32m" + "O"),
    BLUE("\033[34m" + "O"),
    YELLOW("\033[33m" + "O"),
    PURPLE("\033[35m" + "O");


    public final String color;

    Color(String s) {

        this.color = s;
    }
}
