package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_EMPTY_USAGE));
        }
        if (StringUtil.isName(args)) {
            return parseByName(args);
        } else if (StringUtil.isTag(args)) {
            return parseByTag(args);
        } else {
            return parseByIndex(args);
        }
    }

    private DeleteCommand parseByTag(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_TAG_USAGE));
            }
            String[] tags = trimmedArgs.replaceAll(" ", "").split(PREFIX_TAG.toString());
            if (tags.length < 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_TAG_USAGE));
            }
            String tagUsed = tags[1];
            String[] tagKeywords = tagUsed.trim().split("\\s+");
            return new DeleteCommand(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)), tagUsed);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_TAG_USAGE), pe);
        }
    }

    private DeleteCommand parseByIndex(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_INDEX_USAGE), pe);
        }
    }

    private DeleteCommand parseByName(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            return new DeleteCommand(name);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_NAME_USAGE), pe);
        }
    }
}

