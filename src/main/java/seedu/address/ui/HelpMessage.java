package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Second panel containing help message.
 */
public class HelpMessage extends UiPart<Region> implements SecondPanel {
    private static final String fxml = "HelpMessage.fxml";
    private static final String TITLE = "Need help?";
    private static final String HELP_MESSAGE =
            "help: see detailed user guide\n"
                    + "stat: show statistics (Revenue and Products Sold)\n"
                    + "exit: exit the application\n"
                    + "clear: purge all data (use with caution!)\n"
                    + "\n"
                    + "Some sample commands you can try:\n"
                    + "\tAdd a new client: add -c Sora -pn 12345678 -e sora@gmail.com -a address\n"
                    + "\tDelete the client with index of 2: delete -c 2\n"
                    + "\tEdit name of client with index of 2 to 'Sky': edit -c 2 -n Sky\n"
                    + "\tFind clients whose name contains 'Sora': find -c Sora\n"
                    + "\tLists all clients: list -c\n"
                    + "\tViews the details of the client with index of 1: view -c 1\n"
                    + "\n"
                    + "Change -c to -p to switch from client to product :)\n";

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
