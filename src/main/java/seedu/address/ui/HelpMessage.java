package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Second panel containing help message.
 */
public class HelpMessage extends UiPart<Region> implements SecondPanel {
    private static String fxml = "HelpMessage.fxml";
    private static final String TITLE = "Need help?";
    private static final String HELP_MESSAGE = "Some example commands you can try:\n"
            +
            "list -c: Lists all clients\n"
            +
            "list -p: Lists all products\n"
            +
            "view -p 1: Views the details of product with index of 1\n"
            +
            "delete -c 2: Deletes client with index of 2\n";

    @FXML
    private Label title;

    @FXML
    private Label helpMessage;

    /**
     * Constructor for the HelpMessage
     */
    public HelpMessage() {
        super(fxml);
        title.setText(TITLE);
        helpMessage.setText(HELP_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof HelpMessage)) {
            return false;
        }
        HelpMessage help = (HelpMessage) other;
        return helpMessage.equals(help.helpMessage);
    }
}
