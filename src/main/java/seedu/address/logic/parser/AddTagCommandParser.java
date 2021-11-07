package seedu.address.logic.parser;

import static seedu.address.logic.commands.AddTagCommand.MESSAGE_INVALID_COMMAND_FORMAT_INVALID_INDEX;
import static seedu.address.logic.commands.AddTagCommand.MESSAGE_INVALID_COMMAND_FORMAT_TAGS_ABSENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagCommand object
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagCommand
     * and returns an AddTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;
        Set<Tag> tags;

        String argument = argMultimap.getPreamble().trim().toLowerCase();
        if (argument.equals("all")) {
            index = null; //When no index is inputted
        } else {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT_INVALID_INDEX,
                        AddTagCommand.MESSAGE_USAGE), pe); //When invalid index/nothing is inputted.
            }
        }

        tags = parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG));
        if (tags == null || tags.isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT_TAGS_ABSENT, AddTagCommand.MESSAGE_USAGE));
        }

        return new AddTagCommand(index, tags);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Set<Tag> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return null;
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return ParserUtil.parseTags(tagSet);
    }

}
