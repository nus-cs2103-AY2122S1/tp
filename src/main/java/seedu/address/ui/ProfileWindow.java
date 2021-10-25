package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.logic.Logic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ProfileWindow extends UiPart<Stage> {

    private static final String FXML = "ProfileWindow.fxml";

    private Logic logic;
    private MainWindow mainWindow;

    @FXML
    private Button submit;

    @FXML
    private Text message;

    @FXML
    private TextField name;

    @FXML
    private TextField telegram;

    @FXML
    private TextField github;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private TextField tag;

    public ProfileWindow(Stage stage, MainWindow mainWindow, Logic logic) {
        super(FXML, stage);
        this.mainWindow = mainWindow;
        this.logic = logic;
        //submit.setOnAction(event());
    }

    public void start() {
        if (logic.isProfilePresent()) {
            // Update mainWindow.start over here
            mainWindow.show();
        } else {
            getRoot().show();
        }
    }

    private EventHandler<ActionEvent> event() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // CHECK EVERYTHING IS VALID
                System.out.println("Checking");
            }
        };
    }

    public void submit(ActionEvent event) {
        //event.consume();
        System.out.println("Checking submit");




        message.setText("Submitted!");
    }
}
