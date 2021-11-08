package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.logic.commands.CommandUtil.NOTE_KEYWORD;
import static seedu.smartnus.logic.commands.CommandUtil.QUESTION_KEYWORD;
import static seedu.smartnus.logic.commands.CommandUtil.TAG_KEYWORD;
import static seedu.smartnus.model.Model.PREDICATE_SHOW_ALL_NOTES;
import static seedu.smartnus.model.Model.PREDICATE_SHOW_ALL_STATISTICS;

import seedu.smartnus.model.Model;

/**
 * Lists all questions in SmartNus to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_QUESTIONS = "Listed all questions";
    public static final String MESSAGE_SUCCESS_NOTES = "Listed all notes";
    public static final String MESSAGE_SUCCESS_TAGS = "Listed all tags";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": lists the contents of SmartNUS.\n"
            + "Parameters: " + QUESTION_KEYWORD + " / " + TAG_KEYWORD + " / " + NOTE_KEYWORD + "\n"
            + "Example: " + COMMAND_WORD + " " + QUESTION_KEYWORD;

    private String panel;

    /**
     * Instantiates a new ListCommand
     * @param listArg argument passed to the list command
     */
    public ListCommand(String listArg) {
        panel = listArg; // assuming given listArg is valid
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String successMessage;
        switch (panel) {
        case QUESTION_KEYWORD:
            model.resetFilteredQuestionList();
            model.setPanel(QUESTION_KEYWORD);
            successMessage = MESSAGE_SUCCESS_QUESTIONS;

            break;
        case NOTE_KEYWORD:
            model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
            model.setPanel(NOTE_KEYWORD);
            successMessage = MESSAGE_SUCCESS_NOTES;

            break;
        case TAG_KEYWORD:
            model.updateFilteredTagStatistic(PREDICATE_SHOW_ALL_STATISTICS);
            model.setPanel(TAG_KEYWORD);
            successMessage = MESSAGE_SUCCESS_TAGS;

            break;
        default:
            // Defensive programming
            model.resetFilteredQuestionList();
            model.setPanel(QUESTION_KEYWORD);
            successMessage = MESSAGE_SUCCESS_QUESTIONS;
        }
        return new CommandResult(successMessage);
    }

    /**
     * Checks if two instances are the same.
     * @param other the other instance of listCommand.
     * @return true if both instances are the same, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && panel.equals(((ListCommand) other).panel));
    }
}
