package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindOrCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FindOrPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindOrCommandParser implements Parser<FindOrCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindOrCommand
     * and returns a FindOrCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindOrCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG);
        List<String> nameStringList = argMultimap.getAllValues(PREFIX_NAME);
        List<String> tagStringList = argMultimap.getAllValues(PREFIX_TAG);

        List<Name> nameKeywords;
        List<Tag> tagList;
        try {
            nameKeywords = nameStringList.stream().map(Name::new).collect(Collectors.toList());
            tagList = tagStringList.stream().map(Tag::new).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }

        FindOrPredicate findOrPredicate = new FindOrPredicate(nameKeywords, tagList);
        return new FindOrCommand(findOrPredicate);
    }

}

