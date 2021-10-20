package seedu.address.logic.parser;

import seedu.address.logic.commands.ReplaceTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagsPresentPredicate;
import seedu.address.model.tag.Tag;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ReplaceTagCommandParser implements Parser<ReplaceTagCommand> {

    public ReplaceTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        Tag tagToDelete;
        Tag tagToAdd;
        TagsPresentPredicate predicate;

        try {
            String[] arguments = trimmedArgs.split("\\s");

            if (arguments.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReplaceTagCommand.MESSAGE_USAGE));
            }

            tagToDelete = ParserUtil.parseTag(arguments[0]);
            tagToAdd = ParserUtil.parseTag(arguments[1]);
            predicate = new TagsPresentPredicate(Arrays.asList(arguments[0]));


        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }

        return new ReplaceTagCommand(tagToDelete,tagToAdd, predicate);
    }
}
