package seedu.fast.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_ADD_INVESTMENT_PLAN_TAG;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_ADD_PRIORITY_TAG;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_ADD_TAG;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_DELETE_INVESTMENT_PLAN_TAG;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_DELETE_PRIORITY_TAG;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_DELETE_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.exceptions.IllegalValueException;

import seedu.fast.logic.commands.TagCommand;

import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.tag.Tag;

public class TagCommandParser implements Parser<TagCommand> {



    @Override
    public TagCommand parse(String arg) throws ParseException {
        requireNonNull(arg);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arg,
                PREFIX_ADD_TAG, PREFIX_ADD_PRIORITY_TAG, PREFIX_ADD_INVESTMENT_PLAN_TAG,
                PREFIX_DELETE_TAG, PREFIX_DELETE_PRIORITY_TAG, PREFIX_DELETE_INVESTMENT_PLAN_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TagCommand.MESSAGE_USAGE), ive);
        }

        List<Tag> addTags = new ArrayList<>();
        List<Tag> deleteTags = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_ADD_TAG).isPresent()) {
            for (String str: argMultimap.getAllValues(PREFIX_ADD_TAG)) {
                addTags.add(new Tag(str));
            }
        }

        if (argMultimap.getValue(PREFIX_ADD_PRIORITY_TAG).isPresent()) {
            for (String str: argMultimap.getAllValues(PREFIX_ADD_PRIORITY_TAG)) {
                addTags.add(new Tag(str));
            }
        }

        if (argMultimap.getValue(PREFIX_ADD_INVESTMENT_PLAN_TAG).isPresent()) {
            //TODO: to be implemented when IV tags are added
        }

        if (argMultimap.getValue(PREFIX_DELETE_TAG).isPresent()) {
            for (String str: argMultimap.getAllValues(PREFIX_DELETE_TAG)) {
                deleteTags.add(new Tag(str));
            }
        }

        if (argMultimap.getValue(PREFIX_DELETE_PRIORITY_TAG).isPresent()) {
            for (String str: argMultimap.getAllValues(PREFIX_DELETE_PRIORITY_TAG)) {
                deleteTags.add(new Tag(str));
            }
        }

        if (argMultimap.getValue(PREFIX_DELETE_INVESTMENT_PLAN_TAG).isPresent()) {
            //TODO: to be implemented when IV tags are added
        }


        return null;
    }

}
