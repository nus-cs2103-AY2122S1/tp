package seedu.smartnus.logic.commands.questions;

import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.smartnus.model.question.Question;

public class AddTfqCommand extends AddQuestionCommand {
    public static final String COMMAND_WORD = "tfq";
    public static final String MESSAGE_SUCCESS = "New question added: %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a true/false question to SmartNUS.\n"
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_ANSWER + "ANSWER (either T or F) "
            + PREFIX_IMPORTANCE + "IMPORTANCE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "Is 1+1 = 2? "
            + PREFIX_ANSWER + "T "
            + PREFIX_IMPORTANCE + "1 ";

    public AddTfqCommand(Question question) {
        super(question);
    }
}
