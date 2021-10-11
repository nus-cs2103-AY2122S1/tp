package dash.ui;

import java.util.logging.Logger;

import dash.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HelpPanel extends UiPart<Region> {
    private static final String FXML = "helpPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TaskListPanel.class);

    @FXML
    private VBox container;

    @FXML
    private Label text;

    private final String helpContent = "General:\n"
            + "1. Contacts Tab -> contacts\n"
            + "2. Tasks Tab -> tasks\n"
            + "3. Help Tab -> help\n"
            + "4. Exit Tab -> exit\n\n"
            + "Contacts Tab:\n"
            + "1. Add -> add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]\n"
            + "2. Edit -> edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL a/ADDRESS] [t/TAG]\n"
            + "3. Delete -> delete INDEX\n"
            + "4. Find by name -> find NAME\n"
            + "5. Find by other info -> find [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]\n"
            + "6. Clear Contacts -> clear\n\n"
            + "Tasks Tab:\n"
            + "1. Add -> add d/DESCRIPTION [t/TAG]\n"
            + "2. Edit -> edit INDEX [d/DESCRIPTION] [t/TAG]\n"
            + "3. Delete -> delete INDEX\n"
            + "4. Find by description -> find DESCRIPTION\n"
            + "5. Find by tag -> find [t/TAG]\n"
            + "6. Clear Tasks -> clear\n\n"
            + "For more detailed information on each command,"
            + " visit our User Guide at https://ay2122s1-cs2103t-w15-2.github.io/tp/UserGuide.html";

    /**
     * Creates a new Help Panel to contain help messages.
     *
     */
    public HelpPanel() {
        super(FXML);
        text.setText(helpContent);
    }

}
