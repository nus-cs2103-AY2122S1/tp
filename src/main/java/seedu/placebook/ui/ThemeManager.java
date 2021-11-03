package seedu.placebook.ui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class ThemeManager {
    private static final double size = 30;
    private static final String darkThemeStylesheet = "view/DarkTheme.css";
    private static final String lightThemeStylesheet = "view/LightTheme.css";
    private enum Theme { LIGHT, DARK }

    private Theme currentTheme;
    private String currentStylesheet;

    /**
     * Constructs ThemeManager
     */
    public ThemeManager() {
        currentTheme = Theme.LIGHT;
        currentStylesheet = lightThemeStylesheet;
    }

    /**
     * Changes the current theme.
     *
     * @param primaryStage the primary stage
     * @param themeButton the theme button
     */
    public void changeTheme(Stage primaryStage, Button themeButton) {
        switch (currentTheme) {
        case DARK:
            primaryStage.getScene().getStylesheets().remove(darkThemeStylesheet);
            primaryStage.getScene().getStylesheets().add(lightThemeStylesheet);

            ImageView sun = new ImageView("images/sun.png");
            sun.setFitHeight(size);
            sun.setFitWidth(size);
            themeButton.setGraphic(sun);

            currentTheme = Theme.LIGHT;
            currentStylesheet = lightThemeStylesheet;
            break;
        case LIGHT:
            primaryStage.getScene().getStylesheets().remove(lightThemeStylesheet);
            primaryStage.getScene().getStylesheets().add(darkThemeStylesheet);

            ImageView moon = new ImageView("images/moon.png");
            moon.setFitHeight(size);
            moon.setFitWidth(size);
            themeButton.setGraphic(moon);

            currentTheme = Theme.DARK;
            currentStylesheet = darkThemeStylesheet;
            break;
        default:
        }
    }

    public String getCurrentStylesheet() {
        return this.currentStylesheet;
    }
}
