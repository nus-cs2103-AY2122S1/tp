package seedu.address.ui;

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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

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
    private PersonListPanel personListPanel;
    private SummaryPanel summaryPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private DownloadWindow downloadWindowSuccess;
    private DownloadWindow downloadWindowFailure;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem downloadMenuItem;

    @FXML
    private StackPane panelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

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
        downloadWindowSuccess = new DownloadWindow(true);
        downloadWindowFailure = new DownloadWindow(false);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(downloadMenuItem, KeyCombination.valueOf("F2"));
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
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        panelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        summaryPanel = new SummaryPanel(logic.getSummary());
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
     * Shows summary panel.
     */
    @FXML
    private void handleSummary() {
        if (panelPlaceholder.getChildren().contains(summaryPanel.getRoot())) {
            return;
        }
        summaryPanel = new SummaryPanel(logic.getSummary());
        panelPlaceholder.getChildren().remove(personListPanel.getRoot());
        panelPlaceholder.getChildren().add(summaryPanel.getRoot());
    }

    /**
     * Shows summary panel.
     */
    @FXML
    private void handlePersonList() {
        if (!panelPlaceholder.getChildren().contains(summaryPanel.getRoot())) {
            return;
        }
        panelPlaceholder.getChildren().remove(summaryPanel.getRoot());
        panelPlaceholder.getChildren().add(personListPanel.getRoot());
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
        downloadWindowSuccess.hide();
        downloadWindowFailure.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            resultDisplay.setColorBasedOnResultType(commandResult.hasWarning(), false);

            if (commandResult.isShowSummary()) {
                handleSummary();
            } else {
                handlePersonList();
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isShowDownload()) {
                handleDownload();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser("[ERROR] " + e.getMessage());
            resultDisplay.setColorBasedOnResultType(false, true);
            throw e;
        }
    }

    /**
     * Retrieves data stored in SeniorLove.
     *
     * @return JSONArray of data
     * @throws IOException File reading error
     * @throws JSONException JSON error
     */
    private JSONArray getData() throws IOException, JSONException {
        String dataFile = "data/addressbook.json";
        InputStream inputStream = new FileInputStream(dataFile);
        String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        JSONObject obj = new JSONObject(text);
        return obj.getJSONArray("persons");
    }

    /**
     * Writes JSON data to a CSV file.
     *
     * @param data JSONArray of data
     * @param dest File object being written to
     * @throws IOException File reading error
     * @throws JSONException JSON error
     */
    private void writeToCsv(JSONArray data, File dest) throws JSONException, IOException {
        if (dest != null) {
            String csvData = CDL.toString(data);
            FileUtils.writeStringToFile(dest, csvData, Charset.defaultCharset());
            showDownloadWindow(downloadWindowSuccess);
        }
    }

    /**
     * Initialises a file in user's chosen directory.
     *
     * @return File object to be added to user's directory
     */
    private File userChooseDestination() {
        String csvName = "seniorlove.csv";
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(primaryStage);
        if (file == null) {
            return null;
        }
        return new File(file, csvName);
    }

    /**
     * Downloads data in SeniorLove into a CSV file in user's directory.
     */
    @FXML
    private void handleDownload() {
        try {
            JSONArray data = getData();
            File dest = userChooseDestination();
            writeToCsv(data, dest);
        } catch (IOException | JSONException e) {
            showDownloadWindow(downloadWindowFailure);
        }
    }

    /**
     * Shows relevant download window.
     *
     * @param window Download window to be shown
     */
    private void showDownloadWindow(DownloadWindow window) {
        if (!window.isShowing()) {
            window.show();
        } else {
            window.focus();
        }
    }
}
