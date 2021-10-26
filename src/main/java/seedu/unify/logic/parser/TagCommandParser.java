package seedu.unify.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.unify.commons.core.index.Index;
import seedu.unify.logic.commands.TagCommand;
import seedu.unify.logic.commands.TagCommand.TagTaskDescriptor;
import seedu.unify.logic.parser.exceptions.ParseException;
import seedu.unify.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagCommand object
 */
public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Parses teh given {@code String} of arguments in the context of the TagCommand
     * and returns a TagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME, PREFIX_TAG);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE), pe);
        }

        TagTaskDescriptor tagTaskDescriptor = new TagTaskDescriptor();
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(tagTaskDescriptor::setTags);
        if (!tagTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(TagCommand.MESSAGE_NOT_TAGGED);
        }

        return new TagCommand(index, tagTaskDescriptor);
    }

    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;
        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
