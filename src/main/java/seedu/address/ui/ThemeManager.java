package seedu.address.ui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ThemeManager {
    private enum Theme { LIGHT, DARK }
    private Theme currentTheme;
    private static double size = 30;

    public ThemeManager() {
        currentTheme = Theme.LIGHT;
    }

    public void changeTheme(Stage primaryStage, Button themeButton) {
        switch (currentTheme) {
            case DARK:
                primaryStage.getScene().getStylesheets().remove("view/DarkTheme.css");
                primaryStage.getScene().getStylesheets().add("view/LightTheme.css");
                ImageView sun = new ImageView("images/sun.png");
                sun.setFitHeight(size);
                sun.setFitWidth(size);
                themeButton.setGraphic(sun);
                currentTheme = Theme.LIGHT;
                break;
            case LIGHT:
                primaryStage.getScene().getStylesheets().remove("view/LightTheme.css");
                primaryStage.getScene().getStylesheets().add("view/DarkTheme.css");
                ImageView moon = new ImageView("images/moon.png");
                moon.setFitHeight(size);
                moon.setFitWidth(size);
                themeButton.setGraphic(moon);
                currentTheme = Theme.DARK;
                break;
            default:
        }
    }
}
