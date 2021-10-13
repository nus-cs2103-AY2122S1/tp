package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindTagCaseInsensitiveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonTagsContainsCaseInsensitiveTagsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindTagCaseInsensitiveCommand object
 */
public class FindTagCaseInsensitiveCommandParser implements Parser<FindTagCaseInsensitiveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTagsCommand
     * and returns a FindTagsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTagCaseInsensitiveCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCaseInsensitiveCommand.MESSAGE_USAGE));
        }
        try {
            List<Tag> tagKeywords = Arrays.stream(trimmedArgs.split("\\s+"))
                    .map(Tag::new).collect(Collectors.toList());
            return new FindTagCaseInsensitiveCommand(new PersonTagsContainsCaseInsensitiveTagsPredicate(tagKeywords));
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
    }

}
