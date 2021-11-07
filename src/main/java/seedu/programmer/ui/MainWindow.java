package seedu.programmer.ui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.commons.exceptions.IllegalValueException;
import seedu.programmer.commons.util.FileUtil;
import seedu.programmer.commons.util.JsonUtil;
import seedu.programmer.logic.Logic;
import seedu.programmer.logic.commands.CommandResult;
import seedu.programmer.logic.commands.DashboardCommandResult;
import seedu.programmer.logic.commands.DownloadCommandResult;
import seedu.programmer.logic.commands.ExitCommandResult;
import seedu.programmer.logic.commands.HelpCommandResult;
import seedu.programmer.logic.commands.ShowCommandResult;
import seedu.programmer.logic.commands.UploadCommandResult;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.exceptions.DuplicateStudentException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;

    private ResultDisplay resultDisplay;
    private final HelpWindow helpWindow;
    private final DashboardWindow dashboardWindow;
    private final PopupManager popupManager;
    private final FileManager fileManager;

    @FXML
    private Scene primaryScene;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private Button helpButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button downloadButton;

    @FXML
    private Button uploadButton;

    @FXML
    private Button dashboardButton;

    @FXML
    private StackPane studentListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane labResultListPanelPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private SplitPane mainPanel;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
        setAccelerators();

        helpWindow = new HelpWindow();
        dashboardWindow = new DashboardWindow(logic);
        popupManager = new PopupManager(primaryStage);
        fileManager = new FileManager(primaryStage);
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(KeyCombination.valueOf("F1"), this::handleExit);
        setAccelerator(KeyCombination.valueOf("F2"), this::handleHelp);
        setAccelerator(KeyCombination.valueOf("F3"), this::handleDownload);
        setAccelerator(KeyCombination.valueOf("F4"), this::handleUpload);
        setAccelerator(KeyCombination.valueOf("F5"), this::handleDashboard);
    }

    /**
     * Sets the accelerator of a button.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(KeyCombination keyCombination, Runnable runnable) {
        primaryScene.getAccelerators().put(keyCombination, runnable);
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        // Independent Ui parts residing in this Ui container
        StudentListPanel studentListPanel = new StudentListPanel(logic.getFilteredStudentList());
        studentListPanelPlaceholder.getChildren().add(studentListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getProgrammerErrorFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() == null) {
            return;
        }

        primaryStage.setX(guiSettings.getWindowCoordinates().getX());
        primaryStage.setY(guiSettings.getWindowCoordinates().getY());
    }

    /**
     * Shows the primary stage to the user.
     */
    void show() {
        primaryStage.show();
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (helpWindow.isShowing()) {
            helpWindow.focus();
            return;
        }
        logger.fine("Showing help window about the application.");
        helpWindow.show();
    }

    /**
     * Closes the application window.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
        dashboardWindow.hide();
    }

    /**
     * Displays the selected student's lab results.
     */
    @FXML
    public void handleShowResult() {
        LabResultListPanel labResultListPanel = new LabResultListPanel(logic.getSelectedInformation());
        labResultListPanelPlaceholder.getChildren().add(labResultListPanel.getRoot());
        logger.fine("Showing student's lab results.");
    }

    /**
     * Displays the dashboard window.
     */
    @FXML
    private void handleDashboard() {
        dashboardWindow.update();
        if (dashboardWindow.isShowing()) {
            dashboardWindow.focus();
            return;
        }

        logger.fine("Showing dashboard window...");
        dashboardWindow.show();
    }

    /**
     * Uploads CSV data into ProgrammerError's model storage.
     */
    @FXML
    private void handleUpload() {
        File chosenFile = fileManager.promptUserForCsvFile();
        if (chosenFile == null) {
            logger.info("User cancelled the file upload.");
            return;
        }

        List<Student> stuList = getStudentListFromCsv(chosenFile);
        if (stuList == null) {
            return;
        }

        try {
            logic.replaceExistingStudents(stuList);
        } catch (DuplicateStudentException e) {
            logger.info("Aborting: file contains duplicate student");
            popupManager.displayPopup("Upload failed: " + e.getMessage());
        }

        popupManager.displayPopup("Upload success! All past students have been deleted. You now have "
                    + stuList.size() + " students.");
        logger.info("Uploaded CSV data successfully!");
    }

    /**
     * Downloads the JSON data as a CSV file to the user's chosen directory.
     */
    @FXML
    private void handleDownload() {
        JSONArray jsonData = JsonUtil.getJsonData("data/programmerError.json");
        assert (jsonData != null);

        if (jsonData.length() == 0) {
            popupManager.displayPopup("No data to download!");
            return;
        }

        File destinationFile = fileManager.promptUserForFileDestination();
        if (destinationFile == null) {
            return;
        }

        JsonUtil.writeJsonToCsv(jsonData, destinationFile);
        popupManager.displayPopup("Your data has been downloaded to " + destinationFile + "!");
        logger.info("Data successfully downloaded as CSV.");
    }

    /**
     * Sets the hovered background colour for the exit button.
     */
    @FXML
    private void handleHover() {
        exitButton.setStyle("-fx-background-color: -fx-light-bg-color;");
    }

    /**
     * Sets the default background colour for exit button.
     */
    @FXML
    private void handleUnhover() {
        exitButton.setStyle("-fx-background-color: -fx-main-bg-color;");
    }

    /**
     * Retrieves the list of students from the CSV file.
     *
     * @param chosenFile the CSV file to read from
     * @return the list of Students
     */
    private List<Student> getStudentListFromCsv(File chosenFile) {
        List<Student> stuList;
        try {
            stuList = FileUtil.getStudentsFromCsv(chosenFile);
        } catch (IllegalArgumentException | IOException e) {
            popupManager.displayPopup("Upload failed: " + e.getMessage()); // Error with file data
            return null;
        } catch (IllegalValueException e) {
            popupManager.displayPopup(e.getMessage()); // Error with file headers
            return null;
        }

        if (stuList.size() == 0) {
            popupManager.displayPopup("Upload failed: No students were found in your file. "
                    + "Use the purge command if you want to remove all students.");
            return null;
        }

        return stuList;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.programmer.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult instanceof HelpCommandResult) {
                handleHelp();
            } else if (commandResult instanceof ExitCommandResult) {
                handleExit();
            } else if (commandResult instanceof ShowCommandResult) {
                handleShowResult();
            } else if (commandResult instanceof DownloadCommandResult) {
                handleDownload();
            } else if (commandResult instanceof DashboardCommandResult) {
                handleDashboard();
            } else if (commandResult instanceof UploadCommandResult) {
                handleUpload();
            }
            dashboardWindow.update();
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
