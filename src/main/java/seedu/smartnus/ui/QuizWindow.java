package seedu.smartnus.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.commons.core.LogsCenter;
import seedu.smartnus.logic.Logic;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.ShortAnswerQuestion;
import seedu.smartnus.model.question.TrueFalseQuestion;
import seedu.smartnus.model.quiz.QuizManager;


/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class QuizWindow extends UiPart<Stage> {

    // TODO: Create new fxml for QuizWindow
    private static final String FXML = "QuizWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;
    private QuizManager quizManager;

    // Independent Ui parts residing in this Ui container
    // private QuestionListPanel questionListPanel;
    private ChoiceGrid choiceGrid;
    private ResultDisplay resultDisplay;
    private QuestionDisplay questionDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane questionListPanelPlaceholder;

    @FXML
    private StackPane choiceGridPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane questionDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;


    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public QuizWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        quizManager = new QuizManager(logic.getFilteredQuizQuestionList());

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

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        updateStatusBar();

        updateChoices();

        questionDisplay = new QuestionDisplay();
        questionDisplayPlaceholder.getChildren().add(questionDisplay.getRoot());


        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Updates the status bar with the current question number.
     */
    private void updateStatusBar() {
        int currentQuestionNumber = quizManager.getCurrentIndex() + 1;
        int totalQuestions = quizManager.getTotalQuestions();
        StatusBarFooter statusBarFooter = new StatusBarFooter(String.format("Question %d of %d",
                currentQuestionNumber , totalQuestions));
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
    }

    private void updateChoices() {
        choiceGridPlaceholder.getChildren().clear();
        choiceGrid = null;

        Question currentQuestion = quizManager.currQuestion();
        Choice selectedChoice = quizManager.getCurrentSelectedChoice();

        if (currentQuestion instanceof MultipleChoiceQuestion) {
            choiceGrid = new McqChoiceGrid(currentQuestion, selectedChoice);
        } else if (currentQuestion instanceof TrueFalseQuestion) {
            choiceGrid = new TfqChoiceGrid(currentQuestion, selectedChoice);
        } else if (currentQuestion instanceof ShortAnswerQuestion) {
            choiceGrid = new SaqChoiceGrid((ShortAnswerQuestion) currentQuestion, selectedChoice);
        }

        assert choiceGrid != null : "Question should either be instance of MultipleChoiceQuestion or TrueFalseQuestion";

        choiceGridPlaceholder.getChildren().add(choiceGrid.getRoot());
    }

    /**
     * Loads the first question from the list of questions
     */
    void loadQuiz() {
        String display = "Quiz Started!\n";
        Question currentQuestion = quizManager.currQuestion();
        resultDisplay.setFeedbackToUser(display);
        questionDisplay.displayQuestion(currentQuestion);
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
        // TODO: Currently creates a new MainWindow, can explore reusing the original MainWindow in the future
        UiUtils.setGuiSettings(logic, primaryStage);
        MainWindow mainWindow = new MainWindow(primaryStage, logic);
        mainWindow.show();
        mainWindow.fillInnerParts();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText, quizManager);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            questionDisplay.displayQuestion(quizManager.currQuestion());
            updateStatusBar();
            updateChoices();

            if (commandResult.isShowHelp()) {
                handleHelp();
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
