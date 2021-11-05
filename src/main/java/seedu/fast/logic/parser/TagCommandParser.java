package seedu.fast.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_ADD_TAG;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_DELETE_TAG;

import java.util.HashSet;
import java.util.Set;

import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.logic.commands.TagCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.tag.Tag;

public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns a TagCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public TagCommand parse(String arg) throws ParseException {
        requireNonNull(arg);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arg, PREFIX_ADD_TAG, PREFIX_DELETE_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            String errorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);
            throw new ParseException(errorMessage, ive);
        }

        Set<Tag> addTags = new HashSet<>();
        Set<Tag> deleteTags = new HashSet<>();

        try {
            if (argMultimap.getValue(PREFIX_ADD_TAG).isPresent()) {
                checkAndAddNonSpecialTags(argMultimap, addTags, PREFIX_ADD_TAG);
            }

            if (argMultimap.getValue(PREFIX_DELETE_TAG).isPresent()) {
                checkAndAddNonSpecialTags(argMultimap, deleteTags, PREFIX_DELETE_TAG);
            }
        } catch (IllegalArgumentException e) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new TagCommand(index, addTags, deleteTags);
    }

    private void checkAndAddNonSpecialTags(ArgumentMultimap argMultimap, Set<Tag> addTags,
                                        Prefix prefixAddTag) throws ParseException {
        for (String str : argMultimap.getAllValues(prefixAddTag)) {
            checkIfSpecialTag(str);
            addTags.add(Tag.createTag(str));
        }
    }

    private static void checkIfSpecialTag(String tagName) throws ParseException {
        if (Tag.isSpecialTag(tagName)) {
            throw new ParseException(Tag.MESSAGE_SPECIAL_TAG_ENTERED);
        }
    }

}
