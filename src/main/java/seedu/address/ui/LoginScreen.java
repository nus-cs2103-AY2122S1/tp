package seedu.address.ui;

import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.encryption.exceptions.UnsupportedPasswordException;

public class LoginScreen implements Ui {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private MainApp app;
    private boolean isNew;

    /**
     * Constructs a new LoginScreen.
     *
     * @param app The app to have the login screen.
     * @param isNew The boolean value for whether the user is new.
     */
    public LoginScreen(MainApp app, boolean isNew) {
        this.app = app;
        this.isNew = isNew;
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Password");
        stage.setResizable(false);
        stage.setMinHeight(200.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        userInput.setOnAction((event) -> {
            try {
                app.logIn(userInput.getText());
            } catch (UnsupportedPasswordException | NoSuchPaddingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            userInput.clear();
        });
    }
}
