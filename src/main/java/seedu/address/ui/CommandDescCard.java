package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import seedu.address.logic.commands.CommandEnum;

/**
 * A UI component that displays the format, description and examples of a {@code Command}
 */
public class CommandDescCard extends UiPart<TitledPane> {

    private static final String FXML = "CommandDescCard.fxml";

    @FXML
    private TitledPane commandTitle;

    @FXML
    private Label format;

    @FXML
    private Label description;

    @FXML
    private Label examples;

    private CommandSummaryWindow commandSummaryWindow;

    /**
     * Creates a Command Description Card based on the {@code Command} and Command Description window supplied.
     */
    //@@author jacobnbh - reused
    // code snippet adapted from fellow tutorial group, T11-2's source code with minor modifications that are
    // customized to our project
    public CommandDescCard(CommandEnum commandWord, CommandSummaryWindow commandSummaryWindow) {
        super(FXML);
        this.commandSummaryWindow = commandSummaryWindow;
        this.getRoot().heightProperty().addListener((obs, oldHeight, newHeight) ->
                commandSummaryWindow.getRoot().sizeToScene());

        switch (commandWord) {
        case ADD:
            this.getRoot().setText("Add Command");
            this.format.setText("add n/NAME d/DATE t/TIMESLOT l/LOCATION [tag/TAG]...[r/REMARK]");
            this.description.setText("Adds a consultation event to DukePro(f)");
            this.examples.setText("add n/Lulu Yousef d/2020-01-01 t/0800-0900 l/NUS tag/Important tag/supplementary"
                    + "\n" + "add n/Ruth Poh d/2020-02-02 t/1000-1100 l/The Deck r/May have to switch to zoom");
            break;
        case BLOCK:
            this.getRoot().setText("Block Command");
            this.format.setText("block d/DATE t/TIMESLOT");
            this.description.setText("Blocks the specified time slot and prevents an event from being created at"
                    + "the at time slot");
            this.examples.setText("add d/2020-01-01 t/0800-0900");
            break;
        case CLEAR:
            this.getRoot().setText("Clear Command");
            this.format.setText("clear");
            this.description.setText("Clears all consultation events stored in DukePro(f)");
            this.examples.setText("clear");
            break;
        case COMMAND_SUMMARY:
            this.getRoot().setText("Command Summary Command");
            this.format.setText("command_summary");
            this.description.setText("Opens this pop-up window containing a summary of all available commands");
            this.examples.setText("command_summary");
            break;
        case DELETE:
            this.getRoot().setText("Delete Command");
            this.format.setText("delete INDEX");
            this.description.setText("Deletes a consultation event from DukePro(f) at the specified INDEX");
            this.examples.setText("delete 1");
            break;
        case DELETE_BLOCKED:
            this.getRoot().setText("Delete Blocked Slot Command");
            this.format.setText("delete_blocked INDEX");
            this.description.setText("Deletes a blocked slot from DukePro(f) at the specified INDEX");
            this.examples.setText("delete_blocked 1");
            break;
        case EDIT:
            this.getRoot().setText("Edit Command");
            this.format.setText("edit INDEX [n/NAME] [d/DATE] [t/TIMESLOT] [l/LOCATION] [tag/TAG]... [r/REMARK]");
            this.description.setText(
                    "Edits the details of a consultation event from DukePro(f) at the specified INDEX");
            this.examples.setText("edit 2 n/Ruth t/0730-0830"
                    + "\n" + "edit 3 r/May have to switch to zoom");
            break;
        case EXIT:
            this.getRoot().setText("Exit Command");
            this.format.setText("exit");
            this.description.setText("Exits DukePro(f)");
            this.examples.setText("exit");
            break;
        case FIND:
            this.getRoot().setText("Find Command");
            this.format.setText("find KEYWORD [MORE_KEYWORDS]");
            this.description.setText("Finds all events whose names contain any of the specified keywords and displays "
                    + "them as a list with index numbers.");
            this.examples.setText("find Jacob");
            break;
        case HELP:
            this.getRoot().setText("Help Command");
            this.format.setText("help");
            this.description.setText("Opens a pop-up window with a link to DukePro(f)'s user guide.");
            this.examples.setText("help");
            break;
        case LIST:
            this.getRoot().setText("List Command");
            this.format.setText("list");
            this.description.setText("Lists down all consultation events scheduled in DukePro(f)");
            this.examples.setText("list");
            break;
        case LIST_BLOCKED:
            this.getRoot().setText("List Blocked Slots Command");
            this.format.setText("list_blocked");
            this.description.setText("Lists out all blocked slots in DukePro(f)");
            this.examples.setText("list_blocked");
            break;
        case LIST_FREE:
            this.getRoot().setText("List Free Slots Command");
            this.format.setText("list_free");
            this.description.setText("List out all free slots from 0000 today "
                    + "to 2359 of the date with last event/blocked slot");
            this.examples.setText("list_free");
            break;
        case NEXT_EVENT:
            this.getRoot().setText("Next Event Command");
            this.format.setText("next_event");
            this.description.setText("Displays the next consultation event scheduled for the day in DukePro(f)");
            this.examples.setText("next_event");
            break;
        case UPCOMING_EVENTS:
            this.getRoot().setText("Upcoming Event Command");
            this.format.setText("upcoming_events");
            this.description.setText("Lists down all the upcoming consultation events for the current day.");
            this.examples.setText("upcoming_events");
            break;
        default:
            assert false : "CommandDescCard: Invalid Command";
        }
    }
    //@@author
}
