package seedu.programmer.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.logic.Logic;
import seedu.programmer.logic.commands.CommandResult;
import seedu.programmer.logic.commands.exceptions.CommandException;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Student;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private StudentListPanel studentListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private LabResultListPanel labResultListPanel;
    private StudentCard studentParticular;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane studentListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane labResultListPanelPlaceholder;

    @FXML
    private StackPane studentParticularPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

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
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        studentListPanel = new StudentListPanel(logic.getFilteredStudentList());
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
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Display the selected student's lab results.
     */
    @FXML
    public void handleShowResult(Student target) {
        if (studentParticularPlaceholder.getChildren().isEmpty()) {
            studentParticular = new StudentCard(target);
            studentParticularPlaceholder.getChildren().add(studentParticular.getRoot());
        } else {
            studentParticular.updateStudentInformation(target);
            studentParticularPlaceholder.getChildren().set(0, studentParticular.getRoot());
        }
        labResultListPanel = new LabResultListPanel(logic.getLabResultList(target));
        labResultListPanelPlaceholder.getChildren().add(labResultListPanel.getRoot());
    }

    /**
     * Downloads the JSON data as a CSV file to the user's chosen directory.
     */
    @FXML
    private void handleDownload() {
        try {
            JSONArray jsonData = getJsonData();
            File destinationFile = promptUserForDestination();
            if (destinationFile != null) {
                boolean writeSuccessful = writeJsonToCsv(jsonData, destinationFile);
                String message = writeSuccessful ? "Your data has been downloaded to " + destinationFile + "!"
                                                 : "No data to download!";
                Popup popup = createPopup(message);
                showPopupMessage(popup);
                logger.info("Data successfully downloaded as CSV.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays a popup message at the top-center with respect to the primaryStage.
     *
     * @param popup Popup object to be displayed on the primaryStage
     */
    private void showPopupMessage(Popup popup) {
        // Add some left padding
        popup.setX(primaryStage.getX() + primaryStage.getWidth() * 0.04);

        // Set Y coordinate scaled according to primaryStage's Y coordinate
        popup.setY(1.8 * primaryStage.getY());
        popup.show(primaryStage);
    }

    /**
     * Creates a Popup object with a message.
     *
     * @param message The text to display to the user
     * @return a Popup object
     */
    private Popup createPopup(String message) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);

        Label label = new Label(message);
        label.getStylesheets().add("view/Styles.css");
        label.getStyleClass().add("popup");

        // Hide popup when the user clicks on it
        label.setOnMouseReleased(e -> popup.hide());

        popup.getContent().add(label);
        return popup;
    }

    /**
     * Writes JSON data to a CSV file.
     *
     * @param jsonData JSONArray of data
     * @param destinationFile File object to write to
     * @throws IOException if error reading to or from file
     * @throws JSONException if JSON is invalid
     */
    private boolean writeJsonToCsv(JSONArray jsonData, File destinationFile) throws JSONException, IOException {
        String csv = CDL.toString(jsonData);
        if (csv == null) {
            return false;
        }

        FileUtils.writeStringToFile(destinationFile, csv, Charset.defaultCharset());
        logger.info("The following data was written:\n" + csv);
        return true;
    }

    /**
     * Creates a File object based on user's chosen directory.
     *
     * @return File object with a file name appended to the chosen directory
     */
    private File promptUserForDestination() {
        String destFileName = "programmerError.csv";
        DirectoryChooser dirChooser = new DirectoryChooser();
        File chosenDir = dirChooser.showDialog(primaryStage);
        if (chosenDir != null) {
            return new File(chosenDir, destFileName);
        }
        return null;
    }

    /**
     * Retrieves students' JSON data stored in ProgrammerError.
     *
     * @return JSONArray of student's data
     * @throws IOException if error reading to or from file
     * @throws JSONException if JSON is invalid
     */
    private JSONArray getJsonData() throws IOException, JSONException {
        String resourceName = "data/programmerError.json";
        InputStream is = new FileInputStream(resourceName);
        String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
        JSONObject json = new JSONObject(jsonTxt);
        return json.getJSONArray("students");
    }

    public StudentListPanel getStudentListPanel() {
        return studentListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.programmer.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            studentParticularPlaceholder.getChildren().clear();
            labResultListPanelPlaceholder.getChildren().clear();
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowResult()) {
                handleShowResult(commandResult.getTarget());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
