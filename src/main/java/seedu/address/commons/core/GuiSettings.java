package seedu.address.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;
    private static final Theme DEFAULT_THEME = Theme.DARK;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;
    private final Theme theme;

    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
        theme = DEFAULT_THEME;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and
     * position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition, Theme theme) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        this.theme = theme;
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    public Theme getTheme() {
        return this.theme;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GuiSettings)) { // this handles null as well.
            return false;
        }

        GuiSettings o = (GuiSettings) other;

        return windowWidth == o.windowWidth
                && windowHeight == o.windowHeight
                && theme == o.theme
                && Objects.equals(windowCoordinates, o.windowCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, theme, windowCoordinates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : " + windowWidth + System.lineSeparator());
        sb.append("Height : " + windowHeight + System.lineSeparator());
        sb.append("Position : " + windowCoordinates + System.lineSeparator());
        sb.append("Theme: " + theme);
        return sb.toString();
    }

    public enum Theme {
        DARK("view/DarkTheme.css"), LIGHT("view/LightTheme.css");

        private final String url;

        /**
         * @param url for theme's css
         */
        private Theme(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
