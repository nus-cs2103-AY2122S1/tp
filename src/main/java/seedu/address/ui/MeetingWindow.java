package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.Window;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;

/**
 * Window to show upcoming meetings.
 */
public class MeetingWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(MeetingWindow.class);
    private static final String FXML = "MeetingWindow.fxml";
    private ResultDisplay resultDisplay;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private MeetingListPanel meetingListPanel;

    @FXML
    private StackPane meetingListPanelPlaceholder;

    @FXML
    private StackPane meetingCommandBoxPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    /**
     * Creates a new MeetingWindow.
     *
     * @param root Stage to use as the root of the MeetingWindow.
     */
    public MeetingWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
    }

    void fillInnerParts(ObservableList<Meeting> meetingList) {
        logger.info("Loaded meeting list of size " + meetingList.size());
        this.meetingListPanel = new MeetingListPanel(meetingList);
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());
        CommandBox commandBox = new CommandBox(this::executeCommand);
        meetingCommandBoxPlaceholder.getChildren().add(commandBox.getRoot());
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
    }

    /**
     * Shows the meeting window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing upcoming meetings.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    @FXML
    private void handleQuitMeeting() {
        this.hide();
    }

    /**
     * Returns true if the meeting window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the meeting window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the meeting window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    public void setWindowDefaultSize(GuiSettings guiSettings) {
        getRoot().setHeight(guiSettings.getWindowHeight());
        getRoot().setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            getRoot().setX(guiSettings.getWindowCoordinates().getX());
            getRoot().setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String, Window)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText, Window.MEETING);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            if (commandResult.isQuitMeeting()) {
                handleQuitMeeting();
            }
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}
