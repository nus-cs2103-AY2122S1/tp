package seedu.address.ui;

import java.util.logging.Logger;

import com.sun.javafx.scene.control.behavior.TabPaneBehavior;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Category;
import seedu.address.model.client.Client;
import seedu.address.model.product.Product;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {
    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;

    // Independent Ui parts residing in this Ui container
    private ClientListPanel clientListPanel;
    private ProductListPanel productListPanel;
    private ResultDisplay resultDisplay;
    private final HelpWindow helpWindow;
    private HelpMessage helpMessage;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane clientListPanelPlaceholder;

    @FXML
    private StackPane productListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane secondPanelPlaceholder;

    @FXML
    private TabPane tabPane;

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
     *
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
        clientListPanel = new ClientListPanel(logic.getFilteredClientList());
        clientListPanelPlaceholder.getChildren().add(clientListPanel.getRoot());

        productListPanel = new ProductListPanel(logic.getFilteredProductList());
        productListPanelPlaceholder.getChildren().add(productListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        helpMessage = new HelpMessage();
        secondPanelPlaceholder.getChildren().add(helpMessage.getRoot());

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
            secondPanelPlaceholder.getChildren().clear();
            secondPanelPlaceholder.getChildren().add(helpMessage.getRoot());
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

    @FXML
    private void handleStat() {
        PieChartSalesView salesView = new PieChartSalesView(logic.getFilteredClientList());
        secondPanelPlaceholder.getChildren().clear();
        secondPanelPlaceholder.getChildren().add(salesView.getRoot());
    }

    private void handleChangeTab(TabPaneBehavior tpb, int selectedTab, boolean isClient) {
        if (isClient) {
            if (selectedTab == 1) {
                tabPane.setDisable(true);
                tpb.selectNextTab();
                tabPane.setDisable(false);
            }
        } else {
            if (selectedTab == 0) {
                tabPane.setDisable(true);
                tpb.selectNextTab();
                tabPane.setDisable(false);
            }
        }
    }

    private void handleView(TabPaneBehavior tpb, int selectedTab, Category category) {
        if (category instanceof Client) {
            logger.info("View client's details: " + category);

            ViewMoreClient viewMoreClient = new ViewMoreClient();
            viewMoreClient.setClientDetails((Client) category);

            secondPanelPlaceholder.getChildren().clear();
            secondPanelPlaceholder.getChildren().add(viewMoreClient.getRoot());

            if (selectedTab == 1) {
                tabPane.setDisable(true);
                tpb.selectNextTab();
                tabPane.setDisable(false);
            }
        } else if (category instanceof Product) {
            logger.info("View product's details: " + category);

            ViewMoreProduct viewMoreProduct = new ViewMoreProduct();
            viewMoreProduct.setProductDetails((Product) category);

            secondPanelPlaceholder.getChildren().clear();
            secondPanelPlaceholder.getChildren().add(viewMoreProduct.getRoot());

            if (selectedTab == 0) {
                tabPane.setDisable(true);
                tpb.selectNextTab();
                tabPane.setDisable(false);
            }
        }
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

            int selectedTab = tabPane.getSelectionModel().getSelectedIndex();
            TabPaneBehavior tpb = new TabPaneBehavior(tabPane);

            Category category = commandResult.getInfo();
            CommandType commandType = commandResult.getCommandType();
            boolean isClient = commandResult.getIsClientCommand();

            switch (commandType) {
            case HELP:
                handleHelp();
                break;

            case EXIT:
                handleExit();
                break;

            case STAT:
                handleStat();
                break;

            case CLEAR:
                break;

            case ADD:
            case EDIT:
            case VIEW:
                handleView(tpb, selectedTab, category);
                break;

            default:
                handleChangeTab(tpb, selectedTab, isClient);
                break;
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
