package dash.logic.parser.taskcommand;

import static dash.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dash.logic.parser.CliSyntax.PREFIX_TAG;
import static dash.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import dash.logic.commands.taskcommand.FindTaskCommand;
import dash.logic.commands.taskcommand.FindTaskCommand.FindTaskDescriptor;
import dash.logic.parser.ArgumentMultimap;
import dash.logic.parser.ArgumentTokenizer;
import dash.logic.parser.Parser;
import dash.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindTaskCommandParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESCRIPTION, PREFIX_TAG);
        String preamble = argMultimap.getPreamble();

        FindTaskDescriptor findTaskDescriptor = new FindTaskDescriptor();
        boolean descPresent = argMultimap.getValue(PREFIX_TASK_DESCRIPTION).isPresent();
        boolean tagPresent = argMultimap.getValue(PREFIX_TAG).isPresent();
        if (preamble.isEmpty() && !descPresent && !tagPresent) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        } else if (!preamble.isEmpty()) {
            String[] preambleKeywords = preamble.split("\\s+");
            findTaskDescriptor.setDesc(Arrays.asList(preambleKeywords));
        }
        //if both preamble and desc prefix specified, desc prefix will override
        if (descPresent) {
            if (argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get().isEmpty()) {
                throw new ParseException("Arguments cannot be empty");
            }
            String[] nameKeywords = argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get().split("\\s+");
            findTaskDescriptor.setDesc(Arrays.asList(nameKeywords));
        }
        if (tagPresent) {
            if (argMultimap.getValue(PREFIX_TAG).get().isEmpty()) {
                throw new ParseException("Arguments cannot be empty");
            }
            String[] tagKeywords = argMultimap.getValue(PREFIX_TAG).get().split("\\s+");
            findTaskDescriptor.setTags(Arrays.asList(tagKeywords));
        }

        return new FindTaskCommand(findTaskDescriptor);

    }

}
