package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditCommandParser implements Parser<EditCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @param args Args for editing a type.
     * @return EditCommand object created from user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditCommand parse(String args) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        final String commandWord = "edit " + matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case EditModuleCommand.COMMAND_WORD:
            return new EditModuleCommandParser().parse(arguments);
        case EditStudentCommand.COMMAND_WORD:
            return new EditStudentCommandParser().parse(arguments);
        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
