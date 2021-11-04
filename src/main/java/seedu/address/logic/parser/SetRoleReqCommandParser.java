package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Set;

import seedu.address.logic.commands.SetRoleReqCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SetRoleReqCommandParser {


    /**
     * Parses the given {@code String} of arguments in the context of the SetRoleReqCommand
     * and returns a SetRoleReqCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetRoleReqCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROLE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(SetRoleReqCommand.getHelpMessage());
        }

        Set<String> roleReqList = ParserUtil.parseRoleRequirements(argMultimap.getAllValues(PREFIX_ROLE));
        return new SetRoleReqCommand(roleReqList);
    }


}
