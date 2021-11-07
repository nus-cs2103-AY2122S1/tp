package seedu.anilist.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.anilist.commons.core.GuiSettings;
import seedu.anilist.commons.core.LogsCenter;
import seedu.anilist.logic.Logic;
import seedu.anilist.logic.commands.CommandResult;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final String cssFilePath = "view/";
    private final String[] themesArr = {
        "CharlotteTheme.css",
        "DarkTheme.css",
        "SquidGirlTheme.css",
        "WonderEggTheme.css"
    };

    private String themeCss;
    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private CommandBox commandBox;
    private AnimeListPanel animeListPanel;
    private ResultDisplay resultDisplay;
    private StatsDisplay statsDisplay;
    private ToggleGroup themeToggleGroup = new ToggleGroup();

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane animeListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private RadioMenuItem darkTheme;

    @FXML
    private RadioMenuItem charlotteTheme;

    @FXML
    private RadioMenuItem squidGirlTheme;

    @FXML
    private RadioMenuItem wonderEggTheme;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);
        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.animeListPanel = new AnimeListPanel(logic.getFilteredAnimeList(),
                logic.getCurrentTab(), this::executeCommand);

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        statsDisplay = new StatsDisplay();
        statsDisplay.setStatsCloseCommand(this::onCloseStatsWindow);

        initTheme();

        // Configure Hotkeys for tab switching
        KeyCombination nextTabHotKey = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
        Runnable nextTabRunnable = () -> animeListPanel.setNextTab();
        primaryStage.getScene().getAccelerators().put(nextTabHotKey, nextTabRunnable);
        KeyCombination prevTabHotKey = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        Runnable prevTabRunnable = () -> animeListPanel.setPrevTab();
        primaryStage.getScene().getAccelerators().put(prevTabHotKey, prevTabRunnable);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        animeListPanelPlaceholder.getChildren().add(animeListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAnimeListFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand, animeListPanel);
        this.commandBox = commandBox;
        animeListPanel.setCommandBox(commandBox);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

    }


    private void updateStatsDisplay() {
        statsDisplay.setAnimeListStats(logic.getStats());
    }

    private void initTheme() {
        KeyCombination themeHotKey = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
        Runnable themeRunnable = this::setNextTheme;
        primaryStage.getScene().getAccelerators().put(themeHotKey, themeRunnable);

        themeCss = logic.getThemeCss();

        darkTheme.setToggleGroup(themeToggleGroup);
        charlotteTheme.setToggleGroup(themeToggleGroup);
        squidGirlTheme.setToggleGroup(themeToggleGroup);
        wonderEggTheme.setToggleGroup(themeToggleGroup);

        setTheme(themeCss);

        charlotteTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setTheme(themesArr[0]);
            }
        });

        darkTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setTheme(themesArr[1]);
            }
        });

        squidGirlTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setTheme(themesArr[2]);
            }
        });

        wonderEggTheme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setTheme(themesArr[3]);
            }
        });
    }

    private void setTheme(String themeCss) {
        this.themeCss = themeCss;
        switch(themeCss) {
        case "CharlotteTheme.css":
            charlotteTheme.setSelected(true);
            break;
        case "DarkTheme.css":
            darkTheme.setSelected(true);
            break;
        case "SquidGirlTheme.css":
            squidGirlTheme.setSelected(true);
            break;
        case "WonderEggTheme.css":
            wonderEggTheme.setSelected(true);
            break;
        default:
            logger.log(Level.WARNING, themeCss + " file not found. Setting default theme Charlotte Theme.");
            themeCss = "CharlotteTheme.css";
            this.themeCss = themeCss;
            charlotteTheme.setSelected(true);
            break;
        }
        Scene scene = primaryStage.getScene();
        Scene statsScene = statsDisplay.getRoot().getScene();

        // Sets MainWindow and StatsDisplay stylesheets
        ObservableList<String> styleSheets = scene.getStylesheets();
        ObservableList<String> statsStyleSheets = statsScene.getStylesheets();

        styleSheets.remove(0);
        statsStyleSheets.remove(0);
        styleSheets.add(cssFilePath + themeCss);
        statsStyleSheets.add(cssFilePath + themeCss);
    }

    private void setNextTheme() {
        int index = 0;
        while (index < themesArr.length) {
            if (themesArr[index].equals(themeCss)) {
                break;
            }
            index++;
        }
        if (index == themesArr.length - 1) {
            setTheme(themesArr[0]);
        } else {
            setTheme(themesArr[index + 1]);
        }
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
     * Opens the stats display window or focuses on it if it's already opened.
     */
    @FXML
    public void handleStats() {
        updateStatsDisplay();
        if (!statsDisplay.isShowing()) {
            statsDisplay.show();
            updateStatsDisplay();
        } else {
            statsDisplay.focus();
            updateStatsDisplay();
        }
    }

    public void onCloseStatsWindow() {
        commandBox.enableCommandTextField();
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
        logic.setThemeCss(themeCss);
        logic.setGuiSettings(guiSettings);
        statsDisplay.hide();
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

            if (commandResult.isStats()) {
                handleStats();
                commandBox.disableCommandTextField();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
