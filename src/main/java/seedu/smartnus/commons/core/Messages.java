package seedu.smartnus.commons.core;

import seedu.smartnus.logic.commands.CommandUtil;
import seedu.smartnus.logic.commands.ExitCommand;
import seedu.smartnus.logic.commands.ListCommand;
import seedu.smartnus.logic.commands.quiz.NextQuestionCommand;
import seedu.smartnus.logic.commands.quiz.PrevQuestionCommand;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command, type 'help' to get a list of all commands";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX =
            "The index provided is larger than the number of questions in the list";
    public static final String MESSAGE_INVALID_NOTE_DISPLAYED_INDEX =
            "The index provided is larger than the number of notes in the list";
    public static final String MESSAGE_TOO_MANY_ARGUMENTS = "Inserted too many arguments! \n%1$s";
    public static final String MESSAGE_NO_TAGS_AND_INDEXES = "Cannot have tags and indexes together \n%1$s";
    public static final String MESSAGE_NO_LIMIT_AND_INDEXES = "Cannot have limit and indexes together \n%1$s";
    public static final String MESSAGE_QUESTIONS_FOUND_OVERVIEW = "%1$d question(s) found!";
    public static final String MESSAGE_INVALID_MCQ_ANSWER_FORMAT = "Invalid answer format!"
            + " Acceptable answers are 'a', 'b', 'c', 'd' (Case-insensitive)\n";
    public static final String MESSAGE_INVALID_TF_ANSWER_FORMAT = "Invalid answer format!"
            + " Acceptable answers are 't', 'f', 'true', 'false' (Case-insensitive)\n";
    public static final String MESSAGE_INVALID_SAQ_ANSWER_FORMAT = "Invalid answer format!"
            + " Answer must start with prefix ans/\n";
    public static final String MESSAGE_END_OF_QUIZ = "You have reached the end of the quiz, enter '"
            + ExitCommand.COMMAND_WORD + "' to exit the quiz, or enter '" + PrevQuestionCommand.COMMAND_WORD
            + "' to view the previous question.";
    public static final String MESSAGE_CONTINUE_QUIZ = "Enter '" + NextQuestionCommand.COMMAND_WORD
            + "' to proceed with the next question, or '" + ExitCommand.COMMAND_WORD + "' to exit the quiz";
    public static final String MESSAGE_INVALID_LIMIT_ARG =
            "Limit must be a valid positive integer (from 1 to 2147483647 inclusive)!";
    public static final String MESSAGE_INDEX_ERROR = "The index should be a positive integer from 1 to 2147483647";

    public static final String MESSAGE_NOT_IN_QUESTION_PANEL = "This is a command for questions. Try to go to "
            + "the question panel first:"
            + "\n DO: " + ListCommand.COMMAND_WORD + " " + CommandUtil.QUESTION_KEYWORD;

    public static final String MESSAGE_NOT_IN_NOTE_PANEL = "This is a command for notes. Try to go to "
            + "the note panel first:"
            + "\n DO: " + ListCommand.COMMAND_WORD + " " + CommandUtil.NOTE_KEYWORD;

    public static final String MESSAGE_NOT_IN_TAG_PANEL = "This is a command for tags. Try to go to "
            + "the tag panel first:"
            + "\n DO: " + ListCommand.COMMAND_WORD + " " + CommandUtil.TAG_KEYWORD;

}
