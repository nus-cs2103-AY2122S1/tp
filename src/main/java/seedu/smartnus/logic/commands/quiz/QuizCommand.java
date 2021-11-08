package seedu.smartnus.logic.commands.quiz;


import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_NOT_IN_QUESTION_PANEL;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_NUMBER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.CommandResult;
import seedu.smartnus.logic.commands.CommandUtil;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.ui.panel.QuestionListPanel;

/**
 * Starts a quiz
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a quiz, takes optional arguments "
            + "specifying the questions to be included in the quiz.\n"
            + "Parameters: " + "[" + PREFIX_NUMBER + "NUMBER...] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "[" + PREFIX_LIMIT + "LIMIT]\n"
            + "LIMIT (must be a positive integer between 1 and 2147483647 both inclusive)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "CS2103T " + PREFIX_TAG + "CS2100 " + PREFIX_LIMIT + "3"
            + "Example: " + COMMAND_WORD + PREFIX_NUMBER + "1 2 3";


    public static final String MESSAGE_SUCCESS = "Quiz started!";

    public static final String MESSAGE_NO_QUESTIONS = "Quiz has no questions!";

    private final ArrayList<Predicate<Question>> predicates = new ArrayList<>();
    private final Comparator<Question> comparator;
    private final boolean isUseCurrentList;

    /**
     * Creates a QuizCommand
     * @param filterPredicates The predicates the questions in the quiz command has to fulfill
     *                         in order to be in the quiz
     * @param comparator The comparator used to sort the questions in the quiz
     */
    public QuizCommand(ArrayList<Predicate<Question>> filterPredicates, Comparator<Question> comparator) {
        this.predicates.addAll(filterPredicates);
        this.comparator = comparator;
        this.isUseCurrentList = false;
    }

    /**
     * Creates a QuizCommand
     * @param filterPredicates The predicates the questions in the quiz command has to fulfill
     *                         in order to be in the quiz
     * @param comparator The comparator used to sort the questions in the quiz
     * @param isUseCurrentList True if filter uses current List. False otherwise.
     */
    public QuizCommand(ArrayList<Predicate<Question>> filterPredicates, Comparator<Question> comparator,
                       boolean isUseCurrentList) {
        this.predicates.addAll(filterPredicates);
        this.comparator = comparator;
        this.isUseCurrentList = isUseCurrentList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.getPanel().equals(QuestionListPanel.QUESTION_PANEL)) {
            throw new CommandException(MESSAGE_NOT_IN_QUESTION_PANEL);
        }

        if (this.comparator != null) {
            model.sortFilteredQuizQuestionList(comparator);
        }

        if (this.isUseCurrentList) {
            // use current list state
            model.updateFilteredQuizQuestionListWithCurrent(CommandUtil.combinePredicates(predicates));
        } else {
            model.updateFilteredQuizQuestionList(CommandUtil.combinePredicates(predicates));
        }

        if (model.getFilteredQuizQuestionList().isEmpty()) {
            throw new CommandException(MESSAGE_NO_QUESTIONS);
        }

        // TODO: Find some other way of doing this? Making the constructor so long isn't that good as well
        //       Maybe explore overloading or something I'm not sure
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || ((other instanceof QuizCommand) // instanceof handles nulls
                && predicates.equals(((QuizCommand) other).predicates)
                && (Objects.equals(comparator, ((QuizCommand) other).comparator)));
    }

}
