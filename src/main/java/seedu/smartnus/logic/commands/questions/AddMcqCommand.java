package seedu.smartnus.logic.commands.questions;

import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.smartnus.model.question.Question;

/**
 * Adds an MCQ question
 */
public class AddMcqCommand extends AddQuestionCommand {

    public static final String COMMAND_WORD = "mcq";
    public static final String MESSAGE_SUCCESS = "New question added: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an mcq question to SmartNUS.\n"
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_ANSWER + "ANSWER "
            + PREFIX_IMPORTANCE + "IMPORTANCE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "What is 1+1? "
            + PREFIX_OPTION + "3 "
            + PREFIX_OPTION + "4 "
            + PREFIX_OPTION + "5 "
            + PREFIX_ANSWER + "2 "
            + PREFIX_IMPORTANCE + "1 ";

    public AddMcqCommand(Question question) {
        super(question);
    }
}
