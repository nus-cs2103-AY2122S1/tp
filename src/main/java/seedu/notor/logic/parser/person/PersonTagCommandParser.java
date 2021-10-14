package seedu.notor.logic.parser.person;

import static java.util.Objects.requireNonNull;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.person.PersonEditCommand;
import seedu.notor.logic.commands.person.PersonTagCommand;

import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.tag.Tag;

public class PersonTagCommandParser extends PersonCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns an PersonTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;

        try {
            index = super.parseIndex(argMultimap);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, PersonEditCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(PersonEditCommand.MESSAGE_NOT_EDITED);
        }

        Set<Tag> tagsToAdd = parseTags(argMultimap.getAllValues(PREFIX_TAG)).get();

        return new PersonTagCommand(index, tagsToAdd);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTags(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
