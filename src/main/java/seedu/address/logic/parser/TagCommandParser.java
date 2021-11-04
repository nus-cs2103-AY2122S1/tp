package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVE_TAG;

import java.util.ArrayList;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class TagCommandParser implements Parser<Command> {

    private void checkInputFormat(String args, ArgumentMultimap argMultimap) throws ParseException {
        boolean isAddTagArgsEmpty = argMultimap.getAllValues(PREFIX_ADD_TAG).size() == 1
                && argMultimap.getAllValues(PREFIX_ADD_TAG).indexOf("") == 0;
        boolean isRemoveTagArgsEmpty = argMultimap.getAllValues(PREFIX_REMOVE_TAG).size() == 1
                && argMultimap.getAllValues(PREFIX_REMOVE_TAG).indexOf("") == 0;
        boolean isAddAndRemoveTagsMissing = !args.contains("a/") && !args.contains("r/");
        if (isAddAndRemoveTagsMissing
                || (args.contains("a/") && args.contains("r/") && isAddTagArgsEmpty && isRemoveTagArgsEmpty)) {
            throw new ParseException(TagCommand.MESSAGE_MISSING_ADD_AND_REMOVE_TAG_ARGS);
        } else if (args.contains("a/") && isAddTagArgsEmpty) {
            throw new ParseException(TagCommand.MESSAGE_MISSING_ADD_TAG_ARGS);
        } else if (args.contains("r/") && isRemoveTagArgsEmpty) {
            throw new ParseException(TagCommand.MESSAGE_MISSING_REMOVE_TAG_ARGS);
        } else {
            return;
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ADD_TAG, PREFIX_REMOVE_TAG);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(pe.toString() + "\n" + TagCommand.MESSAGE_USAGE);
        }
        checkInputFormat(args, argMultimap);

        try {
            ArrayList<Tag> tagsToAdd = argMultimap.getAllValues(PREFIX_ADD_TAG)
                    .parallelStream()
                    .map(Tag::new)
                    .collect(Collectors.toCollection(ArrayList::new));
            ArrayList<Tag> tagsToRemove = argMultimap.getAllValues(PREFIX_REMOVE_TAG)
                    .parallelStream()
                    .map(Tag::new)
                    .collect(Collectors.toCollection(ArrayList::new));
            return new TagCommand(index, tagsToAdd, tagsToRemove);
        } catch (IllegalArgumentException ex) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TagCommand.MESSAGE_USAGE));
        }
    }
}
