package g54381.atl.samegame.model;

import java.util.Objects;

public class Bubble {

    private final Color color;

    /**
     * Bubble constructor.
     *
     * @param color
     */
    public Bubble(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Bubble{" +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bubble bubble = (Bubble) o;
        return Objects.equals(getColor(), bubble.getColor());
    }

    public boolean isSameColor(Color c) {
        return this.getColor() == c;
    }

}
