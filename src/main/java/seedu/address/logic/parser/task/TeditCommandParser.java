package seedu.address.logic.parser.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.TeditCommand;
import seedu.address.logic.commands.task.TeditCommand.EditTaskDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

public class TeditCommandParser implements Parser<TeditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TeditCommand
     * and returns an TeditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TeditCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(
                userInput, PREFIX_TASK_INDEX, PREFIX_NAME, PREFIX_DATE);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_TASK_INDEX)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TeditCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_TASK_INDEX).get());

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            editTaskDescriptor.setName(ParserUtil.parseName(argumentMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argumentMultimap.getValue(PREFIX_DATE).isPresent()) {
            editTaskDescriptor.setDeadline(ParserUtil.parseTaskDeadline(argumentMultimap.getValue(PREFIX_DATE).get()));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(TeditCommand.MESSAGE_NOT_EDITED);
        }

        return new TeditCommand(index, editTaskDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
