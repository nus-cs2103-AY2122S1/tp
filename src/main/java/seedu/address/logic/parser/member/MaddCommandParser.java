package seedu.address.logic.parser.member;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import java.util.Set;

import seedu.address.logic.commands.member.MaddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Name;
import seedu.address.model.module.member.Address;
import seedu.address.model.module.member.Email;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.member.Phone;
import seedu.address.model.module.member.position.Position;

/**
 * Parses input arguments and creates a new PaddCommand object
 */
public class MaddCommandParser implements Parser<MaddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PaddCommand
     * and returns an PaddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MaddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_POSITION);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MaddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = argMultimap.getValue(PREFIX_EMAIL).isPresent()
                ? ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get())
                : null;
        Address address = argMultimap.getValue(PREFIX_ADDRESS).isPresent()
                ? ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get())
                : null;
        Set<Position> positionList = ParserUtil.parsePositions(argMultimap.getAllValues(PREFIX_POSITION));

        Member member = new Member(name, phone, email, address, positionList);

        return new MaddCommand(member);
    }
}
