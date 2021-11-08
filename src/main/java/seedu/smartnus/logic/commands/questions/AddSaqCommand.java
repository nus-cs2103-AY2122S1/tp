package seedu.smartnus.logic.commands.questions;

import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.smartnus.model.question.Question;

/**
 * Adds a Short Answer Question to SmartNUS
 */
public class AddSaqCommand extends AddQuestionCommand {

    public static final String COMMAND_WORD = "saq";
    public static final String MESSAGE_SUCCESS = "New question added: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a short answer question to SmartNUS.\n"
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_ANSWER + "ANSWER THAT MUST INCLUDE " + PREFIX_KEYWORD + "KEYWORD... "
            + PREFIX_IMPORTANCE + "IMPORTANCE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "You're a wizard, Harry. Where is this quote from? "
            + PREFIX_ANSWER + " "
            + PREFIX_KEYWORD + "Harry "
            + PREFIX_KEYWORD + "Potter "
            + "and the Philosopher's Stone "
            + PREFIX_IMPORTANCE + "1 ";

    public AddSaqCommand(Question question) {
        super(question);
    }
}
