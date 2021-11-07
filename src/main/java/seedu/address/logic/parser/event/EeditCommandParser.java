package seedu.address.logic.parser.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EeditCommand;
import seedu.address.logic.commands.event.EeditCommand.EditEventDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.member.Member;

/**
 * Parses input arguments and creates a new EeditCommand object
 */
public class EeditCommandParser implements Parser<EeditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EeditCommand
     * and returns an EeditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EeditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_EVENT_INDEX, PREFIX_NAME, PREFIX_DATE, PREFIX_MEMBER_INDEX);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_EVENT_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EeditCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_EVENT_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EeditCommand.MESSAGE_USAGE), pe);
        }

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEventDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editEventDescriptor.setDate(ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get()));
        }

        Set<Index> indexList = new HashSet<>();

        if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_MEMBER_INDEX)) {
            editEventDescriptor.setMemberSet(new HashSet<Member>());
            editEventDescriptor.setAttendance(new HashMap<Member, Boolean>());
            if (!argMultimap.getValue(PREFIX_MEMBER_INDEX).get().equals("")
                    || argMultimap.getAllValues(PREFIX_MEMBER_INDEX).size() != 1) {
                indexList = ParserUtil.parseIndices(argMultimap.getAllValues(PREFIX_MEMBER_INDEX));
            }
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EeditCommand.MESSAGE_NOT_EDITED);
        }

        return new EeditCommand(index, editEventDescriptor, indexList);
    }
}
