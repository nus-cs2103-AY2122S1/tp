package seedu.address.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TerminalBox extends VBox {

    private static final String ERROR_STYLE_CLASS = "error";
    private static final String PREFIX = ">  ";

    @FXML
    private Label inputText;

    @FXML
    private Label responseText;

    /**
     * Creates a {@code TerminalBox} with the given input and response strings.
     */
    public TerminalBox(String input, String response) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/TerminalBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.inputText.setText(PREFIX + input);
        this.responseText.setText(response);
    }

    /**
     * Creates a {@code TerminalBox} with the given input string and exception.
     */
    public TerminalBox(String input, Exception exception) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/TerminalBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.inputText.setText(PREFIX + input);
        this.responseText.setText(exception.getMessage());
        responseText.getStyleClass().add(ERROR_STYLE_CLASS);
    }

}
