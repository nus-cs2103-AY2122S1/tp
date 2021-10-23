package seedu.anilist.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.anilist.commons.core.GuiSettings;
import seedu.anilist.commons.core.LogsCenter;
import seedu.anilist.logic.Logic;
import seedu.anilist.logic.commands.CommandResult;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.AnimeList;
import seedu.anilist.model.anime.Anime;

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
    private AnimeListPanel animeListPanel;
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane animeListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane statsDisplayPlaceholder;

    @FXML
    private StatsDisplay statsDisplay;

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
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        animeListPanel = new AnimeListPanel(logic.getAnimeList(), logic.getFilteredAnimeList(),
                logic.getCurrentTab(), this::executeCommand);
        animeListPanelPlaceholder.getChildren().add(animeListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAnimeListFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, animeListPanel);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        statsDisplay = new StatsDisplay();
        statsDisplayPlaceholder.getChildren().add(statsDisplay.getRoot());
        updateStatsDisplay();

    }

    private void updateStatsDisplay() {
        int watchingCount = getAnimeListPanel().getWatchingCount();
        int toWatchCount = getAnimeListPanel().getToWatchCount();
        int finishedCount = getAnimeListPanel().getFinishedCount();
        int total = watchingCount + toWatchCount + finishedCount;

        statsDisplay.setAnimeListStats(
                total,
                watchingCount,
                toWatchCount,
                finishedCount);
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
        primaryStage.hide();
    }

    public AnimeListPanel getAnimeListPanel() {
        return animeListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.anilist.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isExit()) {
                handleExit();
            }

            updateStatsDisplay();

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            updateStatsDisplay();
            throw e;
        }
    }
}
